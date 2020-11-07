package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GameBook implements Iterable<Paragraph>{

	private final String bookTitle;
	private final Map<Integer, Paragraph> paragraphMap;
	//TODO suppress index in paragraph and adapt code
	
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
		if (paragraphMap.containsKey(paragraphIndex)) {
			return paragraphMap.get(paragraphIndex);
		}
		return null;
	}
	
	public int getParagraphNumber() {
		return paragraphMap.size();
	}
	
	public Set<Choice> getChoices(final int paragraphIndex) {
		return paragraphMap.get(paragraphIndex).getChoices();
	}
	
	public List<String> getChoicesList(final int paragraphIndex) {
		return paragraphMap.get(paragraphIndex).getChoicesTexts();
	}
	
	public String getParagraphText(final int paragraphIndex) {
		return paragraphMap.get(paragraphIndex).getParagraphText();
	}
	
	public Set<Integer> getParagraphsIndexes(){
		Set<Integer> paragraphsIndexes = new HashSet<>();
		for (Integer paragraphIndex : paragraphMap.keySet()) {
			paragraphsIndexes.add(paragraphIndex);
		}
		return paragraphsIndexes;
	}
	
	public int getMaxParagraphIndex() {
		if (isEmpty()) {
			return 0;
		}else {
			return Collections.max(paragraphMap.keySet());
		}
	}
	
	public String addParagraph(int index, Paragraph newParagraph) {
		if(newParagraph.getParagraphText().isBlank()) newParagraph.setParagraphText("todo - ");
		if (paragraphMap.containsKey(index)) {
			if (!paragraphMap.get(index).getParagraphText().equals("todo - ")) {
				adjustChoicesOnAdd(index);
				adjustParagraphsOnAdd(index);
			}
		}
		paragraphMap.put(index, newParagraph);
		return String.valueOf(index) + ". " + paragraphMap.get(index).getParagraphText();
	}
	
	private void adjustParagraphsOnAdd(final int newKey) {
		for (int i = paragraphMap.size(); i > 0; i--) {
			if (paragraphMap.get(i).getIndex() >= newKey) {
				Paragraph p = new Paragraph(paragraphMap.get(i).getIndex()+1, paragraphMap.get(i).getParagraphText(), paragraphMap.get(i).getChoices());
				paragraphMap.put(p.getIndex(), p);
			}
		}
	}
	
	private void adjustParagraphsOnRemove(int deadParagraphIndex) {
		List<Paragraph> adjustedParagraphs = new ArrayList<Paragraph>();
		for (Entry<Integer, Paragraph> paragraph : paragraphMap.entrySet()) {
			if (paragraph.getValue().getIndex() > deadParagraphIndex) {
				Paragraph newParagraph = new Paragraph(paragraph.getValue().getIndex()-1, paragraph.getValue().getParagraphText(), paragraph.getValue().getChoices());
				adjustedParagraphs.add(newParagraph);
			}
		}
		for (Paragraph paragraph : adjustedParagraphs) {
			paragraphMap.remove(paragraph.getIndex()+1);
			paragraphMap.put(paragraph.getIndex(), paragraph);
		}
	}
	
	protected void adjustChoicesOnAdd(int index) {
		for (int i = 1; i < paragraphMap.size(); i++) {
			paragraphMap.get(i).moveChoicesUp(index);
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

	/**
	 * Checks if a paragraph is in the book
	 * @param searchedParagraph the index of the searched paragraph
	 * @return true if the paragraph is in the Gamebook
	 */
	public boolean containsParagraph(int searchedParagraph) {
		return paragraphMap.containsKey(searchedParagraph);
	}
	
//	public int getMaxChoiceIndexForParagraph(int paragraphIndex) {
//		return paragraphMap.get(paragraphIndex).getMaxChoiceIndex();
//	}

	public void setParagraphText(int paragraphIndex, String newText) {
		if (paragraphMap.containsKey(paragraphIndex)) {
			paragraphMap.get(paragraphIndex).setParagraphText(newText);
		}
	}

	public void setChoiceText(int paragraphIndex, Choice currentChoice, String userChoice) {
		paragraphMap.get(paragraphIndex).setChoiceText(currentChoice, userChoice);
	}
	
	public void setChoiceText(int paragraphIndex, String choice, String newText, int newDestParagraph) {
		paragraphMap.get(paragraphIndex).setChoiceText(choice, newText, newDestParagraph);
	}

	public void setChoiceDestParagraph(int paragraphIndex, Choice currentChoice, int newDestParagraph) {
		paragraphMap.get(paragraphIndex).setChoiceDestParagraph(currentChoice, newDestParagraph);		
	}

	public void deleteChoice(int paragraphIndex, Choice choice) {
		paragraphMap.get(paragraphIndex).deleteChoice(choice);
	}

	public void deleteChoice(int paragraphIndex, String choiceText, int choiceDestParagraph) {
		paragraphMap.get(paragraphIndex).deleteChoice(choiceText, choiceDestParagraph);
	}
	
	public void deleteParagraph(int deadParagraphIndex) {
		deleteAllChoicesToParagraph(deadParagraphIndex);
		paragraphMap.remove(deadParagraphIndex);
		adjustParagraphsOnRemove(deadParagraphIndex);
	}
	
	private void deleteAllChoicesToParagraph(int deadParagraphIndex){
		for (Entry<Integer, Paragraph> paragraph : paragraphMap.entrySet()) {
			paragraph.getValue().deleteMatchingChoices(deadParagraphIndex);
			if (paragraph.getValue().containsChoicesToModify(deadParagraphIndex)) {
				paragraph.getValue().adjustChoicesOnRemoval(deadParagraphIndex);
			}
		}
	}

	public Set<Integer> getChoicesDestinations(Integer paragraphIndex) {
		return paragraphMap.get(paragraphIndex).getChoicesDestinations();
	}

	public Set<Integer> getTerminalParagraphs() {
		Set<Integer> terminalParagraphs = new HashSet<>();
		for (Entry<Integer, Paragraph> paragraph : paragraphMap.entrySet()) {
			if (paragraph.getValue().isTerminalParagraph()) {
				terminalParagraphs.add(paragraph.getValue().getIndex());
			}
		}
		return terminalParagraphs;
	}
	
	public boolean isTerminalParagraph(int paragraphIndex) {
		return paragraphMap.get(paragraphIndex).isTerminalParagraph();
	}

	public List<String> getParagraphsList() {
		List<String> paragraphsList = new ArrayList<>();
		for (Entry<Integer, Paragraph> paragraph : paragraphMap.entrySet()) {
			paragraphsList.add(paragraph.getKey() + ". " + paragraph.getValue().getParagraphText());
		}
		return paragraphsList;
	}

	public boolean containsChoice(int paragraphIndex, String choice) {
		return paragraphMap.get(paragraphIndex).containsChoice(choice);
	}

	public int getChoiceDestinationParagraph(int choiceOwner, String choice) {
		return paragraphMap.get(choiceOwner).getChoiceDestinationParagraph(choice);
	}
}
