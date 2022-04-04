/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication_jamie;

import groovy.lang.PropertyValue;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sun.security.rsa.RSACore;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class TableDemoController implements Initializable {

    @FXML
    private TableView<Table1_Model> Table1;
    @FXML
    private TableColumn<Table1_Model, String> Fname;
    @FXML
    private TableColumn<Table1_Model, String> Lname;
    @FXML
    private TableColumn<Table1_Model, String> Suffix;
    @FXML
    private TableColumn<Table1_Model, String> ID;
    @FXML
    private AnchorPane date1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    void loadData() {
        try {
            Fname.setCellValueFactory(new PropertyValueFactory<>("idPersons"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("L_Name"));
            Suffix.setCellValueFactory(new PropertyValueFactory<>("Suffix"));

            ObservableList<Table1_Model> DataList = FXCollections.observableArrayList();

            DB_connection1.init();
            Connection c = DB_connection1.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM mydb.persons");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DataList.add(new Table1_Model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }

            Table1.setItems(DataList);

        } catch (SQLException ex) {
            Logger.getLogger(TableDemoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
