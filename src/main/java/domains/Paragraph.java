package domains;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Paragraph implements Iterable<Choice>{

	private final int index;
	private final String paragraphText;
	private final boolean isTerminal;
	private final Set<Choice> choices;
	
	public Paragraph(final int index, final String paragraphText) {
		this.index = index;
		this.paragraphText = paragraphText;
		this.choices = new HashSet<Choice>();
		isTerminal = false;
	}

	public int getIndex() {
		return index;
	}

	public String getParagraphText() {
		return paragraphText;
	}
	
	public boolean isTerminalParagraph() {
		return isTerminal;
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
	
}
