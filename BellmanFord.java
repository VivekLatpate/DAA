import java.util.Scanner;

public class BellmanFord {

    // Edge structure
    static class Edge {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Edge[] edges = new Edge[E];

        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(s, d, w);
        }

        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        // Distance array
        int[] dist = new int[V];

        // Initialize distances
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        // Relax edges V-1 times
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int w = edges[j].weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check negative weight cycle
        boolean hasNegativeCycle = false;
        for (int j = 0; j < E; j++) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int w = edges[j].weight;

            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("Graph contains negative weight cycle!");
        } else {
            // Output shortest distances
            System.out.println("Shortest distances from source " + source + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("To vertex " + i + " : " + dist[i]);
            }
        }

        sc.close();
    }
}