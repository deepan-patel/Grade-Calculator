package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Main extends Application {
    //Creating a var named window to make code more readable
    Stage window;
    TableView<Mark> table;

    //save all the buttons in a list
    Label grade = new Label("Grade");
    Label weight = new Label("Weight(Percent)");

    TextField newTestName = new TextField();
    TextField newWeightFactor = new TextField();
    TextField newGrade = new TextField();


    @Override
    public void start(Stage primaryStage) throws Exception{
        //write main code here
        //this make our var equal to the primary stage
        window = primaryStage;
        window.setTitle("Grade Calculator");
        window.setResizable(false);
        //Our main layout of our Mark scene
        VBox vbox = new VBox(10);


        //Lets make the table so that the user can see
        table = new TableView<>();


        //Name of each test Column
        TableColumn<Mark,String> testName = new TableColumn<>("Test name:");
        testName.setMinWidth(350);
        testName.setCellValueFactory(new PropertyValueFactory<>("testName"));

        //Name of each Weight Factor  Column
        TableColumn<Mark,Double> weightFactor = new TableColumn<>("Weight factor:");
        weightFactor.setMinWidth(350);
        weightFactor.setCellValueFactory(new PropertyValueFactory<>("weightFactor"));

        //Name of each Weight Factor  Column
        TableColumn<Mark,Double> grade = new TableColumn<>("Grade:");
        grade.setMinWidth(350);
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        //setting prompt text
        newGrade.setPromptText("Grade");
        newWeightFactor.setPromptText("Weight Factor(Percentage)");
        newTestName.setPromptText("TestName");
        //User input
        HBox userInput = new HBox();
        userInput.setPadding(new Insets(10,10,10,10));
        userInput.setSpacing(10);

        //Setting min width for each text box
        newTestName.setMinWidth(250);
        newWeightFactor.setMinWidth(250);
        newGrade.setMinWidth(250);

        //Creating button used to add midterms and remove midterms
        Button add = new Button("add");
        add.setOnAction(e -> addClick());
        Button delete = new Button("delete");
        delete.setOnAction(e -> deleteClick());
        Button calculateOverall = new Button("Calculate Overall");
        calculateOverall.setOnAction(e -> calculateOverall());

        userInput.getChildren().addAll(newTestName,newGrade,newWeightFactor,add,delete);

        table.setItems(getMarks());
        table.getColumns().addAll(testName,grade,weightFactor);

        vbox.getChildren().add(table);
        vbox.getChildren().add(userInput);
        vbox.getChildren().add(calculateOverall);
        vbox.setAlignment(Pos.CENTER);
        userInput.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox,1050,500);
        window.setScene(scene);
        window.show();


    }

    //launch args and then call on the Application library
    public static void main(String[] args) {
        launch(args);
    }

    //This function calculate the overall and display the output in an alert box
    public void calculateOverall(){
        double overAll=0,totalWeight=0,currentWeight=0,percentageEarned=0;

        ObservableList<Mark> allMarks;
        allMarks = table.getItems();
        for(Mark mark:allMarks){
            currentWeight = (mark.getWeightFactor()/100.0);
            percentageEarned += mark.getGrade()* currentWeight;
            totalWeight += currentWeight;
        }
        //convert back into percentage
        totalWeight*=100;

        //calculate overall
        overAll = (percentageEarned/totalWeight)*100;

        //helps format to 3 decimal places
        DecimalFormat df = new DecimalFormat("###.###");

        String result = "Your current average is " + df.format(overAll) + " from a total of " + df.format(totalWeight) + " completed, and " +
                "you have earned " + df.format(percentageEarned) + " percent in the course.";
        alertBox.display("Total Overall GPA",result);

    }

    //function returns true if the weight limit is reached
    public double getTotalWeight(){
        double totalWeight=0;

        ObservableList<Mark> allMarks;
        allMarks = table.getItems();

        for(Mark mark:allMarks){
            totalWeight += mark.getWeightFactor();
        }

        return totalWeight;
    }

    //getter for all the marks in the table
    public ObservableList<Mark> getMarks(){
        ObservableList<Mark> marks = FXCollections.observableArrayList();
        return marks;
    }


    //Add the user input to the current table of Marks
    public void addClick(){
        Mark newMark = new Mark();
        boolean case1 = isDouble(newGrade);
        boolean case2 = isDouble(newWeightFactor);


        if((case1) && (case2)){
            double newGradeInput = Double.parseDouble(newGrade.getText());
            double weightFactorInput = Double.parseDouble(newWeightFactor.getText());

            if(getTotalWeight() + weightFactorInput <= 100){
                newMark.setGrade(newGradeInput);
                newMark.setWeightFactor(weightFactorInput);
                newMark.setTestName(newTestName.getText());
                table.getItems().addAll(newMark);
            }
            else{
                alertBox.display("Your total weight has reached 100 percent","Your total weight has " +
                        "reached 100 percent, recheck each individual weight factor to see if you made a mistake.");
            }

        }

    }

    //Help us delete any Mark we don't need
    public void deleteClick(){
        ObservableList<Mark> selectedMarks, allMarks;
        allMarks = table.getItems();
        if(allMarks.isEmpty()==false){
            selectedMarks = table.getSelectionModel().getSelectedItems();
            selectedMarks.forEach(allMarks::remove);
        }

    }

    //Checks if the value inside the textFeild is a boolean
    public boolean isDouble(TextField input){
        try{
            //checking if the value is a double
            double val = Double.parseDouble(input.getText());
            return true;
        }catch(NumberFormatException e){
            alertBox.display("Error inputing values:","You did not enter a number value for " + input.getPromptText());

            return false;
        }
    }


}

