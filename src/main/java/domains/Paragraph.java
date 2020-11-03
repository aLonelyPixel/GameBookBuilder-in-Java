package domains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Paragraph implements Iterable<Choice>{

	private int index;
	private String paragraphText;
	private final Set<Choice> choices;
	
	//TODO créer methode getChoice
	public Paragraph(final int index, final String paragraphText) {
		this.index = index;
		this.paragraphText = paragraphText;
		this.choices = new HashSet<Choice>();
	}
	
	public Paragraph(final int index, final String paragraphText, final Set<Choice> choices) {
		this.index = index;
		this.paragraphText = paragraphText;
		this.choices = choices;
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	public String getParagraphText() {
		return paragraphText;
	}
	
	public void setParagraphText(String newText) {
		paragraphText = newText;
	}
	
	public boolean isTerminalParagraph() {
		return this.choices.isEmpty();
	}
	
	public void addChoice(final Choice newChoice) {
		choices.add(newChoice);
	}
	
	public int getMaxChoiceIndex() {
		int maxIndex = 1;
		for (Choice choice : choices) {
			if (choice.getIndex() > maxIndex) {
				maxIndex = choice.getIndex();
			}
		}
		return maxIndex;
	}

	@Override
	public Iterator<Choice> iterator() {
		Iterator<Choice> it = choices.iterator();
		return it;
	}

	public Set<Choice> getChoices() {
		return choices;
	}

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
	//TODO méthodes très similaires, à fondre en une seule ^^
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

	public void adjustChoicesOnRemoval(int deadParagraphIndex) {
		for (Choice choice : choices) {
			if (choice.getDestParagraph() >= deadParagraphIndex) {
				choice.shiftBackDestParagraph();
			}
		}
	}

	public boolean containsChoicesToModify(int deadParagraphIndex) {
		for (Choice choice : choices) {
			if (choice.getDestParagraph() >= deadParagraphIndex) {
				return true;
			}
		}
		return false;
	}

	public Set<Integer> getChoicesDestinations() {
		Set<Integer> destinations = new HashSet<>();
		for (Choice choice : choices) {
			destinations.add(choice.getDestParagraph());
		}
		return destinations;
	}
}
