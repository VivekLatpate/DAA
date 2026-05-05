import java.util.Scanner;

public class StrassenMatrixMultiplication {

    // Function to add two matrices
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    // Function to subtract two matrices
    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];
        return result;
    }

    // Strassen multiplication function
    public static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        // Base case
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int newSize = n / 2;

            int[][] A11 = new int[newSize][newSize];
            int[][] A12 = new int[newSize][newSize];
            int[][] A21 = new int[newSize][newSize];
            int[][] A22 = new int[newSize][newSize];

            int[][] B11 = new int[newSize][newSize];
            int[][] B12 = new int[newSize][newSize];
            int[][] B21 = new int[newSize][newSize];
            int[][] B22 = new int[newSize][newSize];

           
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    A11[i][j] = A[i][j];
                    A12[i][j] = A[i][j + newSize];
                    A21[i][j] = A[i + newSize][j];
                    A22[i][j] = A[i + newSize][j + newSize];

                    B11[i][j] = B[i][j];
                    B12[i][j] = B[i][j + newSize];
                    B21[i][j] = B[i + newSize][j];
                    B22[i][j] = B[i + newSize][j + newSize];
                }
            }

            // 7 recursive multiplications
            int[][] M1 = strassen(add(A11, A22), add(B11, B22));
            int[][] M2 = strassen(add(A21, A22), B11);
            int[][] M3 = strassen(A11, subtract(B12, B22));
            int[][] M4 = strassen(A22, subtract(B21, B11));
            int[][] M5 = strassen(add(A11, A12), B22);
            int[][] M6 = strassen(subtract(A21, A11), add(B11, B12));
            int[][] M7 = strassen(subtract(A12, A22), add(B21, B22));

            // Combine results
            int[][] C11 = add(subtract(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(subtract(add(M1, M3), M2), M6);

            // Join submatrices into result matrix
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = C11[i][j];
                    C[i][j + newSize] = C12[i][j];
                    C[i + newSize][j] = C21[i][j];
                    C[i + newSize][j + newSize] = C22[i][j];
                }
            }
        }
        return C;
    }

    // Function to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Main function with user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter matrix size (n x n, power of 2): ");
        int n = sc.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = sc.nextInt();

        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                B[i][j] = sc.nextInt();

        int[][] result = strassen(A, B);

        System.out.println("Resultant Matrix:");
        printMatrix(result);

        sc.close();
    }
}