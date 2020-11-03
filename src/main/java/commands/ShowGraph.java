package commands;

import GameBook.MainPresentationModel;
import consoles.Console;

public class ShowGraph extends Command{

	final Console console;
	final MainPresentationModel mpModel;

	public ShowGraph(final Console console, final MainPresentationModel mpModel) {
		super("graph", "Shows the graph of a GameBook");
		this.console = console;
		this.mpModel = mpModel;
	}

	@Override
	public void execute() {
		if (!mpModel.bookExists()) {
			console.printLine("Vous n'avez pas encore crée de livre");
		}else if(mpModel.gameBookisEmpty()){
			console.printLine("Graphe vide\n");
		}else {
			console.printLine(mpModel.buildGraph());
		}
	}

}
