package commands;

import java.util.Set;

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
		printUnreferencedParagraphs();
		printUnreachableParagraphs();
		console.printLine();
	}

	private void printUnreferencedParagraphs() {
		console.print("Nœuds absents de toute action : ");
		Set<Integer> unreferencedParagraphs = mpModel.getUnreferencedParagraphs();
		if (unreferencedParagraphs.isEmpty()) {
			console.printLine("aucun noeud");
		}else {
			printParagraphs(unreferencedParagraphs);
		}
	}
	
	private void printUnreachableParagraphs() {
		console.print("Nœuds terminaux inaccessibles à partir du début : ");
		Set<Integer> unreachableParagraphs = mpModel.getUnreachableParagraphsFromStart();
		if (unreachableParagraphs.isEmpty()) {
			console.printLine("aucun noeud");
		}else {
			printParagraphs(unreachableParagraphs);
		}
	}

	private void printParagraphs(Set<Integer> unreachableParagraphs) {
		for (Integer paragraph : unreachableParagraphs) {
			console.print(paragraph + " - ");
		}
	}
}
