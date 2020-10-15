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
		String bookTitle = "";
		do {
			bookTitle = console.readLine("Titre du livre? ");
			bookEditor.setTitle(bookTitle);
			console.printLine("Votre livre à été crée avec succès !");
			console.printLine("\n\t\t"+bookEditor.getGameBookTitle());
		} while (bookTitle.equals("")||bookTitle.isEmpty());
		
	}	
	
}
