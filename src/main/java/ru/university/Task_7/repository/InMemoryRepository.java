package ru.university.Task_7.repository;
import ru.university.Task_7.model.*;
import java.util.*;

public class InMemoryRepository {
    private final List<Disk> disks = new ArrayList<>();
    private final List<MediaType> mediaTypes = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();

    public InMemoryRepository() {
        mediaTypes.add(new MediaType(UUID.randomUUID(), "DVD"));
        mediaTypes.add(new MediaType(UUID.randomUUID(), "CD-R"));
        mediaTypes.add(new MediaType(UUID.randomUUID(), "Mini-disc"));
        categories.add(new Category(UUID.randomUUID(), "Фильмы"));
        categories.add(new Category(UUID.randomUUID(), "Музыка"));
        categories.add(new Category(UUID.randomUUID(), "ПО"));
    }

    public List<Disk> getDiscs() { return disks; }
    public List<MediaType> getMediaTypes() { return mediaTypes; }
    public List<Category> getCategories() { return categories; }

    public void addDisk(Disk disk) { disks.add(disk); }

    public boolean deleteDisk(UUID id) {
        return disks.removeIf(d -> d.getId().equals(id));
    }
}