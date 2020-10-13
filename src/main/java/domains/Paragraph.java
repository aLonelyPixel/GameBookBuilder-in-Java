package domains;

public class Paragraph {

	private final String paragraphTitle;
	private final String paragraphText;
	
	public Paragraph(final String paragraphTitle, final String paragraphText) {
		this.paragraphTitle = paragraphTitle;
		this.paragraphText = paragraphText;
	}

	public String getParagraphTitle() {
		return paragraphTitle;
	}

	public String getParagraphText() {
		return paragraphText;
	}
	
}
