package domains;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ChoiceTest {
	
	private Choice choice;
	
	@Test
	void equalsText() {
		String text = "a (dest. 1)";
		choice = new Choice("a (dest. 1)", 2);
		
		assertTrue(text.equals(choice.getText()));
	}

	@Test
	void knowsItsText() {
		choice = new Choice("testing", 1);
		
		assertEquals("testing", choice.getText());
	}
	
	@Test
	void setsText() {
		choice = new Choice("testing", -55);
		choice.setText("AHA ! this is a different text!");
		
		assertEquals("AHA ! this is a different text!", choice.getText());
	}
	
	@Test
	void knowsItsDestParagraph() {
		choice = new Choice("testing", 5);
		
		assertEquals(5, choice.getDestParagraph());
		
		choice.setDestParagraph(8);
		
		assertEquals(8, choice.getDestParagraph());
	}
	
	@Test
	void destParagraphIsNegative() {
		choice = new Choice("testing", -55);
		
		assertEquals(0, choice.getDestParagraph());
	}
	
	@Test
	void shiftBackDestParagraph() {
		choice = new Choice("testing", 6);
		choice.shiftBackDestParagraph();
		
		assertEquals(5, choice.getDestParagraph());
	}
	
	@Test
	void shiftBackWhenFirstParagraph() {
		choice = new Choice("testing", 1);
		choice.shiftBackDestParagraph();
		
		assertEquals(1, choice.getDestParagraph());
	}
	
	@Test
	void choiceToString() {
		choice = new Choice("testing", 6);
		
		assertEquals("testing (dest. 6)", choice.toString());
	}
}
