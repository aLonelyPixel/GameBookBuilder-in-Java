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
		console.printLine(bookEditor.getGameBookTitle());
		
	}

	
}
