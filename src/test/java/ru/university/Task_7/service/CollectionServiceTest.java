package ru.university.Task_7.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.university.Task_7.exception.EntityNotFoundException;
import ru.university.Task_7.model.Category;
import ru.university.Task_7.model.Disk;
import ru.university.Task_7.model.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CollectionServiceTest {

    private CollectionService service;

    @BeforeEach
    void setUp() {
        service = new CollectionService();
    }

    @Test
    void testAddDisk() {
        MediaType type = service.getMediaTypes().get(0);
        List<Category> cats = new ArrayList<>();
        cats.add(service.getCategories().get(0));

        service.addDisk(type, cats, "Тестовое описание");

        assertEquals(1, service.getAllDiscs().size());
        assertEquals("Тестовое описание", service.getAllDiscs().get(0).getDescription());
    }

    @Test
    void testSearchByDescription() {
        MediaType type = service.getMediaTypes().get(0);
        service.addDisk(type, new ArrayList<>(), "Уникальное слово");
        service.addDisk(type, new ArrayList<>(), "Просто текст");

        List<Disk> results = service.searchByDescription("Уникальное");

        assertEquals(1, results.size());
        assertTrue(results.get(0).getDescription().contains("Уникальное"));
    }

    @Test
    void testRemoveDisk() {
        MediaType type = service.getMediaTypes().get(0);
        service.addDisk(type, new ArrayList<>(), "На удаление");
        UUID id = service.getAllDiscs().get(0).getId();

        service.removeDisk(id);

        assertEquals(0, service.getAllDiscs().size());
    }

    @Test
    void testRemoveNonExistentDiskThrowsException() {
        UUID randomId = UUID.randomUUID();
        assertThrows(EntityNotFoundException.class, () -> {
            service.removeDisk(randomId);
        });
    }

    @Test
    void testAddDictionaryItem() {
        int initialSize = service.getMediaTypes().size();
        service.addMediaType("New Format");

        assertEquals(initialSize + 1, service.getMediaTypes().size());
        assertEquals("New Format", service.getMediaTypes().get(initialSize).getName());
    }
}