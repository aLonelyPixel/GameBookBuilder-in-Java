package GameBook;

import consoles.Console;
import domains.Book;

public class FrontController {
	
	private final Console console;
	private Book book;
	private boolean bookExists = false;

	public FrontController(final Console console) {
		this.console = console;
	}
	
	public void menuLoop() {
		String userChoice = "";
		do {
			console.printLine("\nMENU\nQue voulez-vous faire?");
			userChoice = console.readLine("1. [C]réer une livre\n2. [Q]uitter\n");
			switch (userChoice.toLowerCase()) {
			case "c":
				if (!bookExists) {
					createNewBook();
					printNewBook(book.getBookTitle());
				}else {
					userChoice = console.readLine("Vous avez déjà un livre en cours de création !\nSi vous créez un nouveau livre le livre"
							+ " précédent sera perdu. Voulez vous continuer [O/N]?");
					if (userChoice.equalsIgnoreCase("o")) {
						createNewBook();
						printNewBook(book.getBookTitle());
					}
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

	
	private boolean createNewBook() {
		String bookTitle = "";
		
		do {
			bookTitle = console.readLine("Titre du livre? ");
			book = new Book(bookTitle);
			console.printLine("Votre livre à été crée avec succès !");
			bookExists = true;
			return true;
		} while (bookTitle.equals("")||bookTitle.isEmpty());
	}
	
	private void printNewBook(String bookTitle) {
		console.printLine("\n\t\t"+bookTitle);
		if (this.book.isEmpty()) {
			console.printLine("\n\t(Aucun paragraphe)");
		}else {
			this.book.getFirstParagraph();
		}
	}
}
