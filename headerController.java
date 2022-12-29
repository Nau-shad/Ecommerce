package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class headerController {
    //not using constructor bcz cant accessed the fxml files

    @FXML
    public void initialize(){
        if(!Main.emailId.equals("")){
            loginButton.setOpacity(0);
            email.setText(Main.emailId);
        }

    }
    @FXML
    Button loginButton,logOutButton;
    @FXML
    Label email;
    @FXML
    TextField searchText;
    @FXML
    public void login(MouseEvent e) throws IOException {

        AnchorPane loginpage = FXMLLoader.load((getClass().getResource("loginpage.fxml")));
         Main.root.getChildren().add(loginpage);

    }
    @FXML

    public void search(MouseEvent e) throws IOException, SQLException {
        productPage productPage = new productPage();

        Header header= new Header();

        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.productsBySearch(searchText.getText()));
        productPane.setLayoutX(125);
        productPane.setLayoutY(50);
        Main.root.getChildren().clear();
        Main.root.getChildren().addAll(header.root,productPane);

    }

@FXML
    public  void logOutAppear(MouseEvent e){
        if(logOutButton.getOpacity()==0){
            logOutButton.setOpacity(1);
        }
        else{
            logOutButton.setOpacity(0);
        }
}
@FXML
    public void logOut(MouseEvent e) throws IOException {
        if(logOutButton.getOpacity()==1) {
            Main.emailId = "";
            logOutButton.setOpacity(0);
            Header header = new Header();
            Main.root.getChildren().add(header.root);
        }
}
}
