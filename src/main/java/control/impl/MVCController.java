package control.impl;

import java.io.File;
import java.io.FileWriter;

import control.CanSynchronizeViewAndModel;
import model.GameBookInterface;
import model.makeJson.JsonBuilder;
import model.makeJson.MyJsonBuilder;
import model.saveload.CanSaveAndLoad;
import view.ViewController;
import view.viewmodel.GameBookViewModel;
import view.viewmodel.ViewModel;

public class MVCController implements CanSynchronizeViewAndModel{

	private GameBookInterface gameBook;
	private CanSaveAndLoad loadAndSave;
	private JsonBuilder jsonBuilder;
	private ViewController view;
	private ViewModel vm;
	
	public MVCController(GameBookInterface gameBookFace, CanSaveAndLoad csal) {
		gameBook = gameBookFace;	
		vm = new GameBookViewModel(gameBook);
		jsonBuilder = new MyJsonBuilder();
		loadAndSave = csal;
	}

	@Override
	public void setView(ViewController view) {
		this.view = view;
	}
	
	public ViewModel getViewModel() {
		return vm;	
	}

	public void setTitle(final String bookTitle) {
		gameBook.setBookTitle(bookTitle);
		if(view != null) {
			view.update();
		}
	}
	
	public void addParagraph(int index, final String paragraphText) {
		if (index == 0 || index > gameBook.getMaxParagraphIndex()) {
			index = gameBook.getMaxParagraphIndex()+1;
		}
		gameBook.addParagraph(index, paragraphText);
		view.update();
	}

	@Override
	public void setParagraphText(int paragraphIndex, String newText) {
		gameBook.setParagraphText(paragraphIndex, newText);
		view.update();
	}

	@Override
	public void deleteParagraph(int deadParagraphIndex) {
		gameBook.deleteParagraph(deadParagraphIndex);
		view.update();
	}

	public void displayParagraphs() {
		view.updateParagraphContent(getViewModel());
	}

	@Override
	public void displayChoices() {
		view.updateChoicesList(getViewModel());
	}

	@Override
	public void addChoice(int ownerParagraph, String choiceText, int destParagraph) {
		gameBook.addChoice(ownerParagraph, choiceText, destParagraph);
		view.update();
	}

	@Override
	public void deleteChoice(int paragraphIndex, String choiceToDelete) {
		gameBook.deleteChoice(paragraphIndex, choiceToDelete);
		view.update();
	}

	@Override
	public void displayChoiceDestination(int paragraphIndex, String choiceText) {
		view.updateChoiceDestination(getViewModel());
	}

	@Override
	public void setChoiceText(int paragraphIndex, String oldText, String newText, int newDestParagraph) {
		gameBook.setChoiceText(paragraphIndex, oldText, newText, newDestParagraph);
		view.update();
	}

	@Override
	public void displayBookValidation() {
		view.showBookValidation(getViewModel());
	}

	@Override
	public void displayGraphValidation() {
		view.showBookGraph(getViewModel());
	}

	public boolean saveBook(String path) {
		return loadAndSave.save(gameBook, path);
	}

	public boolean loadBook(String path) {
		GameBookInterface bookLoaded;
		bookLoaded = loadAndSave.load(path);
		if (bookLoaded!=null) {
			gameBook = bookLoaded;
			vm = new GameBookViewModel(gameBook);
			return true;
		}
		return false;
	}

	@Override
	public boolean exportJson(String path) {
		String json = jsonBuilder.toJson(gameBook);
		
		File output = new File(path);
		try (FileWriter writer = new FileWriter(output)){
			writer.write(json);
			writer.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
