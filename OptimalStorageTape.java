import java.util.Scanner;
import java.util.Arrays;

public class OptimalStorageTape {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of files: ");
        int n = sc.nextInt();

        int[] files = new int[n];

        System.out.println("Enter file sizes:");
        for (int i = 0; i < n; i++) {
            files[i] = sc.nextInt();
        }

        // Step 1: Sort (Greedy choice)
        Arrays.sort(files);

        System.out.println("Optimal order of storing files:");
        for (int i = 0; i < n; i++) {
            System.out.print(files[i] + " ");
        }
        System.out.println();

        // Step 2: Calculate Mean Retrieval Time (MRT)
        int cumulative = 0;
        int total = 0;

        for (int i = 0; i < n; i++) {
            cumulative += files[i];  // running sum
            total += cumulative;     // add to total
        }

        double mrt = (double) total / n;

        // Output
        System.out.println("Mean Retrieval Time: " + mrt);

        sc.close();
    }
}