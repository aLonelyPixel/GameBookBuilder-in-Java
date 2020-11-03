package GameBook;

import java.util.Iterator;
import java.util.Set;
import domains.Choice;
import domains.GameBook;
import domains.GameBookValidator;
import domains.Paragraph;

public class MainPresentationModel implements Iterable<Paragraph>{
	
	private GameBook gameBook;
	private GameBookValidator validator;
	private boolean bookExists;
	//TODO construire livre hors constructeur
	public MainPresentationModel(final GameBook gameBook) {
		this.gameBook = gameBook;
		this.bookExists = false;
		this.validator = new GameBookValidator();
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
	
	public int getMaxParagraphIndex() {
		return gameBook.getMaxParagraphIndex();
	}
	
	public String getParagraphText(final int paragraphIndex) {
		return gameBook.getParagraphText(paragraphIndex);
	}
	
	public void addParagraph(final int index, final Paragraph newParagraph) {
		gameBook.addParagraph(index, newParagraph);
	}
	
	public void addChoice(final int choiceOwner, final Choice newChoice) {
		gameBook.addChoice(choiceOwner, newChoice);
	}
	
	public int getNextChoiceIndex(final int paragraphIndex) {
		return gameBook.getMaxChoiceIndexForParagraph(paragraphIndex);
	}

	public void checkExistingParagraphs(int searchedParagraph) {
		if (!gameBook.containsParagraph(searchedParagraph)) {
			gameBook.addParagraph(searchedParagraph, new Paragraph(searchedParagraph, "todo - "));
		}
	}

	public void setParagraphText(int paragraphIndex, String newText) {
		gameBook.setParagraphText(paragraphIndex, newText);
	}
	
	public Set<Choice> getChoices(final int paragraphIndex) {
		return gameBook.getChoices(paragraphIndex);
	}

	public void setChoiceText(int paragraphIndex, Choice currentChoice, String userChoice) {
		gameBook.setChoiceText(paragraphIndex, currentChoice, userChoice);
	}

	public void setChoiceDestParagraph(int paragraphIndex, Choice currentChoice, int newDestParagraph) {
		gameBook.setChoiceDestParagraph(paragraphIndex, currentChoice, newDestParagraph);
	}

	public void deleteChoice(int paragraphIndex, Choice choice) {
		gameBook.deleteChoice(paragraphIndex, choice);
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
	
	public Set<Integer> getUnreferencedParagraphs(){
		return this.validator.getUnreferencedParagraphs(gameBook);
	}
	
	public Set<Integer> getUnreachableParagraphsFromStart(){
		return this.validator.getUnreachableParagraphs(gameBook);
	}
	
//	public HashSet<Integer>[] getValidationResults(){
//		Set<Integer>[] validations = new HashSet<Integer>[2];
//	}
}
