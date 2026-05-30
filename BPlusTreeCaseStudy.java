import java.util.*;

class BPlusTree {
    private TreeSet<Integer> records;

    public BPlusTree() {
        records = new TreeSet<>();
    }

    public void insert(int key) {
        records.add(key);
    }

    public boolean search(int key) {
        return records.contains(key);
    }

    public void rangeQuery(int start, int end) {
        System.out.println("\nRecords between " + start + " and " + end + ":");
        for (Integer key : records.subSet(start, true, end, true)) {
            System.out.print(key + " ");
        }
        System.out.println();
    }

    public void display() {
        System.out.println("B+ Tree Records:");
        for (Integer key : records) {
            System.out.print(key + " ");
        }
        System.out.println();
    }
}

public class BPlusTreeCaseStudy {
    public static void main(String[] args) {

        BPlusTree tree = new BPlusTree();

        int[] productIDs = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        System.out.println("Inserting Product IDs into B+ Tree:");

        for (int id : productIDs) {
            tree.insert(id);
            System.out.print(id + " ");
        }

        System.out.println("\n");

        tree.display();

        int searchKey = 60;

        System.out.println("\nSearching for Product ID: " + searchKey);

        if (tree.search(searchKey)) {
            System.out.println("Product ID " + searchKey + " found.");
        } else {
            System.out.println("Product ID " + searchKey + " not found.");
        }

        tree.rangeQuery(30, 80);

        System.out.println("\nCase Study Completed Successfully.");
    }
}