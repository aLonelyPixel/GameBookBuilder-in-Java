package control;

import view.ViewController;
import view.viewmodel.ViewModel;

public interface CanSynchronizeViewAndModel {

	void setView(ViewController view);

	void setTitle(String text);

	void addParagraph(int newIndex, String paragraphText);

	void setParagraphText(int paragraphIndex, String newText);

	void deleteParagraph(int paragraphIndex);

	void displayParagraphs();

	void displayChoices();

	void addChoice(int ownerParagraph, String choiceText, int destParagraph);

	void deleteChoice(int paragraphIndex, String choiceToDelete);

	void displayChoiceDestination(int paragraphIndex, String choiceText);

	void setChoiceText(int paragraphIndex, String oldText, String newText, int newDestParagraph);

	void displayBookValidation();

	void displayGraphValidation();

	boolean saveBook(String path);

	boolean loadBook(String path);

	boolean exportJson(String path);

	ViewModel getViewModel();

}
