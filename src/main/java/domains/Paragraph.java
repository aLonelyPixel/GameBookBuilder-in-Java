package domains;

public class Paragraph {

	private final String paragraphTitle;
	private final String paragraphText;
	private final boolean isTerminal;
	
	public Paragraph(final String paragraphTitle, final String paragraphText) {
		this.paragraphTitle = paragraphTitle;
		this.paragraphText = paragraphText;
		isTerminal = false;
	}

	public String getParagraphTitle() {
		return paragraphTitle;
	}

	public String getParagraphText() {
		return paragraphText;
	}
	
	public boolean isTerminalParagraph() {
		return isTerminal;
	}
	
}
