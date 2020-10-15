package domains;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameBook implements Iterable<Paragraph>{

	private final String bookTitle;
	private final Map<Integer, Paragraph> paragraphMap;

	public GameBook(final String bookTitle) {
		this.bookTitle = bookTitle;
		paragraphMap = new HashMap<Integer, Paragraph>();
	}

	public String getBookTitle() {
		return bookTitle;
	}
	
	public boolean isEmpty() {
		return paragraphMap.isEmpty();
	}
	
	public Paragraph getParagraph(final int paragraphIndex) {
		return paragraphMap.get(paragraphIndex);
	}
	
	public Paragraph getFirstParagraph() {
		return paragraphMap.get(0);
	}
	
	public int getParagraphNumber() {
		return paragraphMap.size();
	}
	
	public int getMaxParagraphIndex() {
		if (isEmpty()) {
			return 0;
		}else {
			return Collections.max(paragraphMap.keySet());
		}
	}
	
	public void addParagraph(final int index, final Paragraph newParagraph) {
		if (paragraphMap.containsKey(index)) {
			adjustParagraphsOnAdd(index);
		}
		paragraphMap.put(index, newParagraph);
		
	}
	
	private void adjustParagraphsOnAdd(final int newKey) {
		for (int i = paragraphMap.size(); i >= newKey; i--) {
			Paragraph p = new Paragraph(paragraphMap.get(i).getIndex() + 1, paragraphMap.get(i).getParagraphText());
			paragraphMap.put(p.getIndex(), p);
		}
	}
	
	public void addChoice(final int choiceOwner, final Choice newChoice) {
		paragraphMap.get(choiceOwner).addChoice(newChoice);
	}

	@Override
	public Iterator<Paragraph> iterator() {
		Iterator<Paragraph> it = paragraphMap.values().iterator();
		return it;
	}
	
	public Iterator<Choice> choiceIterator(final int choiceOwner){
		return paragraphMap.get(choiceOwner).iterator();
	}
}
