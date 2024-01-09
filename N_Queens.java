import java.util.ArrayList;
import java.util.*;

public class N_Queens {
    public static void main(String[] args) {
        int n = 4;
        boolean[][] board = new boolean[n][n];
        System.out.println(queens(board, 0));
    }

    static int queens(boolean[][] board, int row) {
        // base case when we reach the last row
        if (row == board.length) {
            List<List<Integer>> list = findList(board);
            System.out.println(list);
            return 1;
        }

        // recursive case when we have to place the queen in the current row
        int count = 0;
        // iterate over the columns
        for (int col = 0; col < board.length; col++) {
            // place the queen if safe
            if (isSafe(board, row, col)) {
                board[row][col] = true; // if safe then place it
                count += queens(board, row + 1); // recursive call
                board[row][col] = false; // backtracking
            }
        }
        return count;
    }

    static boolean isSafe(boolean[][] board, int row, int col) {
        // check for the column
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            // decrease row and col both coz we are checking at left side
            if (board[row - i][col - i]) {
                return false;
            }
        }

        int maxRight = Math.min(row, board.length - col - 1);
        for (int i = 1; i <= maxRight; i++) {
            // decrease row and increase col coz we are checking right side
            if (board[row - i][col + i]) {
                return false;
            }
        }
        // if all the checks passes then return true
        return true;
    }

    static List<List<Integer>> findList(boolean[][] board) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i]) {
                    temp.add(i + 1);
                    temp.add(j + 1);
                    list.add(temp);
                    temp = new ArrayList<>();
                }
            }
        }
        return list;
    }
}