package commands;

import GameBook.MainPresentationModel;
import consoles.Console;

public class CreateBook extends Command{

	final Console console;
	final MainPresentationModel mpModel;

	public CreateBook(final Console console, final MainPresentationModel mpModel) {
		super("create book", "Creates a new GameBook");
		this.console = console;
		this.mpModel = mpModel;
	}

	@Override
	public void execute() {
		String userChoice = "";
		if (mpModel.bookExists()) {
			userChoice = console.readLine("Vous avez déjà un livre en cours de création !\nSi vous créez un nouveau livre le livre"
					+ " précédent sera perdu. Voulez vous continuer [O/N]? ");
		}
		
		if (userChoice.equalsIgnoreCase("o") || userChoice.isEmpty()) {
			String bookTitle = "";
			do {
				bookTitle = console.readLine("Titre du livre? ");
				mpModel.setTitle(bookTitle);
				printBook();
			} while (bookTitle.equals("")||bookTitle.isEmpty());
		}
	}
	
	public void printBook() {
		console.printLine("\nVotre livre à été crée avec succès !");
		console.printLine("\n\t\t"+mpModel.getGameBookTitle());
		if (mpModel.gameBookisEmpty()) {
			console.printLine("\n\t(Aucun paragraphe)");
		}
	}
}
