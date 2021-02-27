//package domains;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import org.junit.jupiter.api.Test;
//
//import GameBook.MainPresentationModel;
//import model.Choice;
//import model.GameBook;
//import model.GraphBuilder;
//import model.Paragraph;
//
//class GraphBuilderTest {
//	
//	private GraphBuilder graphBuilder = new GraphBuilder();
//
//	@Test
//	void emptyBook() {
//		GameBook book = mock(GameBook.class);
//		when(book.isEmpty()).thenReturn(true);
//		String graph = graphBuilder.getGraph(book);
//		assertEquals("Le livre est vide", graph);
//	}
//
//	@Test
//	void normalBook() {
//		Paragraph p1 = new Paragraph(1, "p1");
//		Choice c1 = new Choice("c1", 2);
//		
//		Paragraph p2 = new Paragraph(2, "p2");
//		Choice c2 = new Choice("c2", 4);
//		Choice c3 = new Choice("c3", 3);
//		
//		Paragraph p3 = new Paragraph(3, "p3");
//		Choice c4 = new Choice("c4", 4);
//		
//		Paragraph p4 = new Paragraph(4, "p4");
//		Paragraph p5 = new Paragraph(5, "p5");
//		
//		GameBook book = new GameBook("My book");
//		MainPresentationModel mpModel = new MainPresentationModel(book);
//		mpModel.addParagraph(1, p1);
//		mpModel.addParagraph(2, p2);
//		mpModel.addParagraph(3, p3);
//		mpModel.addParagraph(4, p4);
//		mpModel.addParagraph(5, p5);
//		mpModel.addChoice(1, c1);
//		mpModel.addChoice(2, c2);
//		mpModel.addChoice(2, c3);
//		mpModel.addChoice(3, c4);
//		String graph = graphBuilder.getGraph(book);
//		
//		assertEquals("§1 -> §2 -> §3 -> §4\n" + 
//				"§1 -> §2 -> §4\n" + 
//				"§2 -> §3 -> §4\n" + 
//				"§2 -> §4\n" + 
//				"§3 -> §4\n" + 
//				"§4\n" + 
//				"§5\n", graph);
//	}
//}
