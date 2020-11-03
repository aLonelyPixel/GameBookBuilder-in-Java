package commands;

import GameBook.MainPresentationModel;
import consoles.Console;

public class VerifyGameBook extends Command{
	
	final Console console;
	final MainPresentationModel mpModel;

	public VerifyGameBook(final Console console, final MainPresentationModel mpModel) {
		super("verify", "Verifies the validity of a GameBook");
		this.console = console;
		this.mpModel = mpModel;
	}

	@Override
	public void execute() {
		if (mpModel.bookExists()) {
			console.printLine(mpModel.validateGameBook());
		}else {
			console.printLine("Vous n'avez pas encore crée de livre");
		}
	}
}
