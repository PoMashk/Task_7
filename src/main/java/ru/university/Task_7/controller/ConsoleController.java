package ru.university.Task_7.controller;

import ru.university.Task_7.model.*;
import ru.university.Task_7.service.CollectionService;

import java.util.*;

public class ConsoleController {
    private final CollectionService service = new CollectionService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== УПРАВЛЕНИЕ КОЛЛЕКЦИЕЙ ДИСКОВ ===");
            System.out.println("1. Список | 2. Добавить | 3. Редактировать | 4. Удалить | 5. Поиск | 6. Словари | 0. Выход");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> showAll();
                    case "2" -> addDisk();
                    case "3" -> editDisk();
                    case "4" -> deleteDisk();
                    case "5" -> search();
                    case "6" -> manageDictionaries();
                    case "0" -> System.exit(0);
                    default -> System.out.println("Неверный ввод.");
                }
            } catch (Exception e) {
                System.out.println("ОШИБКА: " + e.getMessage());
            }
        }
    }

    private void showAll() {
        List<Disk> discs = service.getAllDiscs();
        if (discs.isEmpty()) System.out.println("Коллекция пуста.");
        else discs.forEach(System.out::println);
    }

    private void addDisk() {
        MediaType type = selectType();
        List<Category> cats = selectCategories();
        System.out.print("Описание: ");
        String desc = scanner.nextLine();

        service.addDisk(type, cats, desc);
        System.out.println("Диск добавлен.");
    }

    private void editDisk() {
        System.out.print("Введите UUID диска: ");
        UUID id = UUID.fromString(scanner.nextLine());
        Disk disk = service.findDiskById(id);

        System.out.println("1. Изменить описание | 2. Изменить тип | 3. Изменить категории");
        String c = scanner.nextLine();
        switch (c) {
            case "1" -> {
                System.out.print("Новое описание: ");
                disk.setDescription(scanner.nextLine());
            }
            case "2" -> disk.setMediaType(selectType());
            case "3" -> disk.setCategories(selectCategories());
        }
        System.out.println("Обновлено.");
    }

    private void deleteDisk() {
        System.out.print("Введите UUID: ");
        service.removeDisk(UUID.fromString(scanner.nextLine()));
        System.out.println("Удалено.");
    }

    private void search() {
        System.out.print("Поиск в описании: ");
        service.searchByDescription(scanner.nextLine()).forEach(System.out::println);
    }

    private void manageDictionaries() {
        System.out.println("1. Добавить тип носителя | 2. Добавить категорию");
        String choice = scanner.nextLine();
        System.out.print("Название: ");
        String name = scanner.nextLine();
        if (choice.equals("1")) service.addMediaType(name);
        else if (choice.equals("2")) service.addCategory(name);
    }

    private MediaType selectType() {
        List<MediaType> types = service.getMediaTypes();
        for (int i = 0; i < types.size(); i++) System.out.println(i + ". " + types.get(i).getName());
        System.out.print("Выберите номер типа: ");
        return types.get(Integer.parseInt(scanner.nextLine()));
    }

    private List<Category> selectCategories() {
        List<Category> selected = new ArrayList<>();
        List<Category> all = service.getCategories();
        while (true) {
            for (int i = 0; i < all.size(); i++) System.out.println(i + ". " + all.get(i).getName());
            System.out.print("Выберите категорию (№) или 'done': ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) break;
            selected.add(all.get(Integer.parseInt(input)));
        }
        return selected;
    }
}