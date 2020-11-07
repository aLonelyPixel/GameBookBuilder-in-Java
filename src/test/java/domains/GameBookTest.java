package domains;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBookTest {
	
	private GameBook game;
	
	@BeforeEach
	private void setUp() {
		game = new GameBook("My book");
	}
	
	@Test
	void knowsItsTitle() {
		assertEquals("My book", game.getBookTitle());
	}
	
	@Test
	void knowsItsEmpty() {
		assertTrue(game.isEmpty());
	}

	@Test
	void getsCorrectParagraph() {
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		
		game.addParagraph(1, p1);
		game.addParagraph(2, p2);
		
		assertEquals(p1, game.getParagraph(1));
		assertEquals(p2, game.getParagraph(2));
		assertEquals(null, game.getParagraph(3));
	}
	
	@Test
	void knowsItsNumberOfParagraphs() {
		assertEquals(0,  game.getParagraphNumber());
		
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		Paragraph p3 = mock(Paragraph.class);
		
		game.addParagraph(1, p1);
		game.addParagraph(2, p2);
		game.addParagraph(3, p3);
		assertEquals(3, game.getParagraphNumber());
	}
	
	@Test
	void returnsParagraphText() {
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		Paragraph p3 = mock(Paragraph.class);
		
		when(p1.getParagraphText()).thenReturn("sample text");
		when(p2.getParagraphText()).thenReturn("");
		when(p3.getParagraphText()).thenReturn("9");
		
		game.addParagraph(1, p1);
		game.addParagraph(2, p2);
		game.addParagraph(3, p3);
		
		assertEquals("sample text", game.getParagraphText(1));
		assertEquals("", game.getParagraphText(2));
		assertEquals("9", game.getParagraphText(3));
	}
	
	@Test
	void getsChoiceCollection() {
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		
		game.addParagraph(1, p1);
		game.addParagraph(2, p2);
		game.getChoices(2);
		
		verify(p2).getChoices();
	}
	
	@Test
	void returnsMaxParagraphIndex() {
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		Paragraph p3 = mock(Paragraph.class);
		game.addParagraph(1, p1);
		game.addParagraph(365, p2);
		game.addParagraph(5, p3);
		
		assertEquals(365, game.getMaxParagraphIndex());
	}
	
	@Test
	void returnZeroParagraphsOnEmptyBook() {
		assertEquals(0, game.getMaxParagraphIndex());
	}
	
	@Test
	void addExistingParagraphIndex() {
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		Paragraph p3 = mock(Paragraph.class);
		Paragraph p4 = mock(Paragraph.class);
		
		when(p1.getParagraphText()).thenReturn("a");
		when(p2.getParagraphText()).thenReturn("b");
		when(p3.getParagraphText()).thenReturn("c");
		when(p4.getParagraphText()).thenReturn("d");
		when(p1.getIndex()).thenReturn(1);
		when(p2.getIndex()).thenReturn(2);
		when(p3.getIndex()).thenReturn(3);
		when(p4.getIndex()).thenReturn(2);
		
		game.addParagraph(1, p1);
		game.addParagraph(2, p2);
		game.addParagraph(3, p4);
		game.addParagraph(2, p3);

		verify(p1).moveChoicesUp(2);
	}
	
	@Test
	void addExistingButEmptyParagraph() {
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		Paragraph p3 = mock(Paragraph.class);
		
		when(p1.getParagraphText()).thenReturn("a");
		when(p2.getParagraphText()).thenReturn("todo - ");
		when(p3.getParagraphText()).thenReturn("c");
		
		game.addParagraph(1, p1);
		game.addParagraph(2, p2);
		game.addParagraph(2, p3);

		verify(p2).getParagraphText();
	}
	
	@Test
	void addChoiceToParagraph() {
		Choice c1 = mock(Choice.class);
		Paragraph p1 = mock(Paragraph.class);
		Paragraph p2 = mock(Paragraph.class);
		
		game.addParagraph(1, p1);
		game.addParagraph(2, p2);
		game.addChoice(1, c1);

		verify(p1).addChoice(c1);
	}
	
	@Test
	void paragraphIterator() {
		Paragraph p1 = mock(Paragraph.class);
		game.addParagraph(1, p1);
		Iterator<Paragraph> it = game.iterator();

		assertTrue(it.hasNext());
		it.next();
		assertFalse(it.hasNext());
	}
	
	@Test
	void choiceIteratorForAParagraph() {
		Paragraph p1 = mock(Paragraph.class);
		game.addParagraph(1, p1);
		@SuppressWarnings("unused")
		Iterator<Choice> it = game.choiceIterator(1);
		
		verify(p1).iterator();
	}
	
	@Test
	void bookContainsParagraph() {
		Paragraph p1 = mock(Paragraph.class);
		game.addParagraph(1, p1);

		assertTrue(game.containsParagraph(1));
		assertFalse(game.containsParagraph(2));
	}
	
//	@Test
//	void getMaxChoiceForAParagraph() {
//		Paragraph p1 = mock(Paragraph.class);
//		game.addParagraph(1, p1);
//		game.getMaxChoiceIndexForParagraph(1);
//
//		verify(p1).getMaxChoiceIndex();
//	}
	
	@Test
	void setsNewTextToParagraph() {
		Paragraph p1 = mock(Paragraph.class);
		game.addParagraph(1, p1);
		game.setParagraphText(1, "hello there");
		game.setParagraphText(2, "hohoho");
		
		verify(p1).setParagraphText("hello there");
	}
	
	@Test
	void equalsText() {
		String text = "a (dest. 1)";
		GameBook game = new GameBook("test");
		Paragraph p = new Paragraph(1, "yes");
		Choice c = new Choice("a", 1);
		game.addParagraph(1, p);
		p.addChoice(c);
		System.out.println(game.getChoicesList(1) + "\n " + text);
		System.out.println(text.substring(text.length()-2, text.length()-1));

		assertTrue(game.containsChoice(1, text));
		
	}














}
