package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int r;
        int c;
        int t;
        boolean flag;
        public Node(int r, int c, int t, boolean flag) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.flag = flag;
        }
    }
    private static int N, M;
    private static boolean[][] map;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static List<Node> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = c[j] == '1';
                if (map[i][j]) {
                    list.add(new Node(i, j, 0, false));
                }
            }
        }

        solve();
    }

    private static void solve() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, false));

        boolean[][][] isVisited = new boolean[N][M][2];

        while (!q.isEmpty()) {
            Node n = q.poll();

            if (n.r == N - 1 && n.c == M - 1) {
                System.out.println(n.t);
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nr = n.r + dy[k];
                int nc = n.c + dx[k];
                boolean flag = n.flag;

                if (!isRange(nr, nc)) continue;
                if (map[nr][nc]) {
                    if (!flag && !isVisited[nr][nc][1]) {
                        q.add(new Node(nr, nc, n.t + 1, true));
                        isVisited[nr][nc][1] = true;
                    }
                } else {
                    int breakWall = flag ? 1 : 0;
                    if (!isVisited[nr][nc][breakWall]) {
                        q.add(new Node(nr, nc, n.t + 1, flag));
                        isVisited[nr][nc][breakWall] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isRange(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
