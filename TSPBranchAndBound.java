import java.util.Scanner;

public class TSPBranchAndBound {

    static int N;
    static int[][] cost;
    static boolean[] visited;
    static int minCost = Integer.MAX_VALUE;
    static int[] bestPath;

    // Function to find minimum cost
    static void tsp(int level, int currentCost, int currentVertex, int[] path) {

        // If all vertices are visited
        if (level == N) {
            if (cost[currentVertex][0] != 0) {
                int totalCost = currentCost + cost[currentVertex][0];

                if (totalCost < minCost) {
                    minCost = totalCost;
                    System.arraycopy(path, 0, bestPath, 0, N);
                }
            }
            return;
        }

        // Try next vertex
        for (int i = 0; i < N; i++) {
            if (!visited[i] && cost[currentVertex][i] != 0) {

                int newCost = currentCost + cost[currentVertex][i];

                // Branch and Bound (pruning)
                if (newCost < minCost) {
                    visited[i] = true;
                    path[level] = i;

                    tsp(level + 1, newCost, i, path);

                    visited[i] = false; // backtrack
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        N = sc.nextInt();

        cost = new int[N][N];
        visited = new boolean[N];
        bestPath = new int[N];

        System.out.println("Enter cost matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        int[] path = new int[N];

        // Start from city 0
        visited[0] = true;
        path[0] = 0;

        tsp(1, 0, 0, path);

        // Output result
        System.out.println("Minimum Cost: " + minCost);
        System.out.print("Optimal Path: ");

        for (int i = 0; i < N; i++) {
            System.out.print(bestPath[i] + " -> ");
        }
        System.out.println("0"); // return to start

        sc.close();
    }
}