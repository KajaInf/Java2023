package org.starmap.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.starmap.model.Constellation;
import org.starmap.model.Star;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataWriter {

    public static void saveData(String filePath, List<Star> stars, List<Constellation> constellations) {
        JSONObject root = new JSONObject();
        JSONArray starsJson = new JSONArray();
        JSONArray constellationsJson = new JSONArray();


        for (Star star : stars) {
            JSONObject starJson = new JSONObject();
            starJson.put("name", star.getName());
            starJson.put("xPosition", star.getXPosition());
            starJson.put("yPosition", star.getYPosition());
            starJson.put("brightness", star.getBrightness());
            starsJson.put(starJson);
        }

        for (Constellation constellation : constellations) {
            JSONObject constellationJson = new JSONObject();
            JSONArray starNames = new JSONArray();

            for (Star star : constellation.getStars()) {
                starNames.put(star.getName());
            }

            constellationJson.put("name", constellation.getName());
            constellationJson.put("stars", starNames);
            constellationsJson.put(constellationJson);
        }

        root.put("stars", starsJson);
        root.put("constellations", constellationsJson);

        try {
            Path path = Paths.get(filePath);
            Files.writeString(path, root.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
