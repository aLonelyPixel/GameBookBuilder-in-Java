package GameBook;

import java.util.Iterator;
import domains.Choice;
import domains.GameBook;
import domains.Paragraph;

public class BookEditor implements Iterable<Paragraph>{
	
	private GameBook gameBook;
	private boolean bookExists;
	
	public BookEditor(final GameBook gameBook) {
		this.gameBook = gameBook;
		this.bookExists = false;
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
	
	public Iterator<Choice> choiceIterator(final int choiceOwner){
		return gameBook.choiceIterator(choiceOwner);
	}
	
	public boolean gameBookisEmpty() {
		return gameBook.isEmpty();
	}
	
	public int getMaxParagraphIndex() {
		return gameBook.getMaxParagraphIndex();
	}
	
	public void addParagraph(final int index, final Paragraph newParagraph) {
		gameBook.addParagraph(index, newParagraph);
	}
	
	public void addChoice(final int choiceOwner, final Choice newChoice) {
		gameBook.addChoice(choiceOwner, newChoice);
	}
	
	public int getNextChoiceIndex(final int paragraphIndex) {
		return gameBook.getParagraph(paragraphIndex).getMaxChoiceIndex();
	}

}
