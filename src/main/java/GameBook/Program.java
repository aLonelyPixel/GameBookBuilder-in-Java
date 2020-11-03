package GameBook;

import commands.AddParagraph;
import commands.CommandMap;
import commands.CreateBook;
import commands.DeleteParagraph;
import commands.Exit;
import commands.ModifyBook;
import commands.ModifyBookTitle;
import commands.ModifyParagraph;
import commands.PrintBook;
import commands.ShowGraph;
import commands.VerifyGameBook;
import consoles.Console;
import consoles.UserConsole;
import domains.GameBook;

public class Program {

	public static void main(String[] args) {
		final Console console = new UserConsole();
		final MainPresentationModel mpModel = new MainPresentationModel(new GameBook(""));
		final CommandMap maps = new CommandMap(	new Exit(console),
												new CreateBook(console, mpModel),
												new ModifyBook(console, mpModel),
												new PrintBook(console, mpModel),
												new ModifyBookTitle(console, mpModel),
												new AddParagraph(console, mpModel),
												new ModifyParagraph(console, mpModel),
												new DeleteParagraph(console, mpModel),
												new VerifyGameBook(console, mpModel),
												new ShowGraph(console, mpModel));//TODO add names and descriptions directly in main program
		final FrontController frontController = new FrontController(console, maps);
		frontController.menuLoop();
	}
}
