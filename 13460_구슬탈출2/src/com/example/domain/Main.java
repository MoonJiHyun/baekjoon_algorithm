package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static int N, M;
    private static char[][] map;
    private static Node red, blue, hole;
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
                if (map[i][j] == 'R') red = new Node(i, j);
                else if (map[i][j] == 'B') blue = new Node(i, j);
                else if (map[i][j] == 'O') hole = new Node(i, j);
            }
        }

        Queue<Node> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][M];
        queue.offer(red);
        int count = 0;
        while (!queue.isEmpty()) {
            Node n = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nr = n.r + dy[k];
                int nc = n.c + dx[k];

                if (!isRange(nr, nc) || isVisited[nr][nc]) continue;
                isVisited[nr][nc] = true;
                queue.offer(new Node(nr, nc));
            }
        }
    }

    private static boolean isRange(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

    private static void move() {

    }
}
