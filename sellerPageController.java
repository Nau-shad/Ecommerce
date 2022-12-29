package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerPageController {

    @FXML
    TextField name,price,sellerId;

    @FXML
    public void addProduct(MouseEvent e) throws SQLException {
        int productId=1;
        ResultSet response2 = Main.connection.execute("select max(productId) as productId from product;");

        if(response2.next()){
            productId=response2.getInt("productId") + 1;
        }

        String query = String.format("Insert Into product values('%s','%s','%s','%s')", productId,name.getText(),price.getText(),sellerId.getText());

        int response = Main.connection.executeUpdate(query);
        if(response>0){
            System.out.println("new product is added");
        }

    }
}
