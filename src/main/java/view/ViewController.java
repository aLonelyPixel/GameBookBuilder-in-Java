package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import control.CanSynchronizeViewAndModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import view.viewmodel.ViewModel;

public class ViewController implements Initializable {

	private ObservableList<String> parListe;
	private ObservableList<String> actListe;
	@FXML
	private TextField bookTitle;	
	@FXML
	private TextField newParagraphIndex;
	@FXML
	private TextField newChoiceText;
	@FXML
	private TextField choiceText;
	@FXML
	private TextArea newParagraphText;
	@FXML
	private TextArea paragraphContent;
	@FXML
	private Button saveBookTitle;
	@FXML
	private Button saveParagraphContent;
	@FXML
	private Button deleteParagraph;
	@FXML
	private Button addNewChoice;
	@FXML
	private Button addParagraphButton;
	@FXML
	private Button newChoice;
	@FXML
	private Button deleteChoiceButton;
	@FXML
	private Button saveChoice;
	@FXML
	private VBox newActionBox;
	@FXML
	private ListView<String> paragraphsList;
	@FXML
	private ListView<String> actionsList;
	@FXML
	private ComboBox<Integer> newDestination;
	@FXML
	private ComboBox<Integer> possibleDestinations;
	@FXML
	private HBox choiceEditor;
	@FXML
	private MenuItem validateBook;
	@FXML
	private MenuItem getGraph;
	@FXML
	private MenuItem saveBook;
	@FXML
	private MenuItem loadBook;
	@FXML
	private MenuItem exportJson;

	private CanSynchronizeViewAndModel mvcControl;

	public ViewController(CanSynchronizeViewAndModel mvcControl) {
		this.mvcControl = mvcControl;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parListe = FXCollections.observableArrayList();
		paragraphsList.setItems(parListe);
		actListe = FXCollections.observableArrayList();
		actionsList.setItems(actListe);
	}

	@FXML
	private void setBookTitle() {
		mvcControl.setTitle(bookTitle.getText());
	}

	@FXML
	private void addParagraph() {
		int newIndex = checkNewParagraphIndex();
		mvcControl.addParagraph(newIndex, newParagraphText.getText());
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
		mvcControl.setParagraphText(paragraphsList.getSelectionModel().getSelectedIndex()+1, paragraphContent.getText());
	}

	@FXML
	private void displaySelectedParagraph() {
		mvcControl.displayParagraphs();
		displayChoices();
	}

	@FXML
	private void deleteParagraph() {
		if (paragraphsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			mvcControl.deleteParagraph(paragraphsList.getSelectionModel().getSelectedIndex()+1);
		}
	}

	@FXML
	private void displayChoices() {
		actListe.clear();
		mvcControl.displayChoices();
	}

	@FXML
	private void addNewChoice() {
		if (newDestination.getEditor().getText().matches("\\d+") && !newChoiceText.getText().isBlank()) {
			int ownerParagraph = paragraphsList.getSelectionModel().getSelectedIndex()+1;
			int destParagraph = Integer.parseInt(newDestination.getEditor().getText());
			if (destParagraph != ownerParagraph) {
				mvcControl.addChoice(ownerParagraph, newChoiceText.getText(), destParagraph);
			}
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
			mvcControl.deleteChoice(paragraphsList.getSelectionModel().getSelectedIndex()+1, choiceToDelete);
		}
	}

	@FXML
	private void displaySelectedChoice() {
		int index = actionsList.getSelectionModel().getSelectedIndex()+1;
		if (index > 0) {
			choiceEditor.setDisable(false);
			choiceText.setText(actListe.get(index-1));
			mvcControl.displayChoiceDestination(
					paragraphsList.getSelectionModel().getSelectedIndex()+1, 
					actionsList.getItems().get(actionsList.getSelectionModel().getSelectedIndex()));
		}
	}

