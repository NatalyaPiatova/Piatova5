/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication12;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MagazinController implements Initializable {
      private static final  String url = "jdbc:mysql://localhost:3306/magazin.sql";
        private static  String user = "root";      
    private static final String password = "pi159357";
    
    private static Connection con;
    private static Statement stmt;
    static ResultSet rs;
    @FXML
    private Button ADD;
    @FXML
    private Button EXIT;
         public void executeQuery(String query) {
    	Connection conn = getConnection();
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
      public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection(url,user,password);
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }


 @FXML
    private TextField Счет;
  @FXML
    private TextField Имя;
  @FXML
    private TextField Колво;
  @FXML
    private TextField Цена;
  @FXML
    private TextField Код;
    @FXML
    private Button Output5;
    @FXML
    private TextArea Out;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlButtonAction(ActionEvent event) {
          if (event.getSource()==ADD){
       String query = "insert into Repair values("+Счет.getText()+","+Имя.getText()+","+Колво.getText()+","+Цена.getText()+","+Код.getText()+")";
       executeQuery(query);
   } 
            if (event.getSource()==Output5){
     String query = "SELECT МАГАЗИН.ID,МАГАЗИН.TOVAR_ID,МАГАЗИН.KOLVO_ID, ТОВАРЫ.`NAMETOVAR` as `NAMETOVAR`, МАГАЗИН.SELLER_ID FROM МАГАЗИН INNER JOIN ТОВАРЫ ON (МАГАЗИН.`PRICE_ID`= ТОВАРЫ.`PRICE_ID`)";
    try{   Connection con = DriverManager.getConnection(url,user,password);
        Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
                    
                    Out.appendText(" "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"  "+rs.getString(5)+"\n");
                    Out.setEditable(false);
                }
      
    } 
    catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } 
            }
        
        if (event.getSource()==EXIT){
        
                         Platform.exit();
        
        
    }  
        
        
    }
    
}
