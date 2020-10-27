package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static int R;
    private static int C;
    private static char[][] map;
    private static int count = 0;
    private static boolean[] isVisited = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 1);
        System.out.println(count);
    }

    private static void dfs(int r, int c, int depth) {
        int idx = map[r][c] - 'A';
        if (isVisited[idx]) return;
        isVisited[idx] = true;

        for (int k = 0; k < 4; k++) {
            int nr = r + dy[k];
            int nc = c + dx[k];
            if (!isRange(nr, nc) || isVisited[map[nr][nc] - 'A']) continue;
            dfs(nr, nc, depth + 1);
        }
        isVisited[idx] = false;
        count = Math.max(count, depth);
    }

    private static boolean isRange(int nr, int nc) {
        return nr >= 0 && nr < R && nc >= 0 && nc < C;
    }
}
