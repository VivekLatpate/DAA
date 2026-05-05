import java.util.Scanner;

public class HamiltonianPathNonRecursive {

    // Function to check if vertex can be added
    static boolean isSafe(int v, int[][] graph, int[] path, int pos, int V) {

        // Check if this vertex is adjacent to previous vertex
        if (pos > 0 && graph[path[pos - 1]][v] == 0)
            return false;

        // Check if already included
        for (int i = 0; i < pos; i++) {
            if (path[i] == v)
                return false;
        }

        return true;
    }

    // Non-recursive backtracking
    static boolean hamiltonianPath(int[][] graph, int V) {

        int[] path = new int[V];
        int[] next = new int[V]; // to track next candidate
        int pos = 0;

        // initialize path
        for (int i = 0; i < V; i++) {
            path[i] = -1;
            next[i] = 0;
        }

        path[0] = 0; // start from vertex 0
        pos = 1;

        while (pos > 0) {

            if (pos == V) {
                // Found solution
                System.out.println("Hamiltonian Path:");
                for (int i = 0; i < V; i++) {
                    System.out.print(path[i] + " ");
                }
                System.out.println();
                return true;
            }

            boolean found = false;

            for (int v = next[pos]; v < V; v++) {

                if (isSafe(v, graph, path, pos, V)) {
                    path[pos] = v;
                    next[pos] = v + 1; // next time start from next vertex
                    pos++;
                    if (pos < V) next[pos] = 0;
                    found = true;
                    break;
                }
            }

            if (!found) {
                // backtrack
                next[pos] = 0;
                pos--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter adjacency matrix (0/1):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        if (!hamiltonianPath(graph, V)) {
            System.out.println("No Hamiltonian Path exists!");
        }

        sc.close();
    }
}