package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int r;
        int c;
        int time;
        int num;
        public Node(int r, int c, int time, int num) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.num = num;
        }
    }
    private static int N, M;
    private static char[][] map;
    private static Node coin1, coin2;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'o') {
                    if (coin1 == null) coin1 = new Node(i, j, 0, 1);
                    else coin2 = new Node(i, j, 0, 2);
                }
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        Queue<Node> q = new LinkedList<>();
        q.add(coin1);
        q.add(coin2);

        while (!q.isEmpty()) {
            Node c1 = q.poll();
            Node c2 = q.poll();

            if (c1.time >= 10) return -1;

            for (int k = 0; k < 4; k++) {
                boolean isOut1 = false;
                boolean isOut2 = false;
                int nr1 = c1.r + dy[k];
                int nc1 = c1.c + dx[k];
                int nr2 = c2.r + dy[k];
                int nc2 = c2.c + dx[k];

                if (!isRange(nr1, nc1)) isOut1 = true;
                if (!isRange(nr2, nc2)) isOut2 = true;

                if (isOut1 && isOut2) continue;
                if (isOut1 || isOut2) return c1.time + 1;

                if (map[nr1][nc1] == '#' && map[nr2][nc2] == '#') continue;
                if (map[nr1][nc1] == '#') {
                    nr1 -= dy[k];
                    nc1 -= dx[k];
                }
                if (map[nr2][nc2] == '#') {
                    nr2 -= dy[k];
                    nc2 -= dx[k];
                }
                q.offer(new Node(nr1, nc1, c1.time + 1, 1));
                q.offer(new Node(nr2, nc2, c2.time + 1, 2));
            }
        }
        return -1;
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
