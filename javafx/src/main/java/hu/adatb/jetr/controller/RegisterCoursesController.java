package hu.adatb.jetr.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hu.adatb.jetr.model.bean.Kurzus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegisterCoursesController implements Initializable{
	@FXML
	private TableView<Kurzus> tableView;
	@FXML
	private TableColumn<Kurzus, String> kod;
	@FXML
	private TableColumn<Kurzus, String> kurzus;
	@FXML
	private TableColumn<Kurzus, String> oktato;
	@FXML
	private TableColumn<Kurzus, Integer> kredit;
	@FXML
	private TableColumn<Kurzus, Boolean> valaszt;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kod.setCellValueFactory(new PropertyValueFactory<Kurzus, String>("kod"));
		kurzus.setCellValueFactory(new PropertyValueFactory<Kurzus, String>("nev"));
		oktato.setCellValueFactory(new PropertyValueFactory<Kurzus, String>("tanar"));
		kredit.setCellValueFactory(new PropertyValueFactory<Kurzus, Integer>("kredit"));
		valaszt.setCellFactory(p -> new CheckBoxTableCell<>());
		tableView.getItems().setAll( MainController.getCourses());

	}

}
