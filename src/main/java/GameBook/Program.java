package GameBook;

import consoles.Console;
import consoles.UserConsole;

public class Program {

	public static void main(String[] args) {
		final Console console = new UserConsole();
		final FrontController frontController = new FrontController(console);
		frontController.menuLoop();
	}

}
