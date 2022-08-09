package BitlabBootcampCourse.bootcampspring.db;

import BitlabBootcampCourse.bootcampspring.model.Item;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Item> items = new ArrayList<>();
    private static Long id = 7L;

    static {
        items.add(new Item(1L, "Macbook Pro 2020", "8 GB RAM 256 GB SSD Intel Core i7", 1499.99));
        items.add(new Item(2L, "ASUS TUF GAMING", "16 GB RAM 512 GB SSD AMD Ryzen 5", 999.99));
        items.add(new Item(3L, "Apple Iphone 12 Pro", "6 GB RAM 128 GB Memory Super Retina XDR OLED Display", 1099.99));
        items.add(new Item(4L, "XIAOMI Redmi Note 8", "6 GB RAM 64 GB Memory Android 9 Pie", 199.99));
        items.add(new Item(5L, "XIAOMI Redmi Note 10", "8 GB RAM 128 GB Memory Android 11", 299.99));
        items.add(new Item(6L, "MSI Prestige 15", "Intel Core i7 1024 GB Memory 32 GB RAM", 1999.99));
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static Item getItem(Long id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public static void addItem(Item item) {
        item.setId(id);
        items.add(item);
        id++;
    }
}
