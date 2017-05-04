package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	@FXML
	private ResourceBundle resources;
	@FXML
	private Button btnTrovaTuttiVicini;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;

	@FXML
	void doReset(ActionEvent event) {
		txtResult.setText("");
		inputParola.setText("");
		inputNumeroLettere.setText("");
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {	
       try {
    	   int numeroLettere=Integer.parseInt(inputNumeroLettere.getText());
    	   txtResult.appendText("GRAFO CREATO. PAROLE DI LUNGHEZZA "+numeroLettere+" TROVATE:\n");
    	   for(String s: model.createGraph(numeroLettere)){
			txtResult.appendText(s+"\n");}
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
			txtResult.appendText("\nINSERIRE LA LUNGHEZZA DELLE PAROLE");
		}
		}
	
	@FXML
	void doTrovaTuttiVicini(ActionEvent event){
		String word= inputParola.getText();
		try {
			txtResult.setText("TUTTE LE PAROLE VICINE A "+word+" TROVATE:\n");
			for(String s: model.trovaTuttiVicini(word)){
			txtResult.appendText(s+"\n");
			}

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}	
	}
	
	
	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		try {
			
			txtResult.appendText(model.findMaxDegree());

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		String word= inputParola.getText();
		
		try {
			txtResult.appendText("PAROLE VICINE A "+word+" TROVATE:\n");
			for(String s : model.displayNeighbours(word)){
			txtResult.appendText(s+"\n");}

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaTuttiVicini != null : "fx:id=\"btnTrovaTuttiVicini\" was not injected: check your FXML file 'Dizionario.fxml'."; 
	}
	
	Model model;

	public void setModel(Model model) {
		this.model = model;
	}
	
	
	
}