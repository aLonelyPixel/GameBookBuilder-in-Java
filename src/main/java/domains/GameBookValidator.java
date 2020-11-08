package domains;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Andrea Dal Molin
 *
 */
public class GameBookValidator {
	
	public String validate(GameBook gamebook) {
		String unreferencedParagraphs = writeUnreferencedParagraphs(getUnreferencedParagraphs(gamebook));
		String unreachableParagraphs = writeUnreachableParagraphs(getUnreachableParagraphs(gamebook));
		
		return unreferencedParagraphs.concat("\n" + unreachableParagraphs + "\n");
	}

	/**
	 * This method collects all the paragraphs in the GameBook and then collects all the referenced ones.
	 * To get the unreferenced paragraph it removes from the Set containing all the paragraphs all the
	 * ones that have been referenced. The collectReferencedParagraphs method has a compleixity of O(P²).
	 * In this method the remove and removeAll methods are called. Remove has a O(1) complexity while 
	 * removeAll has a TTC of O(n) where. So the TTC of this method is O(P²+n+1) which can be simplified
	 * in O(P²).
	 * 
	 * @param gamebook the GameBook to be analysed
	 * @return a Set containing the unreferenced paragraph
	 */
	public Set<Integer> getUnreferencedParagraphs(GameBook gamebook) {
		Set<Integer> isolatedParagraphs = gamebook.getParagraphsIndexes(); //Init isolated paragraphs with all paragraphs
		Set<Integer> calledParagraphs = new HashSet<>();
		collectReferencedParagraphs(gamebook, isolatedParagraphs, calledParagraphs);
		isolatedParagraphs.removeAll(calledParagraphs);
		isolatedParagraphs.remove(1);
		return isolatedParagraphs;
	}
	
	/**
	 * This method collects all paragraphs that are referenced in the choices stored in a GameBook.
	 * Let the number of paragraphs in the GameBook be P and the number of destinations that it can
	 * point to be D.
	 * We are running through 2 for loops which means we will have a Theoretical Time Complexity (TTC) 
	 * of O(P*D). This said, the value of D depends on the number of paragraphs in the book, since the
	 * number of paragraphs that a single paragraph can point to is equal to the number of paragraphs.
	 * Which means that we can simplify the TTC in O(P*P) or O(P²).
	 */
	private void collectReferencedParagraphs(GameBook gamebook, Set<Integer> isolatedParagraphs, Set<Integer> calledParagraphs) {
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

	/**
	 * This method uses a starting paragraph to explore all the possible paths that lead to a terminal 
	 * paragraph. Basically, it start from the first paragraph then recursively calls itself with a
	 * paragraph that belongs to the destinations of the starting paragraph until it gets to a terminal
	 * paragraph. It uses a for loop, so the complexity dependes on the number of iterations it has to do.
	 * In this case, it iterates through all the destinations of the starting paragraph. The maximum
	 * the number of destinations can be depends on the number of paragraphs itself, so we can call that
	 * value P. This means that in the worst case scenario a paragraph will call all the paragraphs. 
	 * Therefore the TTC is in O(P).
	 */
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
