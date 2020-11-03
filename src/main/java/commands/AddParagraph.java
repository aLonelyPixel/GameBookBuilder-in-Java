package commands;

import GameBook.MainPresentationModel;
import consoles.Console;
import domains.Choice;
import domains.Paragraph;

public class AddParagraph extends Command{

	final Console console;
	final MainPresentationModel bookEditor;

	public AddParagraph(final Console console, final MainPresentationModel bookEditor) {
		super("add paragraph", "Adds a paragraph to the GameBook");
		this.console = console;
		this.bookEditor = bookEditor;
	}

	@Override
	public void execute() {
		String newParagraphIndex = console.readLine("Numéro du nouveau paragraphe ? ");

		if (Integer.parseInt(newParagraphIndex) < 0) {
			console.printLine("Impossible d'encoder un paragraphe avec un indice négatif !");
		}else {
			int newIndex = Integer.parseInt(newParagraphIndex);
			if (Integer.parseInt(newParagraphIndex) > bookEditor.getMaxParagraphIndex()) {
				newIndex = bookEditor.getMaxParagraphIndex()+1;
			}
			String paragraphText = console.readLine("Texte du nouveau paragraphe ? ");
			bookEditor.addParagraph(newIndex, new Paragraph(newIndex, paragraphText));
			addChoices(newIndex);
		}
	}

	private void addChoices(final int choiceOwner) {
		String choiceText = "";
		do {
			choiceText = console.readLine("Texte de l’action possible (Enter si aucune) : ");
			if (!"".equals(choiceText)) {
				String destParagraph = console.readLine("Numéro du paragraphe de destination : ");
				bookEditor.checkExistingParagraphs(Integer.parseInt(destParagraph));
				bookEditor.addChoice(choiceOwner, new Choice(bookEditor.getNextChoiceIndex(choiceOwner), choiceText, Integer.parseInt(destParagraph)));
			}
		} while (!choiceText.isBlank());
	}
}
