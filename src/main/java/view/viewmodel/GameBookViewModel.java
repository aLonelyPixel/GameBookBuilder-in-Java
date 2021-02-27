package view.viewmodel;

import java.util.List;
import java.util.Set;

import model.GameBookInterface;
import model.GameBookValidator;
import model.GraphBuilder;

public class GameBookViewModel implements ViewModel {

	private GameBookInterface gameBookInterface;
	private GameBookValidator validator;
	private GraphBuilder graphBuilder;
	
	public GameBookViewModel(GameBookInterface gameBookInterface) {
		this.gameBookInterface = gameBookInterface;
		validator = new GameBookValidator();
		graphBuilder = new GraphBuilder();
	}

	@Override
	public String getBookTitle() {
		return gameBookInterface.getBookTitle();
	}

	@Override
	public List<String> getParagraphsList() {
		return gameBookInterface.getParagraphsList();
	}

	@Override
	public String getParagraphText(int paragraphIndex) {
		return gameBookInterface.getParagraphText(paragraphIndex);
	}

	@Override
	public List<String> getChoicesList(int paragraphIndex) {
		return gameBookInterface.getChoicesList(paragraphIndex);
	}

	@Override
	public int getChoiceDestinationParagraph(int paragraphIndex, String choiceText) {
		return gameBookInterface.getChoiceDestinationParagraph(paragraphIndex, choiceText);
	}

	@Override
	public Set<Integer> getParagraphsIndexes() {
		return gameBookInterface.getParagraphsIndexes();
	}

	@Override
	public String displayBookValidation() {
		return validator.validate(gameBookInterface);
	}

	@Override
	public String displayGraphValidation() {
		return graphBuilder.getGraph(gameBookInterface);
	}

}
