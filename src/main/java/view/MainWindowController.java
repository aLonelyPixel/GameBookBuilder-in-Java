package view;

import java.net.URL;
import java.util.ResourceBundle;
import GameBook.MainPresentationModel;
import domains.Choice;
import domains.GameBook;
import domains.Paragraph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindowController implements Initializable {

	private MainPresentationModel mpModel = new MainPresentationModel(new GameBook(""));
	private ObservableList<String> parListe;
	private ObservableList<String> actListe;
	@FXML
	private TextField bookTitle;	
	@FXML
	private Button saveBookTitle;
	@FXML
	private Button addParagraphButton;
	@FXML
	private TextArea newParagraphText;
	@FXML
	private ListView<String> paragraphsList;
	@FXML
	private ListView<String> actionsList;
	@FXML
	private TextField newParagraphIndex;
	@FXML
	private TextArea paragraphContent;
	@FXML
	private Button saveParagraphContent;
	@FXML
	private Button deleteParagraph;
	@FXML
	private Button addNewChoice;
	@FXML
	private ComboBox<Integer> newDestination;
	@FXML
	private TextField newChoiceText;
	@FXML
	private Button newChoice;
	@FXML
	private Button deleteChoiceButton;
	@FXML
	private VBox newActionBox;
	@FXML
	private TextField choiceText;
	@FXML
	private ComboBox<Integer> possibleDestinations;
	@FXML
	private Button saveChoice;
	@FXML
	private HBox choiceEditor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parListe = FXCollections.observableArrayList();
		paragraphsList.setItems(parListe);
		actListe = FXCollections.observableArrayList();
		actionsList.setItems(actListe);
	}

	@FXML
	private void setBookTitle() {
		mpModel.setTitle(bookTitle.getText());
	}

	@FXML
	private void addParagraph() {
		int newIndex = checkNewParagraphIndex();
		mpModel.addParagraph(newIndex, new Paragraph(newIndex, newParagraphText.getText()));
		refresh();
	}

	private int checkNewParagraphIndex() {
		int newIndex;
		if (newParagraphIndex.getText().isBlank()) {
			newIndex = 0;
		}else if (newParagraphIndex.getText().matches("\\d+")) {
			newIndex = Integer.parseInt(newParagraphIndex.getText());
		}else {
			newIndex = 0;
		}
		return newIndex;
	}

	@FXML
	private void saveParagraphContent() {
		mpModel.setParagraphText(paragraphsList.getSelectionModel().getSelectedIndex()+1, paragraphContent.getText());
		refresh();
	}

	private void refresh() {
		parListe.clear();
		actListe.clear();
		newDestination.getItems().clear();
		possibleDestinations.getItems().clear();
		parListe.addAll(mpModel.getParagraphsList());
		newParagraphIndex.clear();
		newParagraphText.clear();
		paragraphContent.clear();
		choiceEditor.setDisable(true);
		newDestination.getItems().addAll(mpModel.getParagraphsIndexes());
		possibleDestinations.getItems().addAll(mpModel.getParagraphsIndexes());
	}

	@FXML
	private void displaySelectedParagraph() {
		if (paragraphsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			paragraphContent.setText(mpModel.getParagraphText(paragraphsList.getSelectionModel().getSelectedIndex()+1));
		}
		displayChoices();
	}

	@FXML
	private void deleteParagraph() {
		if (paragraphsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			mpModel.deleteParagraph(paragraphsList.getSelectionModel().getSelectedIndex()+1);
		}
		refresh();
	}

	@FXML
	private void displayChoices() {
		actListe.clear();
		if (paragraphsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			actListe.addAll(mpModel.getChoicesList(paragraphsList.getSelectionModel().getSelectedIndex()+1));
		}
	}

	@FXML
	private void addNewChoice() {
		if (newDestination.getEditor().getText().matches("\\d+") && !newChoiceText.getText().isBlank()) {
			int ownerParagraph = paragraphsList.getSelectionModel().getSelectedIndex()+1;
			int destParagraph = Integer.parseInt(newDestination.getEditor().getText());
			if (destParagraph != ownerParagraph) {
				parListe.clear();
				parListe.addAll(mpModel.checkExistingParagraphs(destParagraph));
			}
			mpModel.addChoice(ownerParagraph, new Choice(newChoiceText.getText(), destParagraph));
			cleanChoices();
		}
	}

	private void cleanChoices() {
		displayChoices();
		newDestination.getEditor().clear();
		newChoiceText.clear();
		newActionBox.setDisable(true);
	}

	@FXML
	private void allowNewChoice() {
		if (paragraphsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			newActionBox.setDisable(false);
		}
	}

	@FXML
	private void deleteChoice() {
		if (actionsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			String choiceToDelete = actionsList.getItems().get(actionsList.getSelectionModel().getSelectedIndex());
			if (mpModel.containsChoice(paragraphsList.getSelectionModel().getSelectedIndex()+1, choiceToDelete)) {
				int destParagraph = mpModel.getChoiceDestinationParagraph(paragraphsList.getSelectionModel().getSelectedIndex()+1, choiceToDelete);
				mpModel.deleteChoice(paragraphsList.getSelectionModel().getSelectedIndex()+1, choiceToDelete, destParagraph);
				actListe.remove(choiceToDelete);
			}
		}
	}
	
	@FXML
	private void displaySelectedChoice() {
		int index = actionsList.getSelectionModel().getSelectedIndex()+1;
		if (index > 0) {
			choiceEditor.setDisable(false);
			choiceText.setText(actListe.get(index-1));
			possibleDestinations.setValue(mpModel.getChoiceDestinationParagraph(
					paragraphsList.getSelectionModel().getSelectedIndex()+1, 
					actionsList.getItems().get(actionsList.getSelectionModel().getSelectedIndex())));
		}
	}
	
	@FXML
	private void saveChoice() {
		if (!choiceText.getText().isBlank()) {
			String newText = choiceText.getText();
			int newDestParagraph = Integer.parseInt(possibleDestinations.getEditor().getText());
			mpModel.setChoiceText(	paragraphsList.getSelectionModel().getSelectedIndex()+1, 
									actionsList.getItems().get(actionsList.getSelectionModel().getSelectedIndex()), 
									newText,
									newDestParagraph);
			refresh();
		}
	}
}