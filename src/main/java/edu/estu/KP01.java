package edu.estu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The 0–1 knapsack problem (KP01) instance
 */
public class KP01 {
    private final long capacity;
    private final Set<Item> items;

    public KP01(long capacity, Item... items) {
        this.capacity = capacity;
        this.items = new HashSet<>(Arrays.asList(items));
    }

    public KP01(long capacity, Set<Item> items) {
        this.capacity = capacity;
        this.items = items;
    }

//    public KP01(Path path) {
//
//        try (InputStream input = Files.newInputStream(path);
//             Scanner scanner = new Scanner(input)) {
//
//            while (scanner.hasNextLong()) {
//                System.out.println(scanner.nextLong());
//
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


    @Override
    public String toString() {
        return "KP01{" +
                "capacity=" + capacity +
                ", # items=" + items.size() +
                '}';
    }

    boolean trivial() {
        return items.stream().mapToLong(Item::weight).sum() <= capacity;
    }
}
