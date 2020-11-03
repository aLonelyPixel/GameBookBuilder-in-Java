package commands;

import GameBook.MainPresentationModel;
import consoles.Console;

public class DeleteParagraph extends Command{
	
	final Console console;
	final MainPresentationModel mpModel;

	public DeleteParagraph(final Console console, final MainPresentationModel mpModel) {
		super("delete paragraph", "Deletes a paragraph from the Gamebook");
		this.console = console;
		this.mpModel = mpModel;
	}

	@Override
	public void execute() {
		if (!mpModel.gameBookisEmpty()) {
			String deadParagraph = "";
			do {
				deadParagraph = console.readLine("Numéro du paragraphe à supprimer? ");
			} while (!validateParagraphChoice(deadParagraph));
			String userChoice = console.readLine("Etes-vous sûr? Le paragraphe " + deadParagraph + " va être définitivement supprimé (o/n) ");
			if (userChoice.equals("o")) {
				mpModel.deleteParagraph(Integer.parseInt(deadParagraph));
				console.printLine("Paragraphe supprimé!");
			}
		}else {
			console.printLine("Vous ne pouvez pas éliminer de paragraphe, le livre est vide!");
		}
	}

	private boolean validateParagraphChoice(String deadParagraph) {
		if (deadParagraph.matches("^\\d+$")) {
			if (mpModel.containsParagraph(Integer.parseInt(deadParagraph))) {
				return true;
			}else {
				console.printLine("Ce paragraphe n'existe pas dans le livre!");
			}
		}
		console.printLine("Entrez un chiffre!");
		return false;
	}
}
