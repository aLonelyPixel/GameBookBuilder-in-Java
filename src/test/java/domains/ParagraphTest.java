package domains;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ParagraphTest {
	
	private Paragraph paragraph;
	
	private void setUpParagraph() {
		Choice c1 = mock(Choice.class);
		Choice c2 = mock(Choice.class);
		Choice c3 = mock(Choice.class);
		Set<Choice> list = new HashSet<Choice>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		paragraph = new Paragraph(5, "text here", list);
		when(c1.getIndex()).thenReturn(1);
		when(c2.getIndex()).thenReturn(7);
		when(c3.getIndex()).thenReturn(2);
	}

	@Test
	void knowsItsIndex() {
		paragraph = new Paragraph(5, "text here");
		assertEquals(5, paragraph.getIndex());
	}
	
	@Test
	void knowsItsText() {
		setUpParagraph();
		assertEquals("text here", paragraph.getParagraphText());
		
		paragraph.setParagraphText("aaaaaa");
		assertEquals("aaaaaa", paragraph.getParagraphText());
	}

	@Test
	void testIsTerminal() {
		setUpParagraph();
		assertEquals(false, paragraph.isTerminalParagraph());
		Paragraph paragraph2 = new Paragraph(5, "text here");
		assertEquals(true, paragraph2.isTerminalParagraph());
	}
	
	@Test
	void addChoiceIncreasesList() {
		setUpParagraph();
		assertEquals(3, paragraph.getChoices().size());
		
		Choice c = mock(Choice.class);
		paragraph.addChoice(c);
		assertEquals(4, paragraph.getChoices().size());
	}
	
	@Test
	void knowsMaxChoiceIndex() {
		setUpParagraph();
		assertEquals(7, paragraph.getMaxChoiceIndex());
	}
	
	@Test
	void returnsChoicesSet() {
		Choice c1 = mock(Choice.class);
		Choice c2 = mock(Choice.class);
		Choice c3 = mock(Choice.class);
		Set<Choice> list = new HashSet<Choice>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		
		paragraph = new Paragraph(5, "text here", list);
		
		assertEquals(list, paragraph.getChoices());
	}
	
	@Test
	void choiceIteratorOnNumber() {
		Choice c1 = mock(Choice.class);
		Choice c2 = mock(Choice.class);
		Choice c3 = mock(Choice.class);
		Set<Choice> list = new HashSet<Choice>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		
		paragraph = new Paragraph(5, "text here", list);
		Iterator<Choice> it = paragraph.iterator();
		
		for (int i = 0; i < 3; i++) {
			assertTrue(it.hasNext());
			it.next();
		}
		assertFalse(it.hasNext());
	}
	
	@Test
	void choiceIteratorOnElements() {
//		Choice c1 = mock(Choice.class);
//		Choice c2 = mock(Choice.class);
		Choice c3 = mock(Choice.class);
		Set<Choice> list = new HashSet<Choice>();
		list.add(c3);
//		list.add(c2);
//		list.add(c3);
		
		paragraph = new Paragraph(5, "text here", list);
		Iterator<Choice> it = paragraph.iterator();
		assertEquals(c3, it.next());
//		assertEquals(c2, it.next());
//		assertEquals(c1, it.next()); //Wtf ?!?
	}
	
	@Test
	void movesChoicesUp() {
		Choice c1 = mock(Choice.class);
		Choice c2 = mock(Choice.class);
		Choice c3 = mock(Choice.class);
		Set<Choice> list = new HashSet<Choice>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		paragraph = new Paragraph(5, "text here", list);
		
		when(c1.getDestParagraph()).thenReturn(1);
		when(c2.getDestParagraph()).thenReturn(2);
		when(c3.getDestParagraph()).thenReturn(3);
		
		paragraph.moveChoicesUp(2);
		
		verify(c1, times(0)).setDestParagraph(2);
		verify(c2).setDestParagraph(3);
		verify(c3).setDestParagraph(4);
	}
	
	
}
