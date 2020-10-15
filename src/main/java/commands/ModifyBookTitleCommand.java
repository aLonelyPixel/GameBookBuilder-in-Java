package commands;

import GameBook.BookEditor;
import consoles.Console;

public class ModifyBookTitleCommand extends Command{

	final Console console;
	final BookEditor bookEditor;

	public ModifyBookTitleCommand(final Console console, final BookEditor bookEditor) {
		super("modify title", "Modifies the title of a GameBook");
		this.console = console;
		this.bookEditor = bookEditor;
	}

	@Override
	public void execute() {
		String newTitle = "";
		newTitle = console.readLine("Quel sera le nouveau titre du GameBook ? ");
		if (!newTitle.equals("")) {
			bookEditor.setTitle(newTitle);
		}else {
			console.printLine("Le titre du GameBook n'a pas été modifié\n");
		}
	}
	
}
