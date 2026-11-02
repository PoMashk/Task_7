package ru.university.Task_7.service;

import ru.university.Task_7.exception.EntityNotFoundException;
import ru.university.Task_7.model.*;
import ru.university.Task_7.repository.InMemoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CollectionService {
    private final InMemoryRepository repository = new InMemoryRepository();
    public List<Disk> getAllDiscs() {
        return repository.getDiscs();
    }

    public void addDisk(MediaType type, List<Category> cats, String desc) {
        Disk disk = new Disk(UUID.randomUUID(), type, cats, desc);
        repository.addDisk(disk);
    }

    public Disk findDiskById(UUID id) {
        return repository.getDiscs().stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Диск с ID " + id + " не найден."));
    }

    public void removeDisk(UUID id) {
        if (!repository.deleteDisk(id)) {
            throw new EntityNotFoundException("Диск не найден для удаления.");
        }
    }

    public List<Disk> searchByDescription(String query) {
        return repository.getDiscs().stream()
                .filter(d -> d.getDescription().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<MediaType> getMediaTypes() { return repository.getMediaTypes(); }
    public void addMediaType(String name) { repository.getMediaTypes().add(new MediaType(UUID.randomUUID(), name)); }

    public List<Category> getCategories() { return repository.getCategories(); }
    public void addCategory(String name) { repository.getCategories().add(new Category(UUID.randomUUID(), name)); }
}