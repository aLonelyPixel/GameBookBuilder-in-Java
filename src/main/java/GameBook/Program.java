package GameBook;

import commands.CommandMap;
import commands.CreateBookCommand;
import commands.ExitCommand;
import commands.ModifyBook;
import consoles.Console;
import consoles.UserConsole;
import domains.GameBook;

public class Program {

	public static void main(String[] args) {
		final Console console = new UserConsole();
		final BookEditor bookEditor = new BookEditor(new GameBook(null));
		final CommandMap maps = new CommandMap(	new ExitCommand(console),
												new CreateBookCommand(console, bookEditor),
												new ModifyBook(console, bookEditor));
		final FrontController frontController = new FrontController(console, maps);
		
		frontController.menuLoop();
	}

}
