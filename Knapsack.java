import java.util.*;

class Knapsack {
    // Function to solve the 0/1 Knapsack problem using dynamic programming
    static int knapsack(int[] wt, int[] val, int n, int W) {
        // Create a 2D DP array to store the maximum value for each subproblem
        int dp[][] = new int[n][W + 1];
        
        // Base Condition
        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }
        
        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap <= W; cap++) {
                // Calculate the maximum value when the current item is not taken
                int notTaken = dp[ind - 1][cap];
                
                // Calculate the maximum value when the current item is taken
                int taken = Integer.MIN_VALUE;
                if (wt[ind] <= cap) {
                    taken = val[ind] + dp[ind - 1][cap - wt[ind]];
                }
                
                // Store the maximum value for the current state
                dp[ind][cap] = Math.max(notTaken, taken);
            }
        }
        
        // The result is stored in the last row and last column of the DP array
        return dp[n - 1][W];
    }

    public static void main(String args[]) {
        int wt[] = {3, 1, 4};
        int val[] = {4, 5, 7};
        int W = 5;
        int n = wt.length;

        // Calculate and print the maximum value of items the thief can steal
        System.out.println("The Maximum value of items the thief can steal is " + knapsack(wt, val, n, W));
    }
}