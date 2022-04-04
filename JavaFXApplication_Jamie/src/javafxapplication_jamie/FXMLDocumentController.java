/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication_jamie;

import groovy.lang.PropertyValue;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button jamie1;
    @FXML
    private Button jamie2;
    @FXML
    private TextField textName;
    @FXML
    private Button PrintData;
    @FXML
    private ComboBox<String> ComboJamie;
    @FXML
    private ComboBox<String> ComboEduardo;
    @FXML
    private TableView<ModelTable> Table1;
    @FXML
    private TableColumn<ModelTable, String> C_Fname;
    @FXML
    private TableColumn<ModelTable, String> C_Middle_name;
    @FXML
    private TableColumn<ModelTable, String> C_lastname;
    Stage TableViewStage = new Stage();

    public void LoadData() {

        DB_connection1.init();

        Connection c = DB_connection1.getConnection();

        PreparedStatement ps;
        try {
            ps = c.prepareStatement("SELECT * FROM mydb.persons");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableData.add(new ModelTable(rs.getString(1), rs.getString(2), rs.getString(3)));
                System.out.println(rs.getString(1));
            }

            Table1.setItems(tableData);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    ObservableList<ModelTable> tableData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initializeing.....");

        C_Fname.setCellValueFactory(new PropertyValueFactory<>("FName"));
        C_Middle_name.setCellValueFactory(new PropertyValueFactory<>("MName"));
        C_lastname.setCellValueFactory(new PropertyValueFactory<>("FName"));
        LoadData();
        Table1.setItems(tableData);
        populateData();
        LoadEduardo();
        ComboJamie.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });

        ComboEduardo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                OnChange();
            }
        });

    }

    private void PrintJJamie(ActionEvent event) {
        OnChange();
    }

    private void PrintJamie2(ActionEvent event) {
        System.out.println(textName.getText());
    }

    void PrintData(String Id) {
        try {
            JasperReport JR;
            JasperPrint JP;
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // String Q="%"+this.txtSearch.getText() +"%";
            // String Source="C:\\Users\\JAMIEXXX3\\Documents\\NetBeansProjects\\Phonelist\\src\\Forms\\report1.jrxml";
            JasperDesign Jd = JRXmlLoader.load(System.getProperty("user.dir") + "\\\\reports\\\\Jamie.jrxml");
            String Location = System.getProperty("user.dir") + "\\\\reports\\\\";

            String SQL = "SELECT * FROM dcaa_databse.student_info where id='" + Id + "'";

            JRDesignQuery JQ = new JRDesignQuery();
            JQ.setText(SQL);
            Jd.setQuery(JQ);
            JR = JasperCompileManager.compileReport(Jd);

            JP = JasperFillManager.fillReport(JR, null, DB_connection1.getConnection());

            JasperViewer.viewReport(JP, false);
        } catch (JRException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void PrintAta_Action(ActionEvent event) {
        String Id = textName.getText();

        PrintData(Id);

    }

    @FXML
    private void Show_window2(ActionEvent event) {
        System.out.println("frame2");
        try {
            System.out.println("frame 2");
            Parent root = FXMLLoader.load(getClass().getResource("FXMLFrame2.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateData() {
        ObservableList<String> data = FXCollections.observableArrayList();

        data.add("Jamie");
        data.add("Jamie2");
        data.add("Jamie");
        data.add("Jamie2");
        data.add("Jamie");
        data.add("Jamie2");
        data.add("Jamie");
        data.add("Jamie2");
        ComboJamie.setItems(data);
        ComboJamie.setValue("Select Jamie here");

    }

    void LoadEduardo() {
        ObservableList<String> dataEduardo = FXCollections.observableArrayList();
        dataEduardo.addAll("Eduardo1", "Eduardo2");
        ComboEduardo.setItems(dataEduardo);
    }

    void OnChange() {
        ObservableList<String> dataJamie = FXCollections.observableArrayList();
        ObservableList<String> dataJamie2 = FXCollections.observableArrayList();

        dataJamie.addAll("Jamie1", "Jamie2", "JAmie3");
        dataJamie2.addAll("Jamie1111", "Jamie22222", "JAmie3222");

        if (ComboEduardo.getValue().equals("Eduardo1")) {
            ComboJamie.setItems(dataJamie);
        } else if (ComboEduardo.getValue().equals("Eduardo2")) {
            ComboJamie.setItems(dataJamie2);
        }

    }

    @FXML
    private void PrintJJamie(ContextMenuEvent event) {
    }

    @FXML

    private void ViewTableForm(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("TableDemo.fxml"));
            Scene scene = new Scene(root);

            TableViewStage.setScene(scene);
            TableViewStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
