package domains;

public class Choice {

//	private int index;
	private String text;
	private int destParagraph;
	
	/**
	 * Construit un objet Choice avec les données en paramètre
	 * @param text
	 * @param destParagraph
	 */
	public Choice(final String text, final int destParagraph) {
//		this.index = (index < 0) ? 0 : index;
		this.text = text;
		this.destParagraph = (destParagraph < 0) ? 0 : destParagraph;
	}

//	public void setIndex(int index) {
//		this.index = index;
//	}

//	public int getIndex() {
//		return index;
//	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public int getDestParagraph() {
		return destParagraph;
	}

	public void setDestParagraph(int destParagraph) {
		this.destParagraph = destParagraph;
	}

	/**
	 * Shifts back once (-1) the destination paragraph
	 */
	public void shiftBackDestParagraph() {
		if (this.destParagraph > 1) {
			this.destParagraph--;
		}
	}

	@Override
	/**
	 * Formats the Choice in a String
	 */
	public String toString() {
		return this.text + " (dest. " + destParagraph + ")";
	}
}
