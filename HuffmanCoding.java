import java.util.*;

// Node class
class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

public class HuffmanCoding {

    // Function to print codes
    public static void printCodes(Node root, String code) {
        if (root == null) return;

        // If leaf node
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] chars = new char[n];
        int[] freq = new int[n];

        System.out.println("Enter characters:");
        for (int i = 0; i < n; i++) {
            chars[i] = sc.next().charAt(0);
        }

        System.out.println("Enter frequencies:");
        for (int i = 0; i < n; i++) {
            freq[i] = sc.nextInt();
        }

        // Min Heap (Priority Queue)
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.freq));

        // Create leaf nodes
        for (int i = 0; i < n; i++) {
            pq.add(new Node(chars[i], freq[i]));
        }

        // Build Huffman Tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node newNode = new Node('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        Node root = pq.poll();

        System.out.println("Huffman Codes:");
        printCodes(root, "");

        sc.close();
    }
}