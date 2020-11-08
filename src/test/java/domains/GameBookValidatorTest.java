package domains;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import GameBook.MainPresentationModel;
import static org.mockito.Mockito.*;

class GameBookValidatorTest {
	
	private GameBookValidator validator = new GameBookValidator();

	@Test
	void emptyBookTest() {
		GameBook book = mock(GameBook.class);
		String s = validator.validate(book);
		
		assertEquals("Noeuds absents de toute action : aucun noeud\n"
				+ "Noeuds terminaux inaccessibles à partir du début : aucun noeud\n", s);
	}

	@Test
	void onlyOneIsolatedParagraph() {
//		Choice c1 = new Choice("c1", 2);
//		Choice c2 = new Choice("c2", 4);
//		Paragraph p1 = new Paragraph(1, "p1");
//		Paragraph p2 = new Paragraph(2, "p2");
//		Paragraph p3 = new Paragraph(3, "p3");
//		Paragraph p4 = new Paragraph(4, "p4");
//		GameBook book = new GameBook("My book");
//		MainPresentationModel mpModel = new MainPresentationModel(book);
//		mpModel.addParagraph(1, p1);
//		mpModel.addParagraph(2, p2);
//		mpModel.addParagraph(3, p3);
//		mpModel.addParagraph(4, p4);
//		mpModel.addChoice(1, c1);
//		mpModel.addChoice(2, c2);
		Paragraph p1 = new Paragraph(1, "p1");
		Choice c1 = new Choice("c1", 2);
		
		Paragraph p2 = new Paragraph(2, "p2");
		Choice c2 = new Choice("c2", 4);
		Choice c3 = new Choice("c3", 3);
		
		Paragraph p3 = new Paragraph(3, "p3");
		Choice c4 = new Choice("c4", 4);
		
		Paragraph p4 = new Paragraph(4, "p4");
		Paragraph p5 = new Paragraph(5, "p5");
		
		GameBook book = new GameBook("My book");
		MainPresentationModel mpModel = new MainPresentationModel(book);
		mpModel.addParagraph(1, p1);
		mpModel.addParagraph(2, p2);
		mpModel.addParagraph(3, p3);
		mpModel.addParagraph(4, p4);
		mpModel.addParagraph(5, p5);
		mpModel.addChoice(1, c1);
		mpModel.addChoice(2, c2);
		mpModel.addChoice(2, c3);
		mpModel.addChoice(3, c4);
		String s = validator.validate(book);
		
		assertEquals("Noeuds absents de toute action : 5 - \nNoeuds terminaux inaccessibles à partir du début : 5 - \n", s);
	}
}
