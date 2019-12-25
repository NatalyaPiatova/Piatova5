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
public class ProdavciController implements Initializable {
    private static final  String url = "jdbc:mysql://localhost:3306/magazin.sql";
        private static  String user = "root";      
    private static final String password = "pi159357";
    
    private static Connection con;
    private static Statement stmt;
    static ResultSet rs;
    @FXML
    private TextField Фамилия;
    @FXML
    private TextField ФИО;
    @FXML
    private TextField Адрес;
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
    private Button Output4;
    @FXML
    private TextArea Out;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // T
    }    

    @FXML
    private void handlButtonAction(ActionEvent event) {
          if (event.getSource()==ADD){
       String query = "insert into Продавцы values("+Фамилия.getText()+","+ФИО.getText()+","+Адрес.getText()+")";
       executeQuery(query);
   } 
    //кнопка просмотр вывод данные из таблицы
     if (event.getSource()==Output4){
       String query = "SELECT * From Продавцы" ;
    try{   Connection con = DriverManager.getConnection(url,user,password);
        Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
                    
                    Out.appendText(" "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"\n");
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
