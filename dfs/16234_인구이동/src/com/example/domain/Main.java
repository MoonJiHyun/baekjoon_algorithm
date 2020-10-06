package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static int N, L, R;
    private static int[][] map;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        int count = 0;
        while (true) {
            isVisited = new boolean[N][N];

            if (checkMove()) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
    }

    private static boolean checkMove() {
        List<Node> list;

        boolean isFinish = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisited[i][j]) continue;
                list = new LinkedList<>();
                list.add(new Node(i, j));

                int sum = dfs(i, j, list);

                if (list.size() > 1) {
                    int avg = sum / list.size();
                    for (Node n : list) {
                        map[n.r][n.c] = avg;
                    }
                    isFinish = true;
                }
            }
        }

        return isFinish;
    }

    private static int dfs(int r, int c, List<Node> list) {
        isVisited[r][c] = true;
        int sum = map[r][c];

        for (int k = 0; k < 4; k++) {
            int nr = r + dy[k];
            int nc = c + dx[k];

            if (!isRange(nr, nc) || isVisited[nr][nc]) continue;

            int diff = Math.abs(map[r][c] - map[nr][nc]);
            if (diff >= L && diff <= R) {
                list.add(new Node(nr, nc));
                sum += dfs(nr, nc, list);
            }
        }
        return sum;
    }

    private static boolean isRange(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}
