package com.example.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieTicketingSystem extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Movie Ticketing System");

        // Create UI elements
        Label movieLabel = new Label("Movie Name:");
        TextField movieTextField = new TextField();

        Label comboLabel = new Label("Experience:");
        ComboBox<String> comboOptions = new ComboBox<>();
        comboOptions.getItems().addAll("Beanie", "Classic", "Deluxe", "Family-Friendly",
                "Flexound", "IMAX", "Indulge", "Infinity", "Junior", "Onyx");

        Label showtimeLabel = new Label("Showtime:");
        ToggleGroup showtimeGroup = new ToggleGroup();
        RadioButton showtime1 = new RadioButton("11:00 AM");
        RadioButton showtime2 = new RadioButton("01:30 PM");
        RadioButton showtime3 = new RadioButton("04:00 PM");
        RadioButton showtime4 = new RadioButton("06:30 PM");
        RadioButton showtime5 = new RadioButton("09:00 PM");
        showtime1.setToggleGroup(showtimeGroup);
        showtime2.setToggleGroup(showtimeGroup);
        showtime3.setToggleGroup(showtimeGroup);
        showtime4.setToggleGroup(showtimeGroup);
        showtime5.setToggleGroup(showtimeGroup);

        Label seatLabel = new Label("Selected Seats (e.g., F6, F7):");
        TextField seatTextField = new TextField();

        Label popcornLabel = new Label("Popcorn Choice:");
        ToggleGroup popcornGroup = new ToggleGroup();

        RadioButton popcorn1 = new RadioButton("Royal Popcorn Combo – Member Special\n RM19.90");
        ImageView popcornImage1 = new ImageView(new Image("C:\\Users\\leeyi\\IdeaProjects\\test\\src\\main\\resources\\popcorn1.png"));
        popcornImage1.setFitHeight(100);
        popcornImage1.setFitWidth(120);
        popcorn1.setGraphic(popcornImage1);
        popcorn1.setToggleGroup(popcornGroup);

        RadioButton popcorn2 = new RadioButton("Royal Popcorn\n RM17.90");
        ImageView popcornImage2 = new ImageView(new Image("C:\\Users\\leeyi\\IdeaProjects\\test\\src\\main\\resources\\popcorn2.png"));
        popcornImage2.setFitHeight(100);
        popcornImage2.setFitWidth(120);
        popcorn2.setGraphic(popcornImage2);
        popcorn2.setToggleGroup(popcornGroup);

        RadioButton popcorn3 = new RadioButton("Royal Popcorn Combo\nRM21.90");
        ImageView popcornImage3 = new ImageView(new Image("C:\\Users\\leeyi\\IdeaProjects\\test\\src\\main\\resources\\popcorn3.png"));
        popcornImage3.setFitHeight(100);
        popcornImage3.setFitWidth(120);
        popcorn3.setGraphic(popcornImage3);
        popcorn3.setToggleGroup(popcornGroup);



        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Validate user input
            if (movieTextField.getText().isEmpty() || comboOptions.getValue() == null ||
                    seatTextField.getText().isEmpty() || popcornGroup.getSelectedToggle() == null ||
                    showtimeGroup.getSelectedToggle() == null) {
                showAlert("Please fill in all fields.");
                return;
            }

            // Calculate total ticket price
            double ticketPrice = getTicketPrice(comboOptions.getValue());
            String selectedSeats = seatTextField.getText();
            int numberOfTickets = selectedSeats.split(",").length;
            double totalTicketPrice = ticketPrice * numberOfTickets;

            // Display user input and total amount
            String userInput = "Movie: " + movieTextField.getText() + "\n" +
                    "Experience: " + comboOptions.getValue() + "\n" +
                    "Showtime: " + ((RadioButton) showtimeGroup.getSelectedToggle()).getText() + "\n" +
                    "Selected Seats: " + selectedSeats + "\n" +
                    "Popcorn Choice: " + ((RadioButton) popcornGroup.getSelectedToggle()).getText() + "\n" +
                    "Total Amount: RM " + totalTicketPrice;

            showAlert(userInput);
        });

        // Create grid layout and add UI elements
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(movieLabel, 0, 0);
        grid.add(movieTextField, 1, 0);
        grid.add(comboLabel, 0, 1);
        grid.add(comboOptions, 1, 1);
        grid.add(showtimeLabel, 0, 2);
        grid.add(showtime1, 1, 2);
        grid.add(showtime2, 1, 3);
        grid.add(showtime3, 1, 4);
        grid.add(showtime4, 1, 5);
        grid.add(showtime5, 1, 6);
        grid.add(seatLabel, 0, 7);
        grid.add(seatTextField, 1, 7);
        grid.add(popcornLabel, 0, 8);
        grid.add(popcorn1, 1, 8);
        grid.add(popcorn2, 1, 9);
        grid.add(popcorn3, 1, 10);
        grid.add(submitButton, 1, 11);

        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to get ticket price based on experience
    private double getTicketPrice(String experience) {
        switch (experience) {
            case "Beanie":
                return 19.90;
            case "Classic":
            case "Junior":
                return 15.90;
            case "Deluxe":
            case "Family-Friendly":
            case "Flexound":
            case "IMAX":
                return 23.90;
            case "Indulge":
            case "Infinity":
                return 120.00;
            case "Onyx":
                return 89.90;
            default:
                return 0.0;
        }
    }

    // Helper method to display an alert
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Movie Ticketing System");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}