package GameBook;

import commands.AddParagraphCommand;
import commands.CommandMap;
import commands.CreateBookCommand;
import commands.ExitCommand;
import commands.ModifyBook;
import commands.ModifyBookTitleCommand;
import commands.PrintBookCommand;
import consoles.Console;
import consoles.UserConsole;
import domains.GameBook;

public class Program {

	public static void main(String[] args) {
		final Console console = new UserConsole();
		final BookEditor bookEditor = new BookEditor(new GameBook(null));
		final CommandMap maps = new CommandMap(	new ExitCommand(console),
												new CreateBookCommand(console, bookEditor),
												new ModifyBook(console, bookEditor),
												new PrintBookCommand(console, bookEditor),
												new ModifyBookTitleCommand(console, bookEditor),
												new AddParagraphCommand(console, bookEditor));
		final FrontController frontController = new FrontController(console, maps);
		frontController.menuLoop();
	}
}
