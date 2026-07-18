package com.example.floodfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int GRID_SIZE = 20;
    private static final double CELL_SIZE = 30.0;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Setup Simulation Core
        SimulationGrid grid = new SimulationGrid(GRID_SIZE, GRID_SIZE);
        Canvas canvas = new Canvas(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        SimulationEngine engine = new SimulationEngine(canvas, grid, CELL_SIZE);

        // Sidebar Controls layout
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #F4F4F4; -fx-border-color: #DCDCDC; -fx-border-width: 0 0 0 1;");

        Label titleLabel = new Label("EvacArena Control");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        ComboBox<String> botSelector = new ComboBox<>();
        botSelector.getItems().addAll("Manual Control", "PanicBot (Heuristic)", "QLearningBot", "GeneticBot");
        botSelector.getSelectionModel().selectFirst();
        botSelector.setMaxWidth(Double.MAX_VALUE);

        Button startBtn = new Button("Start Sim");
        startBtn.setMaxWidth(Double.MAX_VALUE);
        startBtn.setOnAction(e -> engine.start());

        Button stopBtn = new Button("Pause");
        stopBtn.setMaxWidth(Double.MAX_VALUE);
        stopBtn.setOnAction(e -> engine.stop());

        sidebar.getChildren().addAll(titleLabel, new Label("Select Agent Strategy:"), botSelector, startBtn, stopBtn);

        // Assembly
        root.setCenter(canvas);
        root.setRight(sidebar);

        Scene scene = new Scene(root);
        stage.setTitle("EvacArena: Dynamic AI Benchmark");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}