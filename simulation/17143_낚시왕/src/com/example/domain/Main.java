package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Shark {
        int size;
        int speed;
        int dir;

        public Shark(int speed, int dir, int size) {
            this.size = size;
            this.speed = speed;
            this.dir = dir;
        }
    }

    private static int R, C, M;
    private static int currentCol = 1;
    private static Shark[][] map;
    private static int totalSize = 0;
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            switch (d) {
                case 1: d = 0; break;
                case 4: d = 1; break;
            }
            int z = Integer.parseInt(st.nextToken());
            // 위, 왼쪽, 아래, 오른쪽
            map[r][c] = new Shark(s, d, z);
        }

        while (currentCol <= C) {
            getShark();
            moveShark();
            currentCol++;
        }
        System.out.println(totalSize);
    }

    private static void moveShark() {
        Shark[][] copyMap = new Shark[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (map[r][c] == null) continue;

                Shark shark = map[r][c];

                int dir = shark.dir;
                int nr = r;
                int nc = c;

                for (int k = 0; k < shark.speed; k++) {
                    if (nr + dy[dir] < 1 || nr + dy[dir] > R
                        || nc + dx[dir] < 1 || nc + dx[dir] > C) {
                        dir = (dir + 2) % 4;
                    }

                    nr += dy[dir];
                    nc += dx[dir];
                }

                shark.dir = dir;
                if (copyMap[nr][nc] != null && copyMap[nr][nc].size > shark.size) continue;

                copyMap[nr][nc] = shark;
            }
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                map[r][c] = copyMap[r][c];
            }
        }
    }

    private static void getShark() {
        for (int r = 1; r <= R; r++) {
            if (map[r][currentCol] != null) {
                totalSize += map[r][currentCol].size;
                map[r][currentCol] = null;
                break;
            }
        }
    }
}
