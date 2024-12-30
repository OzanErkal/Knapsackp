package edu.estu;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Specify the path to the file
        Path filePath = Paths.get("resources/test.in.large");

        // Check if the file exists
        if (Files.notExists(filePath)) {
            System.err.println("File not found: " + filePath.toAbsolutePath());
            return; // Exit if the file is not found
        }

        // Load the problem instance from the file
        KP01 large = KP01.fromFile(filePath);

        System.out.println(large);

        // Read the number of items from the first line
        final int n = Integer.parseInt(readFirstLine(filePath));

        Set<Item> items;

        try (Stream<String> stream = Files.lines(filePath)) {
            // Skip the first line and then read the items
            items = stream.skip(1).limit(n).map(App::fromLine).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Read the knapsack capacity from the last line
        final long capacity = Long.parseLong(readLastLine(filePath));

        // Create the KP01 problem instance
        KP01 problem = new KP01(capacity, items);

        // Check if it's a trivial case and print the result
        System.out.println("Is this a trivial problem? " + problem.trivial());

        System.out.println(problem);

        // Get and print the greedy solution
        System.out.println("\nGreedy Solution:");
        problem.greedySolution().forEach(item -> System.out.println("Item: " + item));

        // Get and print the random solution
        System.out.println("\nRandom Solution:");
        problem.randomSolution().forEach(item -> System.out.println("Item: " + item));
    }

    static String readFirstLine(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String readLastLine(Path path) {
        String last = null;
        try (BufferedReader input = Files.newBufferedReader(path)) {
            String line;
            while ((line = input.readLine()) != null) {
                last = line;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return last;
    }

    static Item fromLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length != 3) throw new RuntimeException("Invalid line: " + line);
        return new Item(
                Long.parseLong(parts[0]),  // Item ID
                Long.parseLong(parts[1]),  // Item Profit
                Long.parseLong(parts[2])   // Item Weight
        );
    }
}
