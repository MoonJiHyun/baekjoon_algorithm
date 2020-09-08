package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class CCTV {
        private int x;
        private int y;
        private int cctvNum;
        public CCTV(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.cctvNum = c;
        }
    }

    private static int N = 0; // 세로
    private static int M = 0; // 가로
    private static int answer = Integer.MAX_VALUE;
    private static List<CCTV> cctvList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > 0 && arr[i][j] < 6) cctvList.add(new CCTV(j, i, arr[i][j]));
            }
        }
        search(0, arr);
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    private static void search(int depth, int[][] arr) {
        if (depth == cctvList.size()) {
            setAnswer(arr);
            return;
        }
        CCTV cctv = cctvList.get(depth);

        int cctvNum = cctv.cctvNum;
        int x = cctv.x;
        int y = cctv.y;
        int[][] copyArr = new int[N][M];

        switch (cctvNum) {
            case 1:
                for (int k = 0; k < 4; k++) {
                    for (int i = 0; i < N; i++) {
                        copyArr[i] = Arrays.copyOf(arr[i], M);
                    }
                    check(copyArr, x, y, k);
                    search(depth + 1, copyArr);
                }
                break;
            case 2:
                for (int k = 0; k < 2; k++) {
                    for (int i = 0; i < N; i++) {
                        copyArr[i] = Arrays.copyOf(arr[i], M);
                    }
                    check(copyArr, x, y, k);
                    check(copyArr, x, y, k + 2); // 180도 방향
                    search(depth + 1, copyArr);
                }
                break;
            case 3:
                for (int k = 0; k < 4; k++) {
                    for (int i = 0; i < N; i++) {
                        copyArr[i] = Arrays.copyOf(arr[i], M);
                    }
                    check(copyArr, x, y, k);
                    check(copyArr, x, y, (k + 1) % 4); // 90도 방향
                    search(depth + 1, copyArr);
                }
                break;
            case 4:
                for (int k = 0; k < 4; k++) {
                    for (int i = 0; i < N; i++) {
                        copyArr[i] = Arrays.copyOf(arr[i], M);
                    }
                    check(copyArr, x, y, k);
                    check(copyArr, x, y, (k + 1) % 4); // 90도 방향
                    check(copyArr, x, y, (k + 2) % 4); // 180도 방향
                    search(depth + 1, copyArr);
                }
                break;
            case 5:
                for (int i = 0; i < N; i++) {
                    copyArr[i] = Arrays.copyOf(arr[i], M);
                }
                check(copyArr, x, y, 0);
                check(copyArr, x, y, 1); // 90도 방향
                check(copyArr, x, y, 2); // 180도 방향
                check(copyArr, x, y, 3); // 270도 방향
                search(depth + 1, copyArr);
                break;
        }
    }

    private static void check(int[][] arr, int x, int y, int direction) {
        switch (direction) {
            case 0: // 우
                for (int j = x; j < M; j++) {
                    if (arr[y][j] == 6) break;
                    arr[y][j] = 7;
                }
                break;
            case 1: // 하
                for (int i = y; i < N; i++) {
                    if (arr[i][x] == 6) break;
                    arr[i][x] = 7;
                }
                break;
            case 2: // 좌
                for (int j = x; j >= 0; j--) {
                    if (arr[y][j] == 6) break;
                    arr[y][j] = 7;
                }
                break;
            case 3: // 상
                for (int i = y; i >= 0; i--) {
                    if (arr[i][x] == 6) break;
                    arr[i][x] = 7;
                }
                break;
        }
    }

    private static void setAnswer(int[][] arr) {
        int currAnswer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0)  {
                    currAnswer++;
                }
            }
        }
        answer = Math.min(answer,currAnswer);
    }
}
