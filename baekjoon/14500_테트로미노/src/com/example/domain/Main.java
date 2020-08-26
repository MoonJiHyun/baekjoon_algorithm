package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int max = Integer.MIN_VALUE;
    private static int N, M = 0;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    /**
     *      * # * 에서 #을 (0,0)으로 두고 회전한 배열
     *        *
     */
    private static int[][] ax = new int[][]{{0, 0, 0, 1}, {-1, 0, 1, 0}, {0, 0, 0, -1}, {-1, 0, 1, 0}}; // ㅗ, ㅏ, ㅜ, ㅓ
    private static int[][] ay = new int[][]{{-1, 0, 1, 0}, {0, 0, 0, 1}, {-1, 0, 1, 0}, {0, 0, 0, -1}};
    private static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        isVisited = new boolean[N][M];
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(map);
    }

    private static void solve(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isVisited[i][j] = true;
                dfs(map, i, j, 0, 0);
                isVisited[i][j] = false;
                anotherBlock(map, i, j);
            }
        }
        System.out.println(max);
    }

    private static void dfs(int[][] map, int x, int y, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(sum, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isBoundary(nx, ny)) continue;

            if (!isVisited[nx][ny]) {
                isVisited[nx][ny] = true;
                dfs(map, nx, ny, depth + 1, sum + map[nx][ny]);
                isVisited[nx][ny] = false;
            }
        }
    }

    private static void anotherBlock(int[][] map, int x, int y) {
        for (int i = 0; i < 4; i++) {
            boolean isOut = false;
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + ax[i][j];
                int ny = y + ay[i][j];
                if (!isBoundary(nx, ny)) {
                    isOut = true;
                    break;
                }
                sum += map[nx][ny];
            }
            if (!isOut) {
                max = Math.max(max, sum);
            }
        }
    }

    private static boolean isBoundary(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
