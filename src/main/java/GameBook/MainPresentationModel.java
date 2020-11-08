package GameBook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import domains.Choice;
import domains.GameBook;
import domains.GameBookValidator;
import domains.GraphBuilder;
import domains.Paragraph;

public class MainPresentationModel implements Iterable<Paragraph>{
	
	private GameBook gameBook;
	private GameBookValidator validator;
	private GraphBuilder graphBuilder;
	private boolean bookExists;
	
	//TODO construire livre hors constructeur
	public MainPresentationModel(final GameBook gameBook) {
		this.gameBook = gameBook;
		this.bookExists = false;
		this.validator = new GameBookValidator();
		this.graphBuilder = new GraphBuilder();
	}

	public boolean bookExists() {
		return bookExists;
	}
	
	public void setTitle(final String bookTitle) {
		gameBook = new GameBook(bookTitle);
		bookExists = true;
	}
	
	public String getGameBookTitle() {
		return this.gameBook.getBookTitle();
	}
	
	@Override
	public Iterator<Paragraph> iterator() {
		return gameBook.iterator();
	}
	
//	public Iterator<Choice> choiceIterator(final int choiceOwner){
//		return gameBook.choiceIterator(choiceOwner);
//	}
	
	public boolean gameBookisEmpty() {
		return gameBook.isEmpty();
	}
	
	public Set<Integer> getParagraphsIndexes() {
		return gameBook.getParagraphsIndexes();
	}
	
	public List<String> getStringOfParagraphsIndexes() {
		List<String> indexesInString = new ArrayList<>();
		Set<Integer> indexes = getParagraphsIndexes();
		for (Integer index : indexes) {
			indexesInString.add(String.valueOf(index));
		}
		return indexesInString;
	}
	
	public int getMaxParagraphIndex() {
		return gameBook.getMaxParagraphIndex();
	}
	
	public String getParagraphText(final int paragraphIndex) {
		return gameBook.getParagraphText(paragraphIndex);
	}
	
	public String addParagraph(int index, final Paragraph newParagraph) {
		if (index == 0 || index > getMaxParagraphIndex()) {
			index = getMaxParagraphIndex()+1;
			newParagraph.setIndex(index);
		}
		return gameBook.addParagraph(index, newParagraph);
	}
	
	public void addChoice(final int choiceOwner, final Choice newChoice) {
		gameBook.addChoice(choiceOwner, newChoice);
	}
	
//	public int getNextChoiceIndex(final int paragraphIndex) {
//		return gameBook.getMaxChoiceIndexForParagraph(paragraphIndex);
//	}

	public List<String> checkExistingParagraphs(int searchedParagraph) {
		if (!gameBook.containsParagraph(searchedParagraph)) {
			gameBook.addParagraph(searchedParagraph, new Paragraph(searchedParagraph, "todo - "));
		}
		return getParagraphsList();
	}

	public void setParagraphText(int paragraphIndex, String newText) {
		gameBook.setParagraphText(paragraphIndex, newText);
	}
	
	public Set<Choice> getChoices(final int paragraphIndex) {
		return gameBook.getChoices(paragraphIndex);
	}
	
	public List<String> getChoicesList(final int paragraphIndex) {
		return gameBook.getChoicesList(paragraphIndex);
	}

	public void setChoiceText(int paragraphIndex, Choice currentChoice, String userChoice) {
		gameBook.setChoiceText(paragraphIndex, currentChoice, userChoice);
	}
	
	public void setChoiceText(int paragraphIndex, String choice, String newText, int newDestParagraph) {
		gameBook.setChoiceText(paragraphIndex, choice, newText, newDestParagraph);
	}

	public void setChoiceDestParagraph(int paragraphIndex, Choice currentChoice, int newDestParagraph) {
		gameBook.setChoiceDestParagraph(paragraphIndex, currentChoice, newDestParagraph);
	}

	public void deleteChoice(int paragraphIndex, Choice choice) {
		gameBook.deleteChoice(paragraphIndex, choice);
	}
	
	public void deleteChoice(int paragraphIndex, String choiceText, int choiceDestParagraph) {
		gameBook.deleteChoice(paragraphIndex, choiceText, choiceDestParagraph);
	}

	/**
	 * Checks if a paragraph is in the book
	 * @param searchedParagraph the index of the searched paragraph
	 * @return true if the paragraph is in the Gamebook
	 */
	public boolean containsParagraph(int searchedParagraph) {
		return gameBook.containsParagraph(searchedParagraph);
	}

	public void deleteParagraph(int deadParagraphIndex) {
		gameBook.deleteParagraph(deadParagraphIndex);
	}
	
	public String validateGameBook() {
		return validator.validate(gameBook);
	}

	public String buildGraph() {
		return graphBuilder.getGraph(gameBook);
	}
	
	public List<String> getParagraphsList(){
		return gameBook.getParagraphsList();
	}

	public boolean containsChoice(int paragraphIndex, String choice) {
		return gameBook.containsChoice(paragraphIndex, choice);
	}

	public int getChoiceDestinationParagraph(int choiceOwner, String choice) {
		return gameBook.getChoiceDestinationParagraph(choiceOwner, choice);
	}
}
