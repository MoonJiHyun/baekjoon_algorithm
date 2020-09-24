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

    private static int max = Integer.MIN_VALUE;
    private static int N, M;
    private static int[][] map;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static List<Node> virusList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virusList.add(new Node(i, j));
            }
        }
        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int depth) {
        if (depth == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(depth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void bfs() {
        int[][] copyArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            copyArr[i] = Arrays.copyOf(map[i], M);
        }


        Queue<Node> queue = new LinkedList<>(virusList);

        while (!queue.isEmpty()) {
            Node n = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nr = n.r + dy[k];
                int nc = n.c + dx[k];

                if (!isRange(nr, nc) || copyArr[nr][nc] != 0) continue;

                copyArr[nr][nc] = 1;
                queue.add(new Node(nr, nc));
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyArr[i][j] == 0) count++;
            }
        }

        max = Math.max(max, count);
    }

    private static boolean isRange(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
