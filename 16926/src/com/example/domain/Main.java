package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        solution(N, M, R, arr);
//        System.out.println(N + " " + M + " " +R);
    }

    public static void solution(int N, int M, int R, int[][] arr) {
        for (int r = 0; r < R; r++) {
            for (int i = 0; i < Math.min(N, M) / 2; i++) {
                int top = i;
                int left = i;
                int bottom = N - i - 1;
                int right = M - i - 1;
                int tmp = arr[i][i];
                // 좌로 이동
                for (int j = left; j < right; j++) {
                    arr[top][j] = arr[top][j + 1];
                }
                // 상으로 이동
                for (int j = top; j < bottom; j++) {
                    arr[j][right] = arr[j + 1][right];
                }
                // 우로 이동
                for (int j = right; j > left; j--) {
                    arr[bottom][j] = arr[bottom][j - 1];
                }
                // 하로 이동
                for (int j = bottom; j > top; j--) {
                    arr[j][left] = arr[j - 1][left];
                }
                arr[top + 1][left] = tmp;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
