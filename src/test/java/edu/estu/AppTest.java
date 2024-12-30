import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    public static void main(String[] args) {

        System.out.println("Is trivial case? "+problem.isTrivialCase());
        try {
            KnapsackProblem problem = parseKnapsackFile("resources/test.in");
            System.out.println(problem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        testGenerateGreedySolution();
        testGenerateRandomSolution();

    }
    static Item item1 = new Item(1, 100, 5);
    static Item item2 = new Item(2, 200, 10);
    static Item item3 = new Item(3, 150, 15);
    static KnapsackProblem problem = new KnapsackProblem(Arrays.asList(item1, item2, item3), 20);

        public static void testGenerateRandomSolution() {


            // Generate a random solution
            List<Item> randomSolution = problem.generateRandomSolution();

            // Check that the total weight of the selected items does not exceed the capacity
            int totalWeight = randomSolution.stream().mapToInt(Item::getWeight).sum();
            assertTrue(totalWeight <= problem.getCapacity(), "Total weight should be less than or equal to the capacity");

            // Optionally, print the random solution
            System.out.println("Random Solution: " + randomSolution);
        }

        public static void testGenerateGreedySolution() {

            // Generate a greedy solution
            List<Item> greedySolution = problem.generateGreedySolution();

            // Check that the total weight of the selected items does not exceed the capacity
            int totalWeight = greedySolution.stream().mapToInt(Item::getWeight).sum();
            assertTrue(totalWeight <= problem.getCapacity(), "Total weight should be less than or equal to the capacity");

            // Optionally, print the greedy solution
            System.out.println("Greedy Solution: " + greedySolution);
        }

    public static KnapsackProblem parseKnapsackFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // Read the number of items
        int n = Integer.parseInt(reader.readLine().trim());
        List<Item> items = new ArrayList<>();

        // Read each item
        for (int i = 0; i < n; i++) {
            String[] itemDetails = reader.readLine().trim().split("\\s+");
            int id = Integer.parseInt(itemDetails[0]);
            int profit = Integer.parseInt(itemDetails[1]);
            int weight = Integer.parseInt(itemDetails[2]);
            items.add(new Item(id, profit, weight));
        }

        // Read the knapsack capacity
        int capacity = Integer.parseInt(reader.readLine().trim());

        reader.close();

        // Create and return the KnapsackProblem instance
        return new KnapsackProblem(items, capacity);
    }


