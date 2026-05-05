import java.util.Scanner;

public class MinMaxDivideConquer {

    // Function to find min and max using divide and conquer
    static class Pair {
        int min, max;
    }

    public static Pair findMinMax(int[] arr, int low, int high) {
        Pair result = new Pair();

        // Base case: only one element
        if (low == high) {
            result.min = arr[low];
            result.max = arr[low];
            return result;
        }

        // Base case: two elements
        if (high == low + 1) {
            if (arr[low] < arr[high]) {
                result.min = arr[low];
                result.max = arr[high];
            } else {
                result.min = arr[high];
                result.max = arr[low];
            }
            return result;
        }

        // Divide
        int mid = (low + high) / 2;

        Pair left = findMinMax(arr, low, mid);
        Pair right = findMinMax(arr, mid + 1, high);

        // Conquer (combine results)
        result.min = Math.min(left.min, right.min);
        result.max = Math.max(left.max, right.max);

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Pair ans = findMinMax(arr, 0, n - 1);

        System.out.println("Minimum element: " + ans.min);
        System.out.println("Maximum element: " + ans.max);

        sc.close();
    }
}