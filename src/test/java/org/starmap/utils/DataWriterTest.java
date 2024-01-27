package org.starmap.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataWriterTest {

    @TempDir
    Path tempDir;
    private Path testFilePath;

    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("test.json");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(testFilePath);
    }

    @Test
    void testSaveStarsAndConstellations() throws IOException {
        List<Star> stars = new ArrayList<>();
        stars.add(new Star("Sirius", 150, 200, 1.46));
        stars.add(new Star("Canopus", 150, 250, 0.72));

        List<Star> constellationStars = new ArrayList<>();
        constellationStars.add(stars.get(0));
        constellationStars.add(stars.get(1));

        List<Constellation> constellations = new ArrayList<>();
        constellations.add(new Constellation("Canis Major", constellationStars));

        DataWriter.saveData(testFilePath.toString(), stars, constellations);

        String content = Files.readString(testFilePath);
        assertTrue(content.contains("Sirius"));
        assertTrue(content.contains("Canopus"));
        assertTrue(content.contains("Canis Major"));

        List<Star> loadedStars = DataLoader.loadStars(testFilePath.toString());
        List<Constellation> loadedConstellations = DataLoader.loadConstellations(testFilePath.toString(), loadedStars);

        assertEquals(2, loadedStars.size());
        assertEquals(1, loadedConstellations.size());
        assertEquals("Canis Major", loadedConstellations.get(0).getName());
        assertTrue(loadedConstellations.get(0).getStars().stream().anyMatch(star -> star.getName().equals("Sirius")));
    }
}
