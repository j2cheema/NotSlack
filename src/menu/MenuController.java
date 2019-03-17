/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import cps888.Client;
import chat.Chat;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aacay
 */
public class MenuController implements Initializable {
    //General variables
    Client client;
    @FXML
    private Text errMsg; 
    
    //Login Pane
    @FXML
    private TextField userField;  
    @FXML
    private Pane loginPane;  
    
    //Menu Pane
    @FXML
    private Pane menuPane;  
    @FXML
    private Text welcomeText;
    
    //Create Room Pane
    @FXML
    private Pane createRoomPane; 
    @FXML
    private TextField roomField;
    
    //Methods
    @FXML
    public void onConnect(ActionEvent event) {
        String user = userField.getText();    
        if(user.equals("") || user == null) {
            errMsg.setText("Please enter valid username.");
            errMsg.setVisible(true);            
        }
        else {
            client = new Client(user, "localhost", 5000);
            if(!client.start()) {          
                errMsg.setText("Unable to connect to server");
                errMsg.setVisible(true);
            }
            else {
                //Switch to menu displaying rooms and whatnot
                errMsg.setVisible(false);                
                loginPane.setVisible(false);    
                menuPane.setVisible(true);
                welcomeText.setText("Hi, " + user +"!");
            }
        }

    }

    @FXML
    public void onSelectCreate(Event event) {
        menuPane.setVisible(false);
        createRoomPane.setVisible(true);
    }
    
    @FXML
    public void onCreateRoom(Event event) {
        String roomName = roomField.getText();
        createRoomPane.setVisible(false);
        menuPane.setVisible(true);
        
        System.out.println(roomName);
    }  
    
    @FXML
    public void onJoin(Event event) {
        try {
            Chat chat = new Chat();
            Stage stage = new Stage();
            chat.start(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void onMessageUser(Event event) {
        
    }
    
    @FXML
    public void onClose(MouseEvent event) {
        if(client.start()) {
            client.disconnect();
        }
        Platform.exit();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}