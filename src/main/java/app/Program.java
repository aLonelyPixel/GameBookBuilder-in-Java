package app;

import control.CanSynchronizeViewAndModel;
import control.impl.MVCController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GameBook;
import model.saveload.CanSaveAndLoad;
import model.saveload.MySaverLoader;
import view.ViewController;

public class Program extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root;
		try {
			FXMLLoader chargeurFXML = new FXMLLoader (getClass().getResource("/MainWindow2.fxml"));
			CanSaveAndLoad csal = new MySaverLoader();
			CanSynchronizeViewAndModel mvcControl = new MVCController(new GameBook(""), csal);
			ViewController view = new ViewController(mvcControl);
			chargeurFXML.setController(view);
			mvcControl.setView(view);
			
			root = chargeurFXML.load();
			Scene scene = new Scene(root);
			stage.setTitle("Création du GameBook");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			 System.err.println(e.getStackTrace());
		}
		
	}
}
