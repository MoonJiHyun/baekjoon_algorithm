package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, H;
    private static int answer;
    private static int[][] arr;
    private static boolean isFinish;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][H + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[y][x] = 1;
            arr[y + 1][x] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (isFinish) break;
        }
        
        System.out.println((isFinish) ? answer : -1);
    }

    private static void dfs(int x, int depth) {
        if (isFinish) return;
        if (answer == depth) {
            if (check()) isFinish = true;
            return;
        }
        for (int i = 1; i < N; i++) {
            for (int j = x; j <= H; j++) {
                if (arr[i][j] != 0 || arr[i + 1][j] != 0) continue;
                arr[i][j] = 1;
                arr[i + 1][j] = 2;
                dfs(j, depth + 1);
                arr[i][j] = arr[i + 1][j] = 0;
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int x = 1;
            int y = i;
            for (int j = 1; j <= H; j++) {
                if (arr[y][x] == 1) y++;
                else if (arr[y][x] == 2) y--;
                x++;
            }
            if (y != i) return false;
        }
        return true;
    }
}
