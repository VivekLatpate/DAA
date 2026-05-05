import java.util.Scanner;

public class KnapsackDP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];

        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        System.out.println("Enter values of items:");
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();

        // DP table
        int[][] dp = new int[n + 1][W + 1];

        // Build table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } 
                else if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        value[i - 1] + dp[i - 1][w - weight[i - 1]], // include item
                        dp[i - 1][w] // exclude item
                    );
                } 
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Output maximum profit
        System.out.println("Maximum Profit: " + dp[n][W]);

        sc.close();
    }
}