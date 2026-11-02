package ru.university.Task_7.model;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Disk {
    private final UUID id;
    private MediaType mediaType;
    private List<Category> categories;
    private String description;

    public Disk(UUID id, MediaType mediaType, List<Category> categories, String description) {
        this.id = id;
        this.mediaType = mediaType;
        this.categories = categories;
        this.description = description;
    }

    public UUID getId() { return id; }
    public MediaType getMediaType() { return mediaType; }
    public void setMediaType(MediaType mediaType) { this.mediaType = mediaType; }
    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        String cats = categories.stream().map(Category::getName).collect(Collectors.joining(", "));
        return String.format("Диск: %s%nТип: %s | Категории: [%s] | Описание: %s",
                id, mediaType.getName(), cats, description);
    }
}