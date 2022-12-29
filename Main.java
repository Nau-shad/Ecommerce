package com.example.ecommerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main extends Application {
 public static databaseConnection connection;// making object
   public static Group root;
   public static String emailId;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
       emailId = "";
        connection = new databaseConnection();
//        //working on sql for code for testing
//         ResultSet res =   connection.execute("Select * from temperory");
//
//         while(res.next()){
//             int number = res.getInt("temp_number");
//             System.out.println(number);
//         }
//         int rows=connection.executeUpdate("Insert into temperory values(6)");
//        System.out.println("number of rows affected " + rows);

        root= new Group();
        Header header= new Header();
        productPage productPage = new productPage();
        AnchorPane productPane= new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutX(125);
        productPane.setLayoutY(50);

        root.getChildren().addAll(header.root,productPane);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->
        {
            try {
                connection.con.close();
                System.out.println("connection is closed");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}