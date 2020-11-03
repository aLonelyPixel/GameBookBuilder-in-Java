package commands;

import GameBook.MainPresentationModel;
import consoles.Console;

public class ModifyBookTitle extends Command{

	final Console console;
	final MainPresentationModel mpModel;

	public ModifyBookTitle(final Console console, final MainPresentationModel mpModel) {
		super("modify title", "Modifies the title of a GameBook");
		this.console = console;
		this.mpModel = mpModel;
	}

	@Override
	public void execute() {
		String newTitle = "";
		newTitle = console.readLine("Quel sera le nouveau titre du GameBook ? ");
		if (!newTitle.equals("")) {
			mpModel.setTitle(newTitle);
		}else {
			console.printLine("Le titre du GameBook n'a pas été modifié\n");
		}
	}
	
}
