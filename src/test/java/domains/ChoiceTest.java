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

//	@Test
//	void knowsItsIndex() {
//		choice = new Choice(4, "testing", 1);
//		
//		assertEquals(4, choice.getIndex());
//		
//		choice.setIndex(6);
//		
//		assertEquals(6, choice.getIndex());
//	}
//	
//	@Test
//	void knowsItsText() {
//		choice = new Choice(1, "testing", 1);
//		
//		assertEquals("testing", choice.getText());
//	}
//	
//	@Test
//	void setsText() {
//		choice = new Choice(6, "testing", -55);
//		choice.setText("AHA ! this is a different text!");
//		
//		assertEquals("AHA ! this is a different text!", choice.getText());
//	}
//	
//	@Test
//	void knowsItsDestParagraph() {
//		choice = new Choice(1, "testing", 5);
//		
//		assertEquals(5, choice.getDestParagraph());
//		
//		choice.setDestParagraph(8);
//		
//		assertEquals(8, choice.getDestParagraph());
//	}
//	
//	@Test
//	void indexIsNegative() {
//		choice = new Choice(-56, "testing", 5);
//		
//		assertEquals(0, choice.getIndex());
//	}
//	
//	@Test
//	void destParagraphIsNegative() {
//		choice = new Choice(6, "testing", -55);
//		
//		assertEquals(0, choice.getDestParagraph());
//	}
	
}
