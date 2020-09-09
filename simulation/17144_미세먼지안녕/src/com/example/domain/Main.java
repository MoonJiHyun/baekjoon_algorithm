package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int R = 0, C = 0, T = 0;
    private static int[] dx = {0, 0, -1, 1}; // 상하좌우
    private static int[] dy = {1, -1, 0, 0}; // 상하좌우
    private static int airCleanerR = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    airCleanerR = i;
                }
            }
        }
        go(arr,0);
    }

    private static void go(int[][] arr, int time) {
        if (time == T) {
            int sum = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] != -1) sum += arr[i][j];
                }
            }
            System.out.println(sum);
            return;
        }
        arr = dust(arr);
        cleanUp(arr);
        cleanDown(arr);
        go(arr, time + 1);
    }

    private static int[][] dust(int[][] arr) {
        int[][] copyArr = new int[R][C];
        for (int i = 0; i < R; i++) {
            copyArr[i] = Arrays.copyOf(arr[i], C);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] <= 0) continue;
                int amount = arr[i][j] / 5; // 확산되는 양
                int count = 0; // 확산 갯수

                for (int k = 0; k < 4; k++) { // 상하좌우 네방향
                    int y = i + dy[k];
                    int x = j + dx[k];

                    if (checkRange(x, y) && copyArr[y][x] != -1) {
                        copyArr[y][x] += amount;
                        count++;
                    }
                }

                copyArr[i][j] -= amount * count;
            }
        }
        return copyArr;
    }

    private static void cleanUp(int[][] arr) {
        for (int r = airCleanerR - 2; r > 0; r--) {
            arr[r][0] = arr[r - 1][0];
        }

        for (int c = 0; c < C - 1; c++) {
            arr[0][c] = arr[0][c + 1];
        }

        for (int r = 0; r < airCleanerR - 1; r++) {
            arr[r][C - 1] = arr[r + 1][C - 1];
        }

        for (int c = C - 1; c > 0; c--) {
            if (arr[airCleanerR - 1][c - 1] == -1)  {
                arr[airCleanerR - 1][c] = 0;
            } else {
                arr[airCleanerR - 1][c] = arr[airCleanerR - 1][c - 1];
            }
        }
    }

    private static void cleanDown(int[][] arr) {
        for (int r = airCleanerR + 1; r < R - 1; r++) {
            arr[r][0] = arr[r + 1][0];
        }

        for (int c = 0; c < C - 1; c++) {
            arr[R - 1][c] = arr[R - 1][c + 1];
        }

        for (int r = R - 1; r >= airCleanerR; r--) {
            arr[r][C - 1] = arr[r - 1][C - 1];
        }

        for (int c = C - 1; c > 0; c--) {
            if (arr[airCleanerR][c - 1] == -1)  {
                arr[airCleanerR][c] = 0;
            } else {
                arr[airCleanerR][c] = arr[airCleanerR][c - 1];
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }
}
