package domains;

import java.util.HashSet;
import java.util.Set;

public class GameBookValidator {

	public Set<Integer> getUnreferencedParagraphs(GameBook gamebook) {
		Set<Integer> isolatedParagraphs = gamebook.getParagraphsIndexes(); //Init isolated paragraphs with all paragraphs
		Set<Integer> calledParagraphs = new HashSet<>();
		for (Integer paragraphIndex : isolatedParagraphs) {
			Set<Integer> choicesDestinations = gamebook.getChoicesDestinations(paragraphIndex);
			for (Integer destination : choicesDestinations) {
				calledParagraphs.add(destination);
			}
		}
		isolatedParagraphs.removeAll(calledParagraphs);
		isolatedParagraphs.remove(1);
		return isolatedParagraphs;
	}
	
	public Set<Integer> getUnreachableParagraphs(GameBook gamebook) {
		Set<Integer> reachableTerminalParagraphs = new HashSet<>();
		getAllEndingsFromStart(1, gamebook, reachableTerminalParagraphs);
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
}
