package com.example.ecommerce;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
    void placeOrder(String productId) throws SQLException {
        ResultSet res= Main.connection.execute("Select max(orderId) as orderId from orders");
        int orderId=1;
        if(res.next()){
            orderId=res.getInt("orderId") + 1;

        }
        // its only for date Date date = new Date(Calendar.getInstance().getTime().getTime());
        Timestamp ts= new Timestamp(Calendar.getInstance().getTime().getTime());
        String query = String.format("Insert into orders values('%s','%s','%s','%s')"
        ,orderId,productId,Main.emailId,ts);
        int response= Main.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order ");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your order is placed");
            dialog.showAndWait();

        }
        else System.out.println("the order is not placed");

    }
}
