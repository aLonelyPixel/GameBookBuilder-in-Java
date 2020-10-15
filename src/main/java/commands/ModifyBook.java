package commands;

import GameBook.BookEditor;
import consoles.Console;

public class ModifyBook extends Command{
	
	final Console console;
	final BookEditor bookEditor;
	

	public ModifyBook(final Console console, final BookEditor bookEditor) {
		super("modify", "Modifies a GameBook");
		this.console = console;
		this.bookEditor = bookEditor;
	}

	@Override
	public void execute() {
		console.printLine("woooooooooooooo");
	}

//	public void printBook() {
//		console.printLine("\nVotre livre à été crée avec succès !");
//		console.printLine("\n\t\t"+bookEditor.getGameBookTitle());
//		if (bookEditor.gameBookisEmpty()) {
//			console.printLine("\n\t(Aucun paragraphe)");
//		}
//	}
}
