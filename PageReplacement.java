import java.util.*;

public class PageReplacement {
    
    // FIFO Page Replacement Algorithm
    public static int fifoPageReplacement(int[] pages, int frames) {
        Queue<Integer> queue = new LinkedList<>(); // Queue to store page order
        Set<Integer> frameSet = new HashSet<>(); // Set to track pages in memory
        int pageFaults = 0;

        for (int page : pages) {
            if (!frameSet.contains(page)) { // If page is not in memory
                if (queue.size() >= frames) { // If frames are full, remove oldest page
                    int removed = queue.poll();
                    frameSet.remove(removed);
                }
                queue.add(page); // Add new page to queue
                frameSet.add(page); // Add new page to memory
                pageFaults++; // Increment page fault count
            }
        }
        return pageFaults;
    }

    // LRU Page Replacement Algorithm
    public static int lruPageReplacement(int[] pages, int frames) {
        List<Integer> pageStack = new ArrayList<>(); // List to store page access order
        int pageFaults = 0;

        for (int page : pages) {
            if (!pageStack.contains(page)) { // If page is not in memory
                if (pageStack.size() >= frames) { // If frames are full, remove least recently used page
                    pageStack.remove(0);
                }
                pageStack.add(page); // Add new page to memory
                pageFaults++; // Increment page fault count
            } else {
                pageStack.remove((Integer) page); // Remove page from its current position
                pageStack.add(page); // Move page to the most recently used position
            }
        }
        return pageFaults;
    }

    public static void main(String[] args) {
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5}; // Reference string
        int numFrames = 3; // Number of available frames (can be adjusted between 2 to 5)

        int fifoFaults = fifoPageReplacement(pages, numFrames); // Run FIFO algorithm
        int lruFaults = lruPageReplacement(pages, numFrames); // Run LRU algorithm

        System.out.println("FIFO Page Faults: " + fifoFaults); // Print FIFO results
        System.out.println("LRU Page Faults: " + lruFaults); // Print LRU results
    }
}