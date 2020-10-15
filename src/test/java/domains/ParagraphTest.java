package domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParagraphTest {

	@Test
	void test() {
		Paragraph p = new Paragraph(1, "oui");
		p.addChoice(new Choice(56, "", 1));
		p.addChoice(new Choice(43, "", 1));
		p.addChoice(new Choice(92, "", 1));
		
		assertEquals(92, p.getMaxChoiceIndex());
	}

}
