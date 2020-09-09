package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count1 = solve(map, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'G') map[i][j] = 'R';
            }
        }

        int count2 = solve(map, N);

        System.out.println(count1 + " " + count2);
    }

    public static int solve(char[][] map, int N) {
        boolean[][] isVisited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisited[i][j]) continue;
                isVisited[i][j] = true;
                queue.offer(new Point(i, j));

                while(!queue.isEmpty()) {
                    Point p = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int currX = p.x + dx[k];
                        int currY = p.y + dy[k];

                        if (!isRange(currX, currY, N) || isVisited[currX][currY] || map[currX][currY] != map[i][j]) continue;
                        isVisited[currX][currY] = true;
                        queue.offer(new Point(currX, currY));
                    }
                }
                count++;
            }
        }

        return count;
    }

    static boolean isRange(int x, int y, int N) {
        if (x >= 0 && x < N && y >= 0 && y < N) return true;
        return false;
    }
}
