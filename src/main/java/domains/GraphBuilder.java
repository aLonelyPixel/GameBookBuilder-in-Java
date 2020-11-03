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
			List<Set<Integer>> currentParagraphsPaths = new ArrayList<Set<Integer>>();
			currentParagraphsPaths = getAllEndingsFromStart(paragraph, gameBook, currentParagraphsPaths);
			allPaths += formatPath(currentParagraphsPaths);
//			allPaths += formatPath(currentPath) + "\n";
		}
		return allPaths + "";
	}

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

//	private List<Set<Integer>> getAllEndingsFromStart(int startingParagraph, GameBook gamebook, List<Set<Integer>> currentPath) {
//		Set<Integer> currentDestinations = gamebook.getChoicesDestinations(startingParagraph);
//		Set<Integer> veryCurrentPath = new HashSet<>();
//		veryCurrentPath.add(startingParagraph);
//		currentPath.add(veryCurrentPath);
//		for (Integer destination : currentDestinations) {
//			if (gamebook.isTerminalParagraph(destination)) {
//				currentPath.get(startingParagraph).add(destination);
//				return currentPath;
//			}else {
//				Set<Integer> anotherCurrentPath = new HashSet<>();
//				anotherCurrentPath.addAll(veryCurrentPath);
//				currentPath.add(anotherCurrentPath);
//				getAllEndingsFromStart(destination, gamebook, currentPath);
//			}
//		}
//		return currentPath;
//	}
	
	private List<Set<Integer>> getAllEndingsFromStart(int startingParagraph, GameBook gamebook, List<Set<Integer>> currentPath) {
		List<Set<Integer>> allPathsFromHere = new ArrayList<Set<Integer>>();
		Set<Integer> currentDestinations = gamebook.getChoicesDestinations(startingParagraph);
		
		if (currentDestinations.isEmpty()) {
			List<Set<Integer>> terminalParagraphs = new ArrayList<Set<Integer>>();
			Set<Integer> thisTerminalParagraph = new HashSet<>();
			thisTerminalParagraph.add(startingParagraph);
			terminalParagraphs.add(thisTerminalParagraph);
			return terminalParagraphs;
		}
		for (Integer destination : currentDestinations) {
			Set<Integer> thisPath = new HashSet<>();
			thisPath.add(startingParagraph);
			allPathsFromHere.add(thisPath);
			if (gamebook.isTerminalParagraph(destination)) {
				thisPath.add(destination);
			}else {
				List<Set<Integer>> newlyGot = getAllEndingsFromStart(destination, gamebook, currentPath);
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
	
	
	
	//TODO To be greatly optimized, this is just stupid (but hey, it works...)
//	private String formatPath(Set<Integer> currentPath) {
//		String[] values = new String[currentPath.size()];
//		int index = 0;
//		String formattedPath = "";
//		for (Integer step : currentPath) {
//			values[index] = Integer.toString(step);
//			index++;
//		}
//		for (int i = 0; i < values.length-1; i++) {
//			formattedPath = formattedPath.concat("§" + values[i] + " -> ");
//		}
//		formattedPath += "§" + values[values.length-1];
//		return formattedPath;
//	}
}
