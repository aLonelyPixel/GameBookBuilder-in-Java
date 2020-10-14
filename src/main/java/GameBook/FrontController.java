package GameBook;

import consoles.Console;
import domains.GameBook;

public class FrontController {
	
	private final Console console;
	private GameBook gameBook;
	private boolean bookExists = false;

	public FrontController(final Console console) {
		this.console = console;
	}
	
	public void menuLoop() {
		String userChoice = "";
		do {
			console.printLine("\nMENU\nQue voulez-vous faire?");
			userChoice = console.readLine("1. [C]réer une livre\n2.[M]odifier un livre\n3. [Q]uitter\n");
			switch (userChoice.toLowerCase()) {
			case "c":
				if (!bookExists) {
					createNewBook();
					printBook();
				}else {
					userChoice = console.readLine("Vous avez déjà un livre en cours de création !\nSi vous créez un nouveau livre le livre"
							+ " précédent sera perdu. Voulez vous continuer [O/N]?");
					if (userChoice.equalsIgnoreCase("o")) {
						createNewBook();
						printBook();
					}
				}
				break;
			case "m":
				if (bookExists) {
					printBook();
				}else {
					console.printLine("Attention! Vous n'avez pas encore crée de GameBook !\n");
				}
				break;
			case "q":
				console.printLine("Au revoir !");
				System.exit(0);
			default:
				break;
			}
		} while (!"q".equalsIgnoreCase(userChoice));
		
	}

	//This is a comment test
	private boolean createNewBook() {
		String bookTitle = "";
		
		do {
			bookTitle = console.readLine("Titre du livre? ");
			gameBook = new GameBook(bookTitle);
			console.printLine("Votre livre à été crée avec succès !");
			bookExists = true;
			return true;
		} while (bookTitle.equals("")||bookTitle.isEmpty());
	}
	
	private void printBook() {
		console.printLine("\n\t\t"+gameBook.getBookTitle());
		if (this.gameBook.isEmpty()) {
			console.printLine("\n\t(Aucun paragraphe)");
		}else {
			this.gameBook.getFirstParagraph();
		}
	}
}
