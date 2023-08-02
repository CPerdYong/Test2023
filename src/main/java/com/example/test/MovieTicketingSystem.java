package com.example.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MovieTicketingSystem extends Application {

    private ComboBox<String> experienceComboBox;
    private ToggleGroup showtimeToggleGroup;
    private TextField movieNameTextField;
    private TextField seatTextField;
    private RadioButton royalComboMemberSpecialRadioButton;
    private RadioButton royalPopcornRadioButton;
    private RadioButton royalPopcornComboRadioButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Movie Ticketing System");

        GridPane grid = createGridPane();
        addMovieNameField(grid);
        addExperienceComboBox(grid);
        addShowtimeRadioButtons(grid);
        addSeatTextField(grid);
        addPopcornOptions(grid);

        Button submitButton = createSubmitButton();
        submitButton.setOnAction(e -> {
            if (validateInput()) {
                String movieName = getMovieName();
                String experience = getSelectedExperience();
                String showtime = getSelectedShowtime();
                String seats = getSelectedSeats();
                double ticketPrice = calculateTicketPrice(experience, seats);
                String popcornChoice = getSelectedPopcornChoice();
                double popcornPrice = calculatePopcornPrice(popcornChoice);

                double totalPrice = ticketPrice + popcornPrice;

                showConfirmationDialog(movieName, experience, showtime, seats, popcornChoice, totalPrice);
            } else {
                showAlert("Please fill in all the required fields.");
            }

        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid, submitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        return grid;
    }

    private void addMovieNameField(GridPane grid) {
        Label movieNameLabel = new Label("Movie:");
        movieNameTextField = new TextField();
        grid.add(movieNameLabel, 0, 0,1,1);
        grid.add(movieNameTextField, 1, 0,3,1);
    }

    private void addExperienceComboBox(GridPane grid) {
        Label experienceLabel = new Label("Experience:");
        experienceComboBox = new ComboBox<>();
        experienceComboBox.getItems().addAll(
                "Beanie", "Classic", "Deluxe", "Family-Friendly",
                "Flexound", "IMAX", "Indulge", "Infinity",
                "Junior", "Onyx"
        );
        grid.add(experienceLabel, 0, 1,1,1);
        grid.add(experienceComboBox, 1, 1,1,1);
    }

    private void addShowtimeRadioButtons(GridPane grid) {
        Label showtimeLabel = new Label("Session:");
        VBox showtimeBox = new VBox(10);
        showtimeToggleGroup = new ToggleGroup();
        RadioButton am11RadioButton = createRadioButton("11:00 AM", showtimeToggleGroup);
        RadioButton pm1_30RadioButton = createRadioButton("01:30 PM", showtimeToggleGroup);
        RadioButton pm4RadioButton = createRadioButton("04:00 PM", showtimeToggleGroup);
        RadioButton pm6_30RadioButton = createRadioButton("06:30 PM", showtimeToggleGroup);
        RadioButton pm9RadioButton = createRadioButton("09:00 PM", showtimeToggleGroup);
        showtimeBox.getChildren().addAll(
                am11RadioButton, pm1_30RadioButton, pm4RadioButton, pm6_30RadioButton, pm9RadioButton
        );
        grid.add(showtimeLabel, 0, 2,1,1);
        grid.add(showtimeBox, 1, 2,1,1);
    }

    private void addSeatTextField(GridPane grid) {
        Label seatLabel = new Label("Seat(s):");
        seatTextField = new TextField();
        grid.add(seatLabel, 0, 3,1,1);
        grid.add(seatTextField, 1, 3,3,1);
    }

    private void addPopcornOptions(GridPane grid) {
        Label popcornLabel = new Label("Popcorn Options:");
        ImageView popcorn1 = new ImageView(new Image("C:\\Users\\user\\Desktop\\School\\Java2\\Test\\src\\main\\resources\\com\\example\\test\\popcorn1.png"));
        Text foodName = new Text("Royal Popcorn Combo - Member Special");
        royalComboMemberSpecialRadioButton = createRadioButton("RM 19.90");
        ImageView popcorn2 = new ImageView(new Image("C:\\Users\\user\\Desktop\\School\\Java2\\Test\\src\\main\\resources\\com\\example\\test\\popcorn2.png"));
        Text foodName1 = new Text("Royal Popcorn");
        royalPopcornRadioButton = createRadioButton("RM 17.90");
        ImageView popcorn3 = new ImageView(new Image("C:\\Users\\user\\Desktop\\School\\Java2\\Test\\src\\main\\resources\\com\\example\\test\\popcorn3.png"));
        Text foodName2 = new Text("Royal Popcorn Combo");
        royalPopcornComboRadioButton = createRadioButton("RM 21.90");

        popcorn1.setFitHeight(200);
        popcorn1.setFitWidth(270);
        popcorn2.setFitHeight(200);
        popcorn2.setFitWidth(270);
        popcorn3.setFitHeight(200);
        popcorn3.setFitWidth(270);


        grid.add(popcorn1, 1,4,1,1);
        grid.add(popcorn2, 2,4,1,1);
        grid.add(popcorn3, 3,4,1,1);
        grid.add(popcornLabel, 0, 5,1,1);
        grid.add(foodName, 1,5, 1, 1);
        grid.add(foodName1, 2,5, 1,1);
        grid.add(foodName2, 3,5,1,1);
        grid.add(royalComboMemberSpecialRadioButton, 1, 6,1,1);
        grid.add(royalPopcornRadioButton, 2, 6,1,1);
        grid.add(royalPopcornComboRadioButton, 3, 6,1,1);
    }



    private RadioButton createRadioButton(String text) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setUserData(text);
        return radioButton;
    }

    private RadioButton createRadioButton(String text, ToggleGroup toggleGroup) {
        RadioButton radioButton = createRadioButton(text);
        radioButton.setToggleGroup(toggleGroup);
        return radioButton;
    }

    private Button createSubmitButton() {
        Button submitButton = new Button("Submit");
        submitButton.setPrefWidth(100);

        return submitButton;
    }

    private boolean validateInput() {
        String movieName = getMovieName();
        String experience = getSelectedExperience();
        String showtime = getSelectedShowtime();
        String seats = getSelectedSeats();
        String popcornChoice = getSelectedPopcornChoice();

        return !movieName.isEmpty() && experience != null && showtime != null
                && !seats.isEmpty() && popcornChoice != null;
    }

    private String getSelectedPopcornChoice() {
        if (royalComboMemberSpecialRadioButton.isSelected()) {
            return royalComboMemberSpecialRadioButton.getText();
        } else if (royalPopcornRadioButton.isSelected()) {
            return royalPopcornRadioButton.getText();
        } else if (royalPopcornComboRadioButton.isSelected()) {
            return royalPopcornComboRadioButton.getText();
        }
        return null;
    }

    private String getMovieName() {
        return movieNameTextField.getText();
    }

    private String getSelectedExperience() {
        return experienceComboBox.getValue();
    }

    private String getSelectedShowtime() {
        RadioButton selectedRadioButton = (RadioButton) showtimeToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        }
        return null;
    }

    private String getSelectedSeats() {
        return seatTextField.getText();
    }

    private double calculateTicketPrice(String experience, String seats) {
        // Define the base ticket price for each experience
        switch (experience) {
            case "Beanie":
                return 19.90;
            case "Classic":
            case "Junior":
                return 15.90;
            case "Deluxe":
            case "Family-Friendly":
                return 23.90;
            case "Flexound":
            case "IMAX":
                return 25.90;
            case "Indulge":
            case "Infinity":
                return 120.00;
            case "Onyx":
                return 89.90;
            default:
                return 0.0;
        }
    }

    private double calculatePopcornPrice(String popcornChoice) {
        double popcornPrice;
        switch (popcornChoice) {
            case "Royal Popcorn Combo - Member Special":
                popcornPrice = 19.90;
                break;
            case "Royal Popcorn":
                popcornPrice = 17.90;
                break;
            case "Royal Popcorn Combo":
                popcornPrice = 21.90;
                break;
            default:
                popcornPrice = 0.0; // Invalid popcorn choice, handle this case accordingly
                break;
        }
        return popcornPrice;
    }

    private void showConfirmationDialog(String movieName, String experience, String showtime,
                                        String seats, String popcornChoice, double totalPrice) {
        String message = "You selected " + movieName + " " +
                "with " + experience + " experience at " +
                 showtime +
                " for " + seats + " seat(s) and a " + popcornChoice + ". \n" +
                "The total is: RM " + String.format("%.2f", totalPrice);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thank You!");
        alert.setHeaderText("Confirmation");
        alert.setContentText(message);
        alert.getDialogPane().setPrefSize(300, 200);
        alert.showAndWait();

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please complete the required fields. ");
        alert.setContentText(message);
        alert.showAndWait();
    }
}