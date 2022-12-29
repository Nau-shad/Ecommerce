package com.example.ecommerce;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

// we cannot use for scene builder bcz we cannot fetch the data from database
//frontend without fxml
public class productPage {
    ListView<HBox>products;
    ListView<HBox> productsBySearch(String search) throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res= Main.connection.execute("Select * from product");

        while(res.next()) {
            //contains se mila jula word bhi serch ho jayega and search box agar empty rhe to bhi koi problem nhi
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {
                Label name = new Label();
                Label productId = new Label();
                Label price = new Label();
                Button buy = new Button();

                name.setMinWidth(60);
                productId.setMinWidth(60);
                price.setMinWidth(60);
                buy.setText("Buy");
                HBox productDetails = new HBox();

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("buy button clicked");
                        if (Main.emailId.equals("")) {
                            System.out.println("Please login first");
                        } else {
                            System.out.println(" You are logged in with " + Main.emailId);
                            Order order = new Order();
                            try {
                                order.placeOrder(productId.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                name.setText(res.getString("productName"));
                price.setText(res.getString("price"));
                productId.setText(res.getString("productId"));
                productDetails.getChildren().addAll(productId, name, price, buy);

                productList.add(productDetails);
            }
        }
        products.setItems(productList);
        return  products;

    }


    ListView<HBox> products() throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res= Main.connection.execute("Select * from product");

    while(res.next()) {
        Label name = new Label();
        Label productId = new Label();
        Label price = new Label();
        Button buy = new Button();

        name.setMinWidth(60);
        productId.setMinWidth(60);
        price.setMinWidth(60);
        buy.setText("Buy");
        HBox productDetails = new HBox();

        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               System.out.println("buy button clicked");
                if(Main.emailId.equals("")){
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle("Login");
                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().add(type);
                    dialog.setContentText("Please Login First");
                    dialog.showAndWait();
                }
                else{
                    System.out.println(" You are logged in with "+ Main.emailId);
                    Order order= new Order();
                    try {
                        order.placeOrder(productId.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        name.setText(res.getString("productName"));
        price.setText(res.getString("price"));
        productId.setText(res.getString("productId"));
        productDetails.getChildren().addAll(productId, name, price, buy);

        productList.add(productDetails);
    }
    products.setItems(productList);
    return  products;

    }

}
