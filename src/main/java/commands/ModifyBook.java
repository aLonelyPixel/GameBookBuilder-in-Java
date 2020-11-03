package commands;

import GameBook.MainPresentationModel;
import consoles.Console;

public class ModifyBook extends Command{
	
	final Console console;
	final MainPresentationModel mpModel;
	

	public ModifyBook(final Console console, final MainPresentationModel mpModel) {
		super("modify", "Modifies a GameBook");
		this.console = console;
		this.mpModel = mpModel;
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
