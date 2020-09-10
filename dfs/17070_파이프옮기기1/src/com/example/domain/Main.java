package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N = 0;
    private static int count = 0;

    private static int[][] map;
    private static int[] dx = {1, 0, 1}; // 오른쪽, 아래, 대각선
    private static int[] dy = {0, 1, 1}; // 오른쪽, 아래, 대각선

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = 1;
        int c = 2;
        dfs(r, c, 0);
        System.out.println(count);
    }

    /**
     * direction 0 : 가로
     * direction 1 : 세로
     * direction 2 : 대각선
     * @param r
     * @param c
     * @param direction
     */
    private static void dfs(int r, int c, int direction) {
        if (r == N && c == N) {
            count++;
            return;
        }

        for (int k = 0; k < 3; k++) {
            if (!canMove(direction, k)) continue;

            int nr = r + dy[k];
            int nc = c + dx[k];

            if (!isRange(nr, nc) || isWall(nr, nc, k)) continue;

            dfs(nr, nc, k);
        }
    }

    /**
     * @param currentDir (현재 놓여져있는 방향)
     * @param type (움직일 방향)
     * @return
     */
    private static boolean canMove(int currentDir, int type) {
        if (currentDir == 2 || type == 2) return true;
        if (currentDir == 0 && type == 0) return true;
        else return currentDir == 1 && type == 1;
    }

    private static boolean isWall(int r, int c, int k) {
        if (k == 2) {
            return map[r][c] == 1 || map[r - 1][c] == 1 || map[r][c - 1] == 1;
        } else {
            return map[r][c] == 1;
        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= N;
    }
}
