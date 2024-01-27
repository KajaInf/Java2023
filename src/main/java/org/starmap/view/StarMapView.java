package org.starmap.view;

import javafx.animation.PauseTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.starmap.controller.StarMapController;
import org.starmap.model.Constellation;
import org.starmap.model.Star;

import java.util.*;



public class StarMapView extends Canvas {
    private final StarMapController controller;
    private PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
    private Star currentHoveredStar = null;
    private Map<String, Color> constellationColors = new HashMap<>();
    private boolean coordinates = true;

    public StarMapView(StarMapController controller) {
        this.controller = controller;
        this.setWidth(1024); // Set canvas width
        this.setHeight(768); // Set canvas height
        drawMap();
        initializeConstellationColors();
        addMouseMotionListener();
    }

    private void initializeConstellationColors() {
        List<Constellation> constellations = controller.getConstellations();
        for (Constellation constellation : constellations) {
            int hash = constellation.getName().hashCode();
            Random rand = new Random(hash); // Use hash as a seed for random generator
            Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
            constellationColors.put(constellation.getName(), color);
        }
    }

    public void drawMap() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());
        drawStars();
        drawConstellations();
    }

    private void drawStars() {
        GraphicsContext gc = getGraphicsContext2D();
        List<Star> stars = controller.getStars();
        for (Star star : stars) {
            double brightnessScale = star.getBrightness() / 2.0;
            double starSize = 2 + (5 - brightnessScale);
            Color starColor = Color.hsb(60, 0.5, 1 - 0.2 * brightnessScale);
            drawStar(gc, star.getXPosition(), star.getYPosition(), starSize, starColor);
        }
    }

    private void drawStar(GraphicsContext gc, double x, double y, double size, Color color) {
        double[] xPoints = new double[10];
        double[] yPoints = new double[10];
        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 5 * i;
            double radius = i % 2 == 0 ? size : size / 2;
            xPoints[i] = x + radius * Math.sin(angle);
            yPoints[i] = y - radius * Math.cos(angle);
        }
        gc.setStroke(color);
        gc.strokePolyline(xPoints, yPoints, 10);
    }

    private void drawConstellations() {
        GraphicsContext gc = getGraphicsContext2D();
        List<Constellation> constellations = controller.getConstellations();

        for (Constellation constellation : constellations) {
            Color lineColor = constellationColors.getOrDefault(constellation.getName(), Color.BLUE);
            gc.setStroke(lineColor);
            gc.setLineWidth(1);
            gc.setFill(lineColor);
            gc.setFont(new Font("Arial", 14));

            List<Star> starsInConstellation = constellation.getStars();
            for (int i = 0; i < starsInConstellation.size() - 1; i++) {
                Star start = starsInConstellation.get(i);
                Star end = starsInConstellation.get(i + 1);
                gc.strokeLine(start.getXPosition(), start.getYPosition(), end.getXPosition(), end.getYPosition());
            }

            // Rysuj nazwę konstelacji obok pierwszej gwiazdy
            if (!starsInConstellation.isEmpty()) {
                Star firstStar = starsInConstellation.get(0);
                gc.fillText(constellation.getName(), firstStar.getXPosition(), firstStar.getYPosition() - 15);
            }
        }
    }

    private void addMouseMotionListener() {
        this.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            Star foundStar = null;

            List<Star> stars = controller.getStars();
            for (Star star : stars) {
                if (Math.abs(mouseX - star.getXPosition()) < 10 && Math.abs(mouseY - star.getYPosition()) < 10) {
                    foundStar = star;
                    break;
                }
            }

            if (foundStar != null && foundStar != currentHoveredStar) {
                currentHoveredStar = foundStar;
            } else if (foundStar == null && currentHoveredStar != null) {
            }
        });
    }

    private void drawStarName(Star star) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillText(star.getName(), star.getXPosition() + 10, star.getYPosition() - 10);
    }

    private void hideStarName() {
        if (currentHoveredStar != null) {
            pause.setOnFinished(e -> {
                clearCanvas();
                drawMap(); // Rysuj wszystko od nowa
            });
            pause.playFromStart();
        }
    }

    private void clearCanvas() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

    public void toggleCoordinatesVisibility(boolean coordinates) {
        this.coordinates = coordinates;
        drawMap();
    }

    private void renderCoordinates() {
        if (coordinates) {
            GraphicsContext graphics = getGraphicsContext2D();
            graphics.setStroke(Color.WHITE);
            graphics.setLineWidth(0.5);

            graphics.strokeLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

            graphics.strokeLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        }
    }

    private Star searchStarByPosition(double posX, double posY) {
        List<Star> allStars = controller.getStars();
        for (Star star : allStars) {
            double starPosX = star.getXPosition();
            double starPosY = star.getYPosition();
            double distance = Math.sqrt(Math.pow(posX - starPosX, 2) + Math.pow(posY - starPosY, 2));
            if (distance < 10) {
                return star;
            }
        }
        return null;
    }

    private void initializeStarInteraction() {
        setOnMousePressed(event -> {
            double cursorX = event.getX();
            double cursorY = event.getY();

            Star targetedStar = searchStarByPosition(cursorX, cursorY);

            if (targetedStar != null) {
                MouseButton clickType = event.getButton();

                if (clickType == MouseButton.PRIMARY) {
                    controller.beginStarMovement(targetedStar);
                } else if (clickType == MouseButton.SECONDARY) {
                    addNewStarOnCanvas(cursorX, cursorY);
                }
            }
        });

        setOnMouseDragged(event -> {
            controller.moveStar(event.getX(), event.getY());
            drawMap();
        });

        setOnMouseReleased(event -> controller.stopMovingStar());
    }


    private String fetchConstellationWithStar(Star star) {
        return controller.getConstellations().stream()
                .filter(constellation -> constellation.getStars().contains(star))
                .map(Constellation::getName)
                .findFirst()
                .orElse(null);
    }

    private void addNewStarOnCanvas(double x, double y) {
        NewStarData newStarData = promptForNewStarData();
        if (newStarData != null) {
            controller.addStarConfiguration(newStarData.name, x, y);
            if (newStarData.addToConstellation) {
                String constellationName = promptForConstellationName();
                if (constellationName != null && !constellationName.isEmpty()) {
                    addStarToConstellation(newStarData.name, constellationName);
                }
            }
            drawMap();
        }
    }

    private NewStarData promptForNewStarData() {
        TextInputDialog dialog = createStarDialog();
        dialog.showAndWait();
        String newStarName = dialog.getEditor().getText();
        boolean addToConstellation = ((CheckBox) ((GridPane) dialog.getDialogPane().getContent()).getChildren().get(2)).isSelected();

        dialog.getResult();
        return null;
    }

    private TextInputDialog createStarDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nowa Gwiazda");
        dialog.setHeaderText("Podaj nazwę nowej gwiazdy:");

        CheckBox addToConstellationCheckBox = new CheckBox("Dodaj do gwiazdozbioru");
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Nazwa:"), 0, 0);
        gridPane.add(dialog.getEditor(), 1, 0);
        gridPane.add(addToConstellationCheckBox, 0, 1, 2, 1);
        dialog.getDialogPane().setContent(gridPane);

        return dialog;
    }

    private String promptForConstellationName() {
        TextInputDialog constellationNameDialog = createConstellationDialog();
        constellationNameDialog.showAndWait();
        String constellationName = constellationNameDialog.getEditor().getText();

        constellationNameDialog.getResult();
        return null;
    }

    private TextInputDialog createConstellationDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dodaj do gwiazdozbioru");
        dialog.setHeaderText("Podaj nazwę nowego gwiazdozbioru:");
        return dialog;
    }

    private void addStarToConstellation(String starName, String constellationName) {
        Constellation constellation = null;
        for (Constellation c : controller.getConstellations()) {
            if (c.getName().equals(constellationName)) {
                constellation = c;
                break;
            }
        }

        if (constellation == null) {
            constellation = new Constellation(constellationName, new ArrayList<>());
            controller.addConstellation(constellation);
        }

        Star star = controller.getStarByName(starName);
        if (star != null) {
            constellation.addStar(star);
        }
    }

    private static class NewStarData {
        final String name;
        final boolean addToConstellation;

        NewStarData(String name, boolean addToConstellation) {
            this.name = name;
            this.addToConstellation = addToConstellation;
        }
    }

}
