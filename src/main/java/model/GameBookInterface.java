package model;

import java.util.List;
import java.util.Set;

public interface GameBookInterface {
	
	public void setBookTitle(String bookTitle);

	public String getBookTitle();

	public int getMaxParagraphIndex();

	public void addParagraph(int index, String paragraphText);

	public List<String> getParagraphsList();

	public void setParagraphText(int paragraphIndex, String newText);

	public String getParagraphText(int paragraphIndex);

	public void deleteParagraph(int deadParagraphIndex);

	public List<String> getChoicesList(int paragraphIndex);

	public void addChoice(int ownerParagraph, String choiceText, int destParagraph);

	public void deleteChoice(int paragraphIndex, String choiceToDelete);

	public int getChoiceDestinationParagraph(int paragraphIndex, String choiceText);

	public Set<Integer> getParagraphsIndexes();

	public void setChoiceText(int paragraphIndex, String oldText, String newText, int newDestParagraph);

	public String getValidation();

	public String getGraph();

	public boolean isEmpty();

	public Set<Integer> getChoicesDestinations(Integer paragraphIndex);

	public Set<Integer> getTerminalParagraphs();

	public boolean isTerminalParagraph(int destination);

	public List<Paragraph> getParagraphs();

}
