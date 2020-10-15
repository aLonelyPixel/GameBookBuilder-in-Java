package GameBook;

import commands.CommandMap;
import consoles.Console;

public class FrontController {
	
	private final Console console;
	private final CommandMap commandMap;
	private boolean bookExists = false;

	public FrontController(final Console console, final CommandMap commandMap) {
		this.console = console;
		this.commandMap = commandMap;
	}
	
	public void menuLoop() {
		String userChoice = "";
		do {
			console.printLine("\nMENU\nQue voulez-vous faire?");
			userChoice = console.readLine("1. [C]réer une livre\n2.[M]odifier un livre\n3. [Q]uitter\n");
			switch (userChoice.toLowerCase()) {
			case "c":
				userChoice = createNewGameBook(userChoice);
				break;
			case "m":
				modifyGameBook();
				break;
			case "q":
				commandMap.get("exit").execute();;
			default:
				break;
			}
		} while (!"q".equalsIgnoreCase(userChoice));
		
	}

	private String createNewGameBook(String userChoice) {
		if (!bookExists) {
			commandMap.get("create book").execute();
			bookExists = true;
		}else {
			userChoice = console.readLine("Vous avez déjà un livre en cours de création !\nSi vous créez un nouveau livre le livre"
					+ " précédent sera perdu. Voulez vous continuer [O/N]?");
			if (userChoice.equalsIgnoreCase("o")) {
				commandMap.get("create book").execute();
			}
		}
		return userChoice;
	}
	
	private void modifyGameBook() {
		if (bookExists) {
			console.printLine("NOT IMPLEMENTED");
		}else {
			console.printLine("Attention! Vous n'avez pas encore crée de GameBook !\n");
		}
	}
}
