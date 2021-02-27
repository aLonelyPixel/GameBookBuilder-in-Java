package domains;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

import model.Choice;
import model.Paragraph;

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
		when(c1.getText()).thenReturn("choice 1");
		when(c2.getText()).thenReturn("choice 2");
		when(c3.getText()).thenReturn("choice 3");
		when(c1.getDestParagraph()).thenReturn(2);
		when(c2.getDestParagraph()).thenReturn(5);
		when(c3.getDestParagraph()).thenReturn(3);
		when(c1.toString()).thenReturn("choice 1 (dest. 2)");
		when(c2.toString()).thenReturn("choice 2 (dest. 5)");
		when(c3.toString()).thenReturn("choice 3 (dest. 3)");
	}

	@Test
	void knowsItsIndex() {
		paragraph = new Paragraph(5, "text here");
		assertEquals(5, paragraph.getIndex());
		paragraph.setIndex(9);
		assertEquals(9, paragraph.getIndex());
		paragraph.setIndex(-1);
		assertEquals(9, paragraph.getIndex());
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
		
//		Choice c = mock(Choice.class);
//		paragraph.addChoice(c);
		assertEquals(4, paragraph.getChoices().size());
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
		Choice c3 = mock(Choice.class);
		Set<Choice> list = new HashSet<Choice>();
		list.add(c3);
		
		paragraph = new Paragraph(5, "text here", list);
		Iterator<Choice> it = paragraph.iterator();
		assertEquals(c3, it.next());
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
	
	@Test
	void getChoicesTexts() {
		setUpParagraph();
		List<String> list = paragraph.getChoicesTexts();
		assertEquals(3, paragraph.getChoicesTexts().size());
		assertTrue(list.contains("choice 1 (dest. 2)"));
		assertTrue(list.contains("choice 2 (dest. 5)"));
		assertTrue(list.contains("choice 3 (dest. 3)"));
	}
	
	@Test
	void setChoiceText() {
		setUpParagraph();
		Choice c4 = mock(Choice.class);
		when(c4.getText()).thenReturn("text");
//		paragraph.addChoice(c4);
		paragraph.setChoiceText(c4, "moddedText");
		
		verify(c4).setText("moddedText");
	}
	
	@Test
	void setChoiceTextByString() {
		setUpParagraph();
		Choice c4 = mock(Choice.class);
		when(c4.toString()).thenReturn("text (dest. 4)");
//		paragraph.addChoice(c4);
		paragraph.setChoiceText("text (dest. 4)", "new text", 5);
		
		verify(c4).setText("new text");
	}
	
	@Test
	void setChoiceDestination() {
		setUpParagraph();
		Choice c4 = mock(Choice.class);
		when(c4.getDestParagraph()).thenReturn(4);
//		paragraph.addChoice(c4);
		paragraph.setChoiceDestParagraph(c4, 3);
		
		verify(c4).setDestParagraph(3);
	}
	
	@Test
	void deleteChoice() {
		setUpParagraph();
		Choice c4 = mock(Choice.class);
		Choice c5 = mock(Choice.class);
		when(c4.getText()).thenReturn("choice fake");
		when(c4.getDestParagraph()).thenReturn(4);
//		paragraph.addChoice(c4);
		List<String> list = paragraph.getChoicesTexts();
		
		assertTrue(list.contains("choice fake (dest. 4)"));
		
		paragraph.deleteChoice(c4);
		paragraph.deleteChoice(c5);
		list = paragraph.getChoicesTexts();
		
		assertTrue(list.contains("choice 1 (dest. 2)"));
		assertTrue(list.contains("choice 2 (dest. 5)"));
		assertTrue(list.contains("choice 3 (dest. 3)"));
	}
	
	@Test
	void deleteChoiceByString() {
		setUpParagraph();
		Choice c4 = mock(Choice.class);
		Choice c5 = mock(Choice.class);
		when(c4.getText()).thenReturn("choice fake");
		when(c4.getDestParagraph()).thenReturn(4);
		when(c4.toString()).thenReturn("choice fake (dest. 4)");
		when(c5.getText()).thenReturn("choice fake");
		when(c5.getDestParagraph()).thenReturn(5);
		when(c5.toString()).thenReturn("choice fake (dest. 4)");

//		paragraph.addChoice(c4);
		List<String> list = paragraph.getChoicesTexts();
		
		assertTrue(list.contains("choice fake (dest. 4)"));
		
		paragraph.deleteChoice("choice fake (dest. 4)", 4);
		paragraph.deleteChoice("choice fake (dest. 4)", 5);
		paragraph.deleteChoice("choice fake (dest. 5)", 5);
		paragraph.deleteChoice("choice fake (dest. 5)", 4);
		list = paragraph.getChoicesTexts();
		
		assertTrue(list.contains("choice 1 (dest. 2)"));
		assertTrue(list.contains("choice 2 (dest. 5)"));
		assertTrue(list.contains("choice 3 (dest. 3)"));
	}
	
	@Test
	void deleteMatchingChoices() {
		setUpParagraph();
		paragraph.deleteMatchingChoices(2);
		assertEquals(2, paragraph.getChoices().size());
	}
	
	@Test
	void adjustChoicesWhenRemovingParagraph() {
		setUpParagraph();
		Choice c4 = mock(Choice.class);
		when(c4.getText()).thenReturn("choice 1");
		when(c4.getDestParagraph()).thenReturn(3);
		when(c4.toString()).thenReturn("choice 3 (dest. 3)");
//		paragraph.addChoice(c4);
		paragraph.adjustChoicesOnRemoval(2);
		paragraph.adjustChoicesOnRemoval(6);
		verify(c4).shiftBackDestParagraph();
	}
	
	@Test
	void containsChoicesToModify() {
		setUpParagraph();
		assertTrue(paragraph.containsChoicesToModify(2));
		assertFalse(paragraph.containsChoicesToModify(6));
	}
	
	@Test
	void getChoicesDestinations() {
		setUpParagraph();
		Set<Integer> set = paragraph.getChoicesDestinations();
		
		assertEquals(3, set.size());
		assertTrue(set.contains(2));
		assertTrue(set.contains(5));
		assertTrue(set.contains(3));
		assertFalse(set.contains(6));
	}
	
	@Test
	void getChoiceDestinationParagraph() {
		setUpParagraph();
		int dest = paragraph.getChoiceDestinationParagraph("choice 1 (dest. 2)");
		assertEquals(2, dest);
		dest = paragraph.getChoiceDestinationParagraph("choice 7 (dest. 2)");
		assertEquals(-1, dest);
	}
}






























