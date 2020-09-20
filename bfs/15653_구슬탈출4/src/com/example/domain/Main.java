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
        public Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    private static int N, M;
    private static char[][] map;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        Node red = null;
        Node blue = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    red = new Node(i, j, 0);
                } else if (map[i][j] == 'B') {
                    blue = new Node(i, j, 0);
                }
            }
        }
        System.out.println(solve(red, blue));
    }

    private static int solve(Node red, Node blue) {
        boolean[][][][] isVisited= new boolean[N][M][N][M];
//        boolean[][] isVisitedBlue = new boolean[N][M];
        Queue<Node> q = new LinkedList<>();

        q.add(red);
        q.add(blue);

        isVisited[red.r][red.c][blue.r][blue.c] = true;
//        isVisitedBlue[blue.r][blue.c] = true;

        map[red.r][red.c] = '.';
        map[blue.r][blue.c] = '.';

        while (!q.isEmpty()) {
            red = q.poll();
            blue = q.poll();

            for (int k = 0; k < 4; k++) {
                boolean redFinish = false, blueFinish = false, behindBlue = false;
                int redR = red.r;
                int redC = red.c;
                int blueR = blue.r;
                int blueC = blue.c;

                while (canMove(redR + dy[k], redC + dx[k])) {
                    redR += dy[k];
                    redC += dx[k];
                    if (redR == blueR && redC == blueC) behindBlue = true;
                    if (map[redR][redC] == 'O') {
                        redFinish = true;
                        break;
                    }
                }

                while (canMove(blueR + dy[k], blueC + dx[k])) {
                    blueR += dy[k];
                    blueC += dx[k];
                    if (map[blueR][blueC] == 'O') {
                        blueFinish = true;
                        break;
                    }
                }

                if (blueFinish) continue;
                if (redFinish) return red.time + 1;

                if (redR == blueR && redC == blueC) {
                    if (behindBlue) { // 파란 -> 빨간
                        redR -= dy[k];
                        redC -= dx[k];
                    } else {
                        blueR -= dy[k];
                        blueC -= dx[k];
                    }
                }
                if (isVisited[redR][redC][blueR][blueC]) continue;

                isVisited[redR][redC][blueR][blueC] = true;
                q.add(new Node(redR, redC, red.time + 1));
                q.add(new Node(blueR, blueC, blue.time + 1));
            }
        }
        return -1;
    }

    private static boolean canMove(int r, int c) {
        return map[r][c] != '#';
    }
}
