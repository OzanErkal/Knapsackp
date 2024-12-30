package edu.estu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit test for the KP01 class.
 */
public class AppTest
{
    /**
     * Test the trivial case of the KP01 problem
     */
    @Test
    public void testTrivialCase() {
        // Create sample items
        Item item1 = new Item(1, 10, 5);
        Item item2 = new Item(2, 20, 10);
        Item item3 = new Item(3, 30, 15);

        // Test case where total weight is less than capacity
        KP01 knapsack1 = new KP01(50, item1, item2, item3);
        assertTrue(knapsack1.trivial()); // Should return true as the total weight (30) is less than capacity (50)

        // Test case where total weight exceeds capacity
        KP01 knapsack2 = new KP01(20, item1, item2, item3);
        assertFalse(knapsack2.trivial()); // Should return false as the total weight (30) exceeds capacity (20)
    }
}
