package domains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A paragraph object is determined by its index, its text and its choices.
 * For the Choices I've chosen to use a Set, more specifically a HashSet.
 * This is due by the fact that we do not want multiple choices that have the
 * same values. A Set ensures that is automatically checked and prevents multiple
 * of the same Choices to be contained. A List would not allow that as easily, nor
 * would a Map. Since we will only be needing values a Map isn't interesting here
 * either. Compared to a HashSet a TreeSet helps for the element order. However
 * this isn't interesting in this use case, hence the choice of HashSet.
 * The actions performed more often are adding and removing elements in the Set,
 * both are in O(1) complexity. The only drawback is the slow way of getting 
 * specific elements, which is done with a loop, so is in O(N) 
 * 
 * @author Andrea Dal Molin
 *
 */
public class Paragraph implements Iterable<Choice>{

	private int index;
	private String paragraphText;
	private final Set<Choice> choices;
	
	//TODO créer methode getChoice
	/**
	 * Builds a paragraph with the parameters
	 * @param index
	 * @param paragraphText
	 */
	public Paragraph(final int index, final String paragraphText) {
		this.index = index;
		this.paragraphText = paragraphText;
		this.choices = new HashSet<Choice>();
	}
	
	/**
	 * Builds a paragraph with an included set of Choices
	 * @param index
	 * @param paragraphText
	 * @param choices
	 */
	public Paragraph(final int index, final String paragraphText, final Set<Choice> choices) {
		this.index = index;
		this.paragraphText = paragraphText;
		this.choices = choices;
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int newIndex) {
		if (newIndex > 0) {
			this.index = newIndex;
		}
	}

	public String getParagraphText() {
		return paragraphText;
	}
	
	public void setParagraphText(String newText) {
		paragraphText = newText;
	}
	
	/**
	 * If a paragraph has no choices it means it's terminal
	 * This method checks if the current paragraph is terminal
	 * @return true if the paragrah is terminal
	 */
	public boolean isTerminalParagraph() {
		return this.choices.isEmpty();
	}
	
	/**
	 * Adds a choice to the current Set of Choices
	 * @param newChoice the choice to be added
	 */
	public void addChoice(final Choice newChoice) {
		choices.add(newChoice);
	}
	
//	public int getMaxChoiceIndex() {
//		int maxIndex = 1;
//		for (Choice choice : choices) {
//			if (choice.getIndex() > maxIndex) {
//				maxIndex = choice.getIndex();
//			}
//		}
//		return maxIndex;
//	}

	@Override
	public Iterator<Choice> iterator() {
		Iterator<Choice> it = choices.iterator();
		return it;
	}

	/**
	 * Returns the current Set of Choices
	 * @return a Set of Choices
	 */
	public Set<Choice> getChoices() {
		Set<Choice> paragraphChoices = new HashSet<>();
		paragraphChoices.addAll(choices);
		return paragraphChoices;
	}

	/**
	 * Gets all the Choices, converts them into Strings and puts them into a list
	 * Since our choices are already managed by a Set, we know there won't be any
	 * double values. This is why a simple List, in the form of an ArrayList which
	 * has a quick add time complexity (O(N)) is more than enough for this use case
	 * @return a List with the Choices formatted into Strings
	 */
	public List<String> getChoicesTexts() {
		List<String> choicesList = new ArrayList<>();
		for (Choice choice : this.choices) {
			choicesList.add(choice.getText() + " (dest. " + choice.getDestParagraph() + ")");
		}
		return choicesList;
	}
	
	/**
	 * Shifts ahead (+1) the choices that are affected by the addition of a paragraph
	 * @param newParagraphIndex the paragraph index of the added paragraph
	 */
	public void moveChoicesUp(int newParagraphIndex) {
		for (Choice choice : choices) {
			if (choice.getDestParagraph() >= newParagraphIndex) {
				choice.setDestParagraph(choice.getDestParagraph()+1);
			}
		}
	}

	public void setChoiceText(Choice currentChoice, String userChoice) {
		for (Choice choice : choices) {
			if (choice.equals(currentChoice)) {
				choice.setText(userChoice);
			}
		}
	}
	
