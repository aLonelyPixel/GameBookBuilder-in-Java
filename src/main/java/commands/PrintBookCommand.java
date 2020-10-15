package commands;

import java.util.Iterator;

import GameBook.BookEditor;
import consoles.Console;
import domains.Choice;
import domains.Paragraph;

public class PrintBookCommand extends Command{

	final Console console;
	final BookEditor bookEditor;
	

	public PrintBookCommand(final Console console, final BookEditor bookEditor) {
		super("print", "Prints the content of a GameBook");
		this.console = console;
		this.bookEditor = bookEditor;
	}

	@Override
	public void execute() {
		console.printLine("\n\t\t"+bookEditor.getGameBookTitle());
		if (bookEditor.gameBookisEmpty()) {
			console.printLine("\n\t(Aucun paragraphe)\n");
		}else {
			Iterator<Paragraph> paragraphIterator = bookEditor.iterator();
			while (paragraphIterator.hasNext()) {
				Paragraph thisParagraph = paragraphIterator.next();
				Iterator<Choice> choiceIterator = thisParagraph.iterator();
				console.print("\n\t§" + thisParagraph.getIndex() + "\t" + shortenParagraphText(thisParagraph.getParagraphText(), 25) + "\t");
				while (choiceIterator.hasNext()) {
					Choice thisChoice = choiceIterator.next();
					console.print(ShortenChoiceText(thisChoice.getText(), 10) + thisChoice.getDestParagraph() + "- ");
				}
			}
		}
	}
	
	private String shortenParagraphText(final String text, final int textMaxLength) {
		if (text.length()>textMaxLength) {
			return text.substring(0, textMaxLength) + "...";
		}else {
			return text;
		}
	}
	
	private String ShortenChoiceText(final String text, final int textMaxLength) {
		if (text.length()>textMaxLength) {
			return text.substring(0, textMaxLength) + " ";
		}else {
			return text;
		}
	}
}