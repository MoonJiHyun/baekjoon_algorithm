package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Node> blankList = new ArrayList<>();
    private static int[][] sudoku = new int[9][9];
    private static boolean isFinish = false;
    static class Node {
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            char[] arr = sc.next().toCharArray();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(String.valueOf(arr[j]));
                if (sudoku[i][j] == 0) blankList.add(new Node(i, j));
            }
        }

        dfs(0);
    }

    private static void dfs(int depth) {
        if (depth == blankList.size()) {
            printSudoku();
            isFinish = true;
            return;
        }
        if (isFinish) return;

        int r = blankList.get(depth).r;
        int c = blankList.get(depth).c;

        for (int num = 0; num < 9; num++) {
            if (!isDuplicatedNum(r, c, num + 1)) {
                sudoku[r][c] = num + 1;
                dfs(depth + 1);
                sudoku[r][c] = 0;
            }
        }
    }

    private static boolean isDuplicatedNum(int r, int c, int num) {
        for (int n = 0; n < 9; n++) {
            if (sudoku[r][n] == num) return true;
            if (sudoku[n][c] == num) return true;
        }

        int nr = (r / 3) * 3;
        int nc = (c / 3) * 3;

        for (int i = nr; i < nr + 3; i++) {
            for (int j = nc; j < nc + 3; j++) {
                if (sudoku[i][j] == num) return true;
            }
        }
        return false;
    }

    private static void printSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }
}
