package edu.estu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The 0–1 knapsack problem is a classical NP-hard optimization problem.
 * In this problem, one is given a knapsack with an integer capacity c and a set of n items,
 * which each have an integer profit p_i and an integer weight w_i.
 * The goal is to select a subset of items to put into the knapsack such
 * that the total value is maximized and the total weight does not
 * exceed the knapsack capacity.
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

    public static KP01 fromFile(Path path) {
        try (InputStream input = Files.newInputStream(path);
             Scanner scanner = new Scanner(input)) {

            int numberOfItems = scanner.nextInt();
            Set<Item> items = new HashSet<>(numberOfItems);

            for (int i = 0; i < numberOfItems; i++) {
                items.add(new Item(scanner.nextLong(), scanner.nextLong(), scanner.nextLong()));
            }

            long capacity = scanner.nextLong();
            return new KP01(capacity, items);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    // Greedy Solution: Sort by profit-to-weight ratio, and select items that fit
    public List<Item> greedySolution() {
        // Sort items by profit/weight in descending order
        List<Item> sortedItems = items.stream()
                .sorted((i1, i2) -> Double.compare(i2.profit() / (double) i2.weight(), i1.profit() / (double) i1.weight()))
                .collect(Collectors.toList());

        List<Item> selectedItems = new ArrayList<>();
        long currentWeight = 0;

        for (Item item : sortedItems) {
            if (currentWeight + item.weight() <= capacity) {
                selectedItems.add(item);
                currentWeight += item.weight();
            }
        }

        return selectedItems;
    }

    // Random Solution: Randomly include or exclude items and ensure total weight does not exceed capacity
    public List<Item> randomSolution() {
        Random random = new Random();
        List<Item> selectedItems = new ArrayList<>();
        long currentWeight = 0;

        for (Item item : items) {
            if (random.nextBoolean() && currentWeight + item.weight() <= capacity) {
                selectedItems.add(item);
                currentWeight += item.weight();
            }
        }

        return selectedItems;
    }
}
