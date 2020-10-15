package GameBook;

import domains.GameBook;

public class BookEditor {
	
	private GameBook gameBook;
	private final boolean bookExists;
	
	public BookEditor(final GameBook gameBook) {
		this.gameBook = gameBook;
		this.bookExists = false;
	}

	public boolean bookExists() {
		return bookExists;
	}
	
	public void setTitle(final String bookTitle) {
		gameBook = new GameBook(bookTitle);
	}
	
	public String getGameBookTitle() {
		return this.gameBook.getBookTitle();
	}
}
