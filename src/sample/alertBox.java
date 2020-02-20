package sample;

import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.String;

public class alertBox {
    public static void display(String title,String message){
        Stage window = new Stage();
        window.setResizable(false);
        //stops the interaction between other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);


        Label label = new Label(message);

        Button closeButton = new Button("Okay");
        closeButton.setOnAction(e-> window.close());

        //layout structure
        VBox vbox = new VBox(10);

        vbox.getChildren().addAll(label,closeButton);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox,800,250);
        window.setScene(scene);

        //This will show until it is closed
        window.showAndWait();

    }
}