	@FXML
	private void saveChoice() {
		if (!choiceText.getText().isBlank()) {
			String newText = choiceText.getText();
			int newDestParagraph = Integer.parseInt(possibleDestinations.getEditor().getText());
			mvcControl.setChoiceText(	paragraphsList.getSelectionModel().getSelectedIndex()+1, 
					actionsList.getItems().get(actionsList.getSelectionModel().getSelectedIndex()), 
					newText,
					newDestParagraph);
		}
	}

	@FXML
	private void displayBookValidation() throws IOException {
		mvcControl.displayBookValidation();
	}

	@FXML
	private void displayGraphValidation() throws IOException {
		mvcControl.displayGraphValidation();
	}
	
	public void update() {
		update(mvcControl.getViewModel());
	}

	private void update(ViewModel viewModel) {
		parListe.clear();
		newParagraphText.clear();
		newParagraphIndex.clear();
		parListe.addAll(viewModel.getParagraphsList());
		actListe.clear();
		newDestination.getItems().clear();
		newDestination.getItems().addAll(viewModel.getParagraphsIndexes());
	}

	public void updateParagraphContent(ViewModel viewModel) {
		if (paragraphsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			paragraphContent.setText(viewModel.getParagraphText(paragraphsList.getSelectionModel().getSelectedIndex()+1));
		}
	}

	public void updateChoicesList(ViewModel viewModel) {
		if (paragraphsList.getSelectionModel().getSelectedIndex()+1 > 0) {
			actListe.addAll(viewModel.getChoicesList(paragraphsList.getSelectionModel().getSelectedIndex()+1));
		}
	}

	public void updateChoiceDestination(ViewModel viewModel) {
		possibleDestinations.getItems().clear();
		possibleDestinations.getItems().addAll(viewModel.getParagraphsIndexes());
		possibleDestinations.setValue(viewModel.getChoiceDestinationParagraph(
				paragraphsList.getSelectionModel().getSelectedIndex()+1, 
				actionsList.getItems().get(actionsList.getSelectionModel().getSelectedIndex())));
	}

	public void showBookValidation(ViewModel viewModel){
		GraphAndValidationController validation = new GraphAndValidationController();
		try {
			validation.display("Validation du livre", viewModel.displayBookValidation());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showBookGraph(ViewModel viewModel){
		GraphAndValidationController graph = new GraphAndValidationController();
		try {
			graph.display("Graphe du livre", viewModel.displayGraphValidation());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void saveBook(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Sauver un livre-jeu");

		File f = fileChooser.showSaveDialog(paragraphsList.getScene().getWindow());
		if (f!=null) {
			boolean b = mvcControl.saveBook(f.getPath());
			if (b)
				displayAlertMessage("Information","Sauvegarde réussie", "Le livre-jeu a été correctement sauvegardé" );
			else
				displayAlertMessage("Attention","Echec de la sauvegarde", "Le livre-jeu n'a pas pu être sauvegardé" );
		}
	}

	@FXML
	public void loadBook() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Charger un livre-jeu");

		File f = fileChooser.showOpenDialog(paragraphsList.getScene().getWindow());
		if (f!=null) {
			boolean succeed = mvcControl.loadBook(f.getPath());
			if (succeed) {
				displayAlertMessage("Information","Chargement réussi", "Le livre-jeu a été correctement récupéré");
				update();
			}else {
				displayAlertMessage("Attention","Echec du chargement", "Le livre-jeu n'a pas pu être récupéré" );
			}
		}
	}

	private void displayAlertMessage(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	@FXML
	public void exportJson() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Exporter un livre-jeu en JSON");

		File f = fileChooser.showSaveDialog(paragraphsList.getScene().getWindow());
		if (f!=null) {
			boolean succeed = mvcControl.exportJson(f.getPath());
			if (succeed) {
				displayAlertMessage("Information","Export réussi", "Le livre-jeu a été correctement exporté");
				update();
			}else {
				displayAlertMessage("Attention","Echec de l'export", "Le livre-jeu n'a pas pu être exporté");
			}
		}
	}
}
