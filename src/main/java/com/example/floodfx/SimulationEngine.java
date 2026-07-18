package com.example.floodfx;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimulationEngine extends AnimationTimer {
    private final Canvas canvas;
    private final SimulationGrid grid;
    private final double cellSize;

    private int agentX = 2;
    private int agentY = 2;

    private long lastFloodUpdate = 0;
    private final long floodIntervalNanos = 800_000_000L; // 0.8 seconds

    public SimulationEngine(Canvas canvas, SimulationGrid grid, double cellSize) {
        this.canvas = canvas;
        this.grid = grid;
        this.cellSize = cellSize;

    }

    @Override
    public void handle(long now) {

        // Update simulation physics (The Flood)
        if (lastFloodUpdate == 0) lastFloodUpdate = now;
        if (now - lastFloodUpdate >= floodIntervalNanos) {
            grid.stepFlood();
            lastFloodUpdate = now;
        }


        render();
    }

    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                gc.setFill(grid.getCell(x, y).getColor());
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);

                gc.setStroke(Color.web("#E0E0E0"));
                gc.setLineWidth(0.5);
                gc.strokeRect(x * cellSize, y * cellSize, cellSize, cellSize);

            }
        }

        // Draw Player Agent
        gc.setFill(Color.CRIMSON);
        gc.fillOval(agentX * cellSize + 2, agentY * cellSize + 2, cellSize - 4, cellSize - 4);
    }
}