	public void setChoiceText(String choice, String newText, int newDestParagraph) {
		for (Choice thisChoice : choices) {
			if (thisChoice.toString().equals(choice)) {
				thisChoice.setText(newText);
				thisChoice.setDestParagraph(newDestParagraph);
			}
		}
	}
	
	public void setChoiceDestParagraph(Choice currentChoice, int newDestParagraph) {
		for (Choice choice : choices) {
			if (choice.equals(currentChoice)) {
				choice.setDestParagraph(newDestParagraph);
			}
		}
	}

	public void deleteChoice(Choice choice) {
		if (choices.contains(choice)) {
			choices.remove(choice);
		}
	}

	public void deleteChoice(String choiceText, int choiceDestParagraph) {
		if (containsChoice(choiceText)) {
			choices.remove(getOriginalChoice(choiceText, choiceDestParagraph));
		}
	}
	
	/**
	 * From a string, fetches the original Choice object to then use
	 * @param choiceText the string representing the choice
	 * @param choiceDestParagraph the destination paragraph of the choice
	 * @return the choice corresponding to the search, null if not found
	 */
	private Choice getOriginalChoice(String choiceText, int choiceDestParagraph) {
		for (Choice choice : choices) {
			if (choice.toString().equals(choiceText) && choice.getDestParagraph() == choiceDestParagraph) {
				return choice;
			}
		}
		return null;
	}

	/**
	 * Deletes all the choices in the paragraph that lead to a paragraph 
	 * that is being deleted
	 * @param dyingParagraph the index of the paragraph that is being deleted
	 */
	public void deleteMatchingChoices(int dyingParagraph) {
		List<Choice> deadChoices = new ArrayList<Choice>();
		for (Choice choice : choices) {
			if (choice.getDestParagraph() == dyingParagraph) {
				deadChoices.add(choice);
			}
		}
		for (Choice deadChoice : deadChoices) {
			deleteChoice(deadChoice);
		}
	}

	/**
	 * Corrects the indexes when a paragraph is deleted
	 * @param deadParagraphIndex the index of the paragraph that has been deleted
	 */
	public void adjustChoicesOnRemoval(int deadParagraphIndex) {
		for (Choice choice : choices) {
			if (choice.getDestParagraph() >= deadParagraphIndex) {
				choice.shiftBackDestParagraph();
			}
		}
	}

	/**
	 * Checks if the current paragraph contains choices to  be modified when a
	 * paragraph is deleted
	 * @param deadParagraphIndex the index of the paragraph that is being deleted
	 * @return true if there are choices to modify
	 */
	public boolean containsChoicesToModify(int deadParagraphIndex) {
		for (Choice choice : choices) {
			if (choice.getDestParagraph() >= deadParagraphIndex) {
				return true;
			}
		}
		return false;
	}

	/**
	 * For all the choices in a paragraph, this methods gets their destination
	 * paragraph and puts them in a Set. A Set has been chosen because in this
	 * use case, it's useful not to have double values and a HashSet automatically
	 * takes care of that. We will need values only and the elements order is not
	 * important so a HashSet is the ideal collection.
	 * @return a Set containing all the destinations pointed by the paragraph in
	 * all of the different choices it offers
	 */
	public Set<Integer> getChoicesDestinations() {
		Set<Integer> destinations = new HashSet<>();
		for (Choice choice : choices) {
			destinations.add(choice.getDestParagraph());
		}
		return destinations;
	}

	/**
	 * Checks if the current paragraph contains a specific choice
	 * @param choice in String format
	 * @return true if the choice is contained by the paragraph
	 */
	public boolean containsChoice(String choice) {
		for (Choice thisChoice : choices) {
			if (thisChoice.toString().equals(choice) && thisChoice.getDestParagraph() == Integer.parseInt(choice.substring(choice.length()-2, choice.length()-1))) {
				return true;
			}
		}
		return false;
	}

	public int getChoiceDestinationParagraph(String choice) {
		for (Choice thisChoice : choices) {
			if (thisChoice.toString().equals(choice) && thisChoice.getDestParagraph() == Integer.parseInt(choice.substring(choice.length()-2, choice.length()-1))) {
				return thisChoice.getDestParagraph();
			}
		}
		return -1;
	}
}
