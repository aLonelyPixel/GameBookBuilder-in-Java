package domains;

import java.util.HashMap;
import java.util.Map;

public class GameBook {

	private final String bookTitle;
	private final Map<String, Paragraph> paragraphMap;

	public GameBook(final String bookTitle) {
		this.bookTitle = bookTitle;
		paragraphMap = new HashMap<String, Paragraph>();
	}

	public String getBookTitle() {
		return bookTitle;
	}
	
	public boolean isEmpty() {
		return paragraphMap.isEmpty();
	}
	
	public Paragraph getFirstParagraph() {
		return paragraphMap.get("0");
	}
}
