package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Dice {
        int top, bottom, east, west, north, south;

        public Dice() {
            this.top = 0;
            this.bottom = 0;
            this.east = 0;
            this.west = 0;
            this.north = 0;
            this.south = 0;
        }

        public void rollEast() {
            int temp = top;
            top = west;
            west = bottom;
            bottom = east;
            east = temp;
        }

        public void rollWest() {
            int temp = top;
            top = east;
            east = bottom;
            bottom = west;
            west = temp;
        }

        public void rollNorth() {
            int temp = top;
            top = south;
            south = bottom;
            bottom = north;
            north = temp;
        }

        public void rollSouth() {
            int temp = top;
            top = north;
            north = bottom;
            bottom = south;
            south = temp;
        }
    }

    private static int N = 0, M = 0, x = 0, y = 0, k = 0;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] map;
    private static int[] cmdList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cmdList = new int[k];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            cmdList[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        solve();
    }

    private static void solve() {
        Dice dice = new Dice();
        StringBuilder sb = new StringBuilder();

        for (int cmd : cmdList) {
            int nx = x + dx[cmd];
            int ny = y + dy[cmd];
            if (!isRange(nx, ny)) continue;
            switch (cmd) {
                case 0:
                    dice.rollEast();
                    break;
                case 1:
                    dice.rollWest();
                    break;
                case 2:
                    dice.rollNorth();
                    break;
                case 3:
                    dice.rollSouth();
                    break;
            }

            if (map[nx][ny] == 0) {
                map[nx][ny] = dice.bottom;
            } else {
                dice.bottom = map[nx][ny];
                map[nx][ny] = 0;
            }

            sb.append(dice.top).append("\n");
            x = nx;
            y = ny;
        }
        System.out.println(sb.toString());
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
