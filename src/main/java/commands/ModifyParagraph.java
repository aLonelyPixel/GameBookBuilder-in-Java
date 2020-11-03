package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import GameBook.MainPresentationModel;
import consoles.Console;
import domains.Choice;

public class ModifyParagraph extends Command{

	final Console console;
	final MainPresentationModel mpModel;

	public ModifyParagraph(final Console console, final MainPresentationModel mpModel) {
		super("modifyParagraph", "Modifies a paragraph");
		this.console = console;
		this.mpModel = mpModel;
	}

	@Override
	public void execute() {
		String userChoice = "";
		int paragraphNumber = mpModel.getMaxParagraphIndex();
		if (paragraphNumber > 0) {
			userChoice = console.readLine("Numéro du paragraphe à modifier ? (Enter si stop) ");
			if (!userChoice.isBlank()) {
				if ((Integer.parseInt(userChoice) > 0 && Integer.parseInt(userChoice) <= paragraphNumber)) {
					modifyParagraph(Integer.parseInt(userChoice));
				}else {
					console.printLine("Choix invalide !");
				}
			}
		}else {
			console.printLine("Il n'y a encore aucun paragraphe ! ");
		}
	}
	
	private void modifyParagraph(final int paragraphIndex) {
		printCurrentParagraph(paragraphIndex);
		String userChoice = "";
		String newText = console.readLine("Encodez le nouveau texte ou Enter pour le conserver: ");
		
		if (!newText.isBlank()) {
			mpModel.setParagraphText(paragraphIndex, newText);
		}
		
		userChoice = console.readLine("Souhaitez-vous modifier les actions possibles (o/n)? ").toLowerCase();
		if (userChoice.equals("o")) {
			modifyChoices(paragraphIndex);
		}
	}

	private void modifyChoices(final int paragraphIndex) {
		Set<Choice> choices = mpModel.getChoices(paragraphIndex);
		List<Choice> choicesToDelete = new ArrayList<Choice>();
		String userChoice = "";
		for (Choice choice : choices) {
			console.printLine("Action possible: " + choice.getText() + " " + choice.getDestParagraph());
			userChoice = console.readLine("Supprimer ? (o/n) ");
			while (!userChoice.toLowerCase().equals("o") && !userChoice.toLowerCase().equals("n")) {
				userChoice = console.readLine("Entrez un choix valide ! Supprimer ? (o/n) ");
			}
			do {
				if (userChoice.toLowerCase().equals("o")) {
					if (confirms()) {
						choicesToDelete.add(choice);
					}
				}else if(userChoice.toLowerCase().equals("n")) {
					modifyCurrentChoice(paragraphIndex, choice);
					userChoice = "exit";
				}
			} while (!userChoice.toLowerCase().equals("o") && !userChoice.toLowerCase().equals("n") && !userChoice.toLowerCase().equals("exit"));
		}
		for (Choice deadChoice : choicesToDelete) {
			deleteChoice(paragraphIndex, deadChoice);
		}
		userChoice = console.readLine("Souhaitez-vous ajouter une action possible (o/n)?").toLowerCase();
		if (userChoice.equals("o")) {
			addChoices(paragraphIndex);
		}
	}

	private boolean confirms() {
		String userChoice = "";
		do {
			userChoice = console.readLine("Êtes-vous sûr ? (o/n) ");
		} while (userChoice.isBlank() || (!userChoice.toLowerCase().equals("o") && !userChoice.toLowerCase().equals("n")));
		if (userChoice.equals("o")) {
			console.printLine("Action supprimée");
			return true;
		}
		return false;
	}

	private void deleteChoice(int paragraphIndex, Choice choice) {
		mpModel.deleteChoice(paragraphIndex, choice);
	}

	private void modifyCurrentChoice(int paragraphIndex, Choice currentChoice) {
		String userChoice = console.readLine("Encodez le nouveau texte ou Enter pour le conserver: ");
		if (!userChoice.isBlank()) {
			mpModel.setChoiceText(paragraphIndex, currentChoice, userChoice);
			userChoice = "";
		}
		userChoice = console.readLine("Encodez le nouveau numéro ou Enter pour le conserver: ");
		if (!userChoice.isBlank()) {
			mpModel.checkExistingParagraphs(Integer.parseInt(userChoice));
			mpModel.setChoiceDestParagraph(paragraphIndex, currentChoice, Integer.parseInt(userChoice));
		}
	}

	private void addChoices(final int choiceOwner) {
		String choiceText = "";
		do {
			choiceText = console.readLine("Texte de l’action possible (Enter si aucune) : ");
			if (!choiceText.isBlank()) {
				String destParagraph = console.readLine("Numéro du paragraphe de destination : ");
				mpModel.checkExistingParagraphs(Integer.parseInt(destParagraph));
				mpModel.addChoice(choiceOwner, new Choice(mpModel.getNextChoiceIndex(choiceOwner), choiceText, Integer.parseInt(destParagraph)));
			}
		} while (!choiceText.isBlank());
	}

	private void printCurrentParagraph(final int paragraphIndex) {
		console.printLine("Texte actuel du paragraphe " + paragraphIndex + " : " + mpModel.getParagraphText(paragraphIndex));
	}
}
