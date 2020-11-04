package domains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphBuilder {

	public String getGraph(GameBook gameBook) {
		String allPaths = "";
		Set<Integer> allParagraphs = gameBook.getParagraphsIndexes();
		for (Integer paragraph : allParagraphs) {
			List<Set<Integer>> currentParagraphsPaths = getAllEndingsFromParagraph(paragraph, gameBook);
			allPaths += formatPath(currentParagraphsPaths);
		}
		return allPaths;
	}

	private List<Set<Integer>> getAllEndingsFromParagraph(int startingParagraph, GameBook gamebook) {
		if (gamebook.isTerminalParagraph(startingParagraph)) {
			return getTerminalParagraph(startingParagraph);
		}
		
		List<Set<Integer>> allPathsFromHere = new ArrayList<Set<Integer>>();
		Set<Integer> currentDestinations = gamebook.getChoicesDestinations(startingParagraph);
		
		for (Integer destination : currentDestinations) {
			Set<Integer> thisPath = new HashSet<>();
			thisPath.add(startingParagraph);
			allPathsFromHere.add(thisPath);
			if (gamebook.isTerminalParagraph(destination)) {
				thisPath.add(destination);
			}else {
				List<Set<Integer>> newlyGot = getAllEndingsFromParagraph(destination, gamebook);
				if (newlyGot.size() == 1) {
					thisPath.addAll(newlyGot.get(0));
				}
				boolean firstPassed = false;
				for (Set<Integer> paths : newlyGot) {
					if (!firstPassed) {
						thisPath.addAll(paths);
					}else {
						Set<Integer> anotherPath = new HashSet<>();
						anotherPath.add(startingParagraph);
						anotherPath.addAll(paths);
						allPathsFromHere.add(anotherPath);
					}
					firstPassed = true;
				}
			}
		}
		return allPathsFromHere;
	}

	private List<Set<Integer>> getTerminalParagraph(int startingParagraph) {
		List<Set<Integer>> terminalParagraphs = new ArrayList<Set<Integer>>();
		Set<Integer> thisTerminalParagraph = new HashSet<>();
		thisTerminalParagraph.add(startingParagraph);
		terminalParagraphs.add(thisTerminalParagraph);
		return terminalParagraphs;
	}
	
	//TODO To be greatly optimized, this is just stupid (but hey, it works...)
	private String formatPath(List<Set<Integer>> currentParagraphPaths) {
		String allCurrentParagraphPaths = "";
		for (Set<Integer> path : currentParagraphPaths) {
			String[] values = new String[path.size()];
			int index = 0;
			String formattedPath = "";
			for (Integer step : path) {
				values[index] = Integer.toString(step);
				index++;
			}
			for (int i = 0; i < values.length-1; i++) {
				formattedPath = formattedPath.concat("§" + values[i] + " -> ");
			}
			formattedPath += "§" + values[values.length-1];
			allCurrentParagraphPaths += formattedPath + "\n";
		}
		
		return allCurrentParagraphPaths;
	}
}
