package view.viewmodel;

import java.util.List;
import java.util.Set;

public interface ViewModel {

	public String getBookTitle();

	public List<String> getParagraphsList();

	public String getParagraphText(int paragraphIndex);

	public List<String> getChoicesList(int paragraphIndex);

	public int getChoiceDestinationParagraph(int paragraphIndex, String choiceText);

	public Set<Integer> getParagraphsIndexes();

	public String displayBookValidation();

	public String displayGraphValidation();
	
}
