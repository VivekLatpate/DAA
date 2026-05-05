import java.util.Scanner;

public class NQueens {

    // Function to check if it's safe to place queen
    public static boolean isSafe(int[][] board, int row, int col, int n) {

        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Recursive function (Backtracking)
    public static boolean solveNQueens(int[][] board, int row, int n) {

        // Base case: all queens placed
        if (row == n) {
            return true;
        }

        // Try placing queen in each column
        for (int col = 0; col < n; col++) {

            if (isSafe(board, row, col, n)) {

                board[row][col] = 1; // place queen

                if (solveNQueens(board, row + 1, n)) {
                    return true;
                }

                board[row][col] = 0; // backtrack
            }
        }

        return false;
    }

    // Print board
    public static void printBoard(int[][] board, int n) {
        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of queens (n): ");
        int n = sc.nextInt();

        int[][] board = new int[n][n];

        if (solveNQueens(board, 0, n)) {
            printBoard(board, n);
        } else {
            System.out.println("No solution exists!");
        }

        sc.close();
    }
}