package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static boolean find = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] small = new int[9];
        for (int i = 0; i < 9; i++ ) {
            small[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(small);

        comb(small, new boolean[9], 9, 7, 0);
    }
    private static void comb(int[] small, boolean[] visited, int n, int r, int target) {
        if (find) return;
        if (r == 0) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    sum += small[i];
                }
            }
            if (sum == 100) {
                for (int i = 0; i < n; i++) {
                    if (visited[i]) {
                        System.out.println(small[i]);
                    }
                }
                find = true;
            }
            return;
        }
        if (target == n) {
            return;
        } else {
            visited[target] = true;
            comb(small, visited, n, r - 1, target + 1);
            visited[target] = false;
            comb(small, visited, n, r, target + 1);
        }
    }
}
