package commands;

import GameBook.BookEditor;
import consoles.Console;

public class CreateBookCommand extends Command{

	final Console console;
	final BookEditor bookEditor;

	public CreateBookCommand(final Console console, final BookEditor bookEditor) {
		super("create book", "Creates a new GameBook");
		this.console = console;
		this.bookEditor = bookEditor;
	}

	@Override
	public void execute() {
		String userChoice = "";
		if (bookEditor.bookExists()) {
			userChoice = console.readLine("Vous avez déjà un livre en cours de création !\nSi vous créez un nouveau livre le livre"
					+ " précédent sera perdu. Voulez vous continuer [O/N]? ");
		}
		
		if (userChoice.equalsIgnoreCase("o") || userChoice.isEmpty()) {
			String bookTitle = "";
			do {
				bookTitle = console.readLine("Titre du livre? ");
				bookEditor.setTitle(bookTitle);
				printBook();
			} while (bookTitle.equals("")||bookTitle.isEmpty());
		}
	}
	
	public void printBook() {
		console.printLine("\nVotre livre à été crée avec succès !");
		console.printLine("\n\t\t"+bookEditor.getGameBookTitle());
		if (bookEditor.gameBookisEmpty()) {
			console.printLine("\n\t(Aucun paragraphe)");
		}
	}
}
