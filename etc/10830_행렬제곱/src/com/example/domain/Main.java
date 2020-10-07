package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long B;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = solve(B, arr);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((result[i][j] % 1000) + " ");
            }
            System.out.println();
        }
    }

    private static int[][] solve(long b, int[][] arr) {
        if (b == 1) {
            return arr;
        } else {
            if (b % 2 == 0) {
                int[][] tmp = solve(b / 2, arr);
                return multiply(tmp, tmp);
            } else {
                int[][] tmp = solve(b - 1, arr);
                return multiply(arr, tmp);
            }
        }
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += a[i][k] * b[k][j];
                }
                tmp[i][j] = sum % 1000;
            }
        }

        return tmp;
    }
}
