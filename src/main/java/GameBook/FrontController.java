package GameBook;

import commands.CommandMap;
import consoles.Console;

public class FrontController {
	
	private final Console console;
	private final CommandMap commandMap;
	private boolean bookCreated = false;

	public FrontController(final Console console, final CommandMap commandMap) {
		this.console = console;
		this.commandMap = commandMap;
	}
	
	public void menuLoop() {
		String userChoice = "";
		do {
			console.printLine("MENU\n");
			userChoice = console.readLine("1. [C]réer une livre\n2. [M]odifier un livre\n3. [Q]uitter\n");
			
			switch (userChoice.toLowerCase()) {
			case "c":
				commandMap.get("create book").execute();
				bookCreated = true;
				break;
			case "m":
				modifyGameBook();
				break;
			case "q":
				commandMap.get("exit").execute();;
			default:
				console.print("Choix invalide dans le menu");
				break;
			}
		} while (!"q".equalsIgnoreCase(userChoice));
	}

	private void modifyGameBook() {
		if (bookCreated) {
			String userModifyingChoice = "";
			
			do {
				commandMap.get("print").execute();
				console.printLine("\nMENU DE MODIFICATIONS\n");
				userModifyingChoice = console.readLine("1. Modifier le titre du livre\n2. Ajouter un nouveau paragraphe\n3. Modifier un paragraphe existant\n"
						+ "4. Supprimer un paragraphe\n5. Arrêter la modification\n");
				
				switch (userModifyingChoice.toLowerCase()) {
				case "1":
					commandMap.get("modify title").execute();					
					break;
				case "2":
					commandMap.get("add paragraph").execute();					
					break;
				case "5":
					userModifyingChoice = "exit";
					break;
				default:
					console.print("Choix invalide dans le menu de modifications");
					break;
				}
			} while (!"exit".equalsIgnoreCase(userModifyingChoice));
		}else {
			console.printLine("Attention ! Vous n'avez pas encore crée de GameBook !");
		}
	}
	
}
