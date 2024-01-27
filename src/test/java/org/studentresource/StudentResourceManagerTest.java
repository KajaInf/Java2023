package org.studentresource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentResourceManagerTest {
    private StudentResourceManager<Course> manager;

    @BeforeEach
    void setUp() {
        manager = new StudentResourceManager<>();
    }

    @Test
    void addAndRetrieveResourceTest() {
        Course course = new Course("CS101", "Introduction to Computer Science");
        manager.addResource(course);

        Course retrieved = manager.getResource("CS101");
        assertNotNull(retrieved, "Retrieved course should not be null.");
        assertEquals("Introduction to Computer Science", retrieved.getName(), "Course name should match.");
    }

    @Test
    void removeResourceTest() {
        Course course = new Course("CS102", "Data Structures");
        manager.addResource(course);
        assertNotNull(manager.getResource("CS102"), "Resource should exist before removal.");

        manager.removeResource(course);
        assertNull(manager.getResource("CS102"), "Resource should be null after removal.");
    }

    @Test
    void addDuplicateResourceTest() {
        Course course1 = new Course("CS101", "Introduction to Computer Science");
        Course course2 = new Course("CS101", "Advanced Computer Science");
        manager.addResource(course1);
        manager.addResource(course2);

        Course retrieved = manager.getResource("CS101");
        assertNotNull(retrieved, "Duplicate resource should be retrievable.");
        assertEquals("Advanced Computer Science", retrieved.getName(), "The name of the last added resource should match.");
    }

    @Test
    void addNullResourceTest() {
        assertThrows(NullPointerException.class, () -> manager.addResource(null), "Adding null resource should throw NullPointerException.");
    }

    @Test
    void getResourceNotFoundTest() {
        Course retrieved = manager.getResource("CS999");
        assertNull(retrieved, "Non-existent resource should return null.");
    }
}

