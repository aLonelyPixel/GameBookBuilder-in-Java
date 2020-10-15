package domains;

public class Choice {

	private int index;
	private String text;
	private int destParagraph;
	
	public Choice(final int index, final String text, final int destParagraph) {
		this.index = index;
		this.text = text;
		if (destParagraph == Integer.MIN_VALUE) {
			this.destParagraph = 0;
		}else {
			this.destParagraph = destParagraph;
		}
		
	}

	public int getIndex() {
		return index;
	}

	public String getText() {
		return text;
	}

	public int getDestParagraph() {
		return destParagraph;
	}
	
}
