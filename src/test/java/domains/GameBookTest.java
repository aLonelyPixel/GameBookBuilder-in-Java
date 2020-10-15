package domains;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameBookTest {

	@Test
	void test() {
		
		GameBook b = new GameBook("title");
		Paragraph p1 = new Paragraph(1, "yeah");
		Paragraph p2 = new Paragraph(2, "no");
		Paragraph p3 = new Paragraph(3, "yes");
		b.addParagraph(p1.getIndex(), p1);
		b.addParagraph(p2.getIndex(), p2);
		b.addParagraph(p3.getIndex(), p3);
		
		Paragraph pInsert = new Paragraph(2, "insertTest");
		b.addParagraph(pInsert.getIndex(), pInsert);
		
		assertEquals(4, b.getMaxParagraphIndex());
		
	}

}
