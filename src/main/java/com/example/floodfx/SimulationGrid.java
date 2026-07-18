package com.example.floodfx;


public class SimulationGrid {
    private final int width;
    private final int height;
    private final CellType[][] matrix;

    public SimulationGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new CellType[height][width];
        initializeMap();
    }

    private void initializeMap() {

        // Default
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    matrix[y][x] = CellType.WALL;

                } else {
                    matrix[y][x] = CellType.OPEN;

                }

            }

        }
        // Place a goal and an initial flood pocket
        matrix[height - 2][width - 2] = CellType.EXIT;
        matrix[1][1] = CellType.WATER;
    }

    public void stepFlood() {

        // Create a snapshot to prevent single-frame sweeping
        CellType[][] nextMatrix = new CellType[height][width];
        for (int y = 0; y < height; y++) {
            System.arraycopy(matrix[y], 0, nextMatrix[y], 0, width);
        }

        // Spread water to adjacent open spaces
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if (matrix[y][x] == CellType.WATER) {
                    if (matrix[y-1][x] == CellType.OPEN) nextMatrix[y-1][x] = CellType.WATER;
                    if (matrix[y+1][x] == CellType.OPEN) nextMatrix[y+1][x] = CellType.WATER;
                    if (matrix[y][x-1] == CellType.OPEN) nextMatrix[y][x-1] = CellType.WATER;
                    if (matrix[y][x+1] == CellType.OPEN) nextMatrix[y][x+1] = CellType.WATER;

                }
            }
        }

        for (int y = 0; y < height; y++) {
            System.arraycopy(nextMatrix[y], 0, matrix[y], 0, width);

        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public CellType getCell(int x, int y) { return matrix[y][x]; }
}
