package domains;

import java.util.HashSet;
import java.util.Set;

public class GameBookValidator {
	
	public String validate(GameBook gamebook) {
		String unreferencedParagraphs = writeUnreferencedParagraphs(getUnreferencedParagraphs(gamebook));
		String unreachableParagraphs = writeUnreachableParagraphs(getUnreachableParagraphs(gamebook));
		
		return unreferencedParagraphs.concat("\n" + unreachableParagraphs + "\n");
	}

	public Set<Integer> getUnreferencedParagraphs(GameBook gamebook) {
		Set<Integer> isolatedParagraphs = gamebook.getParagraphsIndexes(); //Init isolated paragraphs with all paragraphs
		Set<Integer> calledParagraphs = new HashSet<>();
		collectReferencedParagraphs(gamebook, isolatedParagraphs, calledParagraphs);
		isolatedParagraphs.removeAll(calledParagraphs);
		isolatedParagraphs.remove(1);
		return isolatedParagraphs;
	}

	private void collectReferencedParagraphs(GameBook gamebook, Set<Integer> isolatedParagraphs,
			Set<Integer> calledParagraphs) {
		for (Integer paragraphIndex : isolatedParagraphs) {
			Set<Integer> choicesDestinations = gamebook.getChoicesDestinations(paragraphIndex);
			for (Integer destination : choicesDestinations) {
				calledParagraphs.add(destination);
			}
		}
	}
	
	public Set<Integer> getUnreachableParagraphs(GameBook gamebook) {
		Set<Integer> reachableTerminalParagraphs = new HashSet<>();
		if (!gamebook.isEmpty()) getAllEndingsFromStart(1, gamebook, reachableTerminalParagraphs);
		Set<Integer> unreachableTerminalParagraphs = gamebook.getTerminalParagraphs();
		unreachableTerminalParagraphs.removeAll(reachableTerminalParagraphs);
		
		return unreachableTerminalParagraphs;
	}

	//TODO expliquer CTT récursive
	private void getAllEndingsFromStart(int startingParagraph, GameBook gamebook, Set<Integer> reachableTerminalParagraphs) {
		Set<Integer> currentDestinations = gamebook.getChoicesDestinations(startingParagraph);
		for (Integer destination : currentDestinations) {
			if (gamebook.isTerminalParagraph(destination)) {
				reachableTerminalParagraphs.add(destination);
			}else {
				getAllEndingsFromStart(destination, gamebook, reachableTerminalParagraphs);
			}
		}
	}
	
	private String writeUnreferencedParagraphs(Set<Integer> unreferencedParagraphs) {
		String firstValidation = "Noeuds absents de toute action : ";
		if (unreferencedParagraphs.isEmpty()) {
			firstValidation = firstValidation.concat("aucun noeud");
			;
		}else {
			for (Integer paragraph : unreferencedParagraphs) {
				firstValidation = firstValidation.concat(Integer.toString(paragraph) + " - ");
			}
		}
		return firstValidation;
	}	
	
	private String writeUnreachableParagraphs(Set<Integer> unreachableParagraphs) {
		String secondValidation = "Noeuds terminaux inaccessibles à partir du début : ";
		if (unreachableParagraphs.isEmpty()) {
			secondValidation = secondValidation.concat("aucun noeud");
		}else {
			for (Integer paragraph : unreachableParagraphs) {
				secondValidation = secondValidation.concat(Integer.toString(paragraph) + " - ");
			}
		}
		return secondValidation;
	}
}
