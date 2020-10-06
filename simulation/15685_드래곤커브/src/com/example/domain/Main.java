package com.example.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static boolean[][] map = new boolean[101][101];
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int x, y, d, g;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            List<Integer> directionList = new LinkedList<>();
            directionList.add(d);

            generate(g, directionList);
            draw(x, y, directionList);
        }
        System.out.println(getSquareCount());
    }

    private static int getSquareCount() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1]
                        && map[i + 1][j] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void draw(int x, int y, List<Integer> directionList) {
        int nx = x;
        int ny = y;

        map[ny][nx] = true;

        for (int d : directionList) {
            nx += dx[d];
            ny += dy[d];
            map[ny][nx] = true;
        }
    }

    private static void generate(int g, List<Integer> directionList) {
        for (int i = 0; i < g; i++) {
            for (int j = directionList.size() - 1; j >= 0; j--) {
                int direction = (directionList.get(j) + 1) % 4;
                directionList.add(direction);
            }
        }
    }
}
