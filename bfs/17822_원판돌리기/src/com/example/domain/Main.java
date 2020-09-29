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
        int value;

        public Node(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }
    private static int N, M, T;
    private static int x, d, k;
    private static int[][] arr;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            for (int j = x; j <= N; j += x) {
                rotate(j);
            }

            calc();
        }

        int sum = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sum += arr[i][j];
            }
        }

        System.out.println(sum);
    }

    private static void calc() {
        if (remove()) return;
        
        int count = 0;
        double sum = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (arr[i][j] == 0) continue;
                count++;
                sum += arr[i][j];
            }
        }

        double avg = sum / count;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (arr[i][j] == 0) continue;
                if (arr[i][j] > avg) arr[i][j]--;
                else if (arr[i][j] < avg) arr[i][j]++;
            }
        }
    }

    private static boolean remove() {
        Queue<Node> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (arr[i][j] != 0) queue.offer(new Node(i, j, arr[i][j]));
            }
        }
        boolean checkRemoved = false;

        while (!queue.isEmpty()) {
            boolean checkSameNum = false;

            Node n = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nr = n.r + dy[k];
                int nc = n.c + dx[k];

                if (nc == M + 1) nc = 1;
                else if (nc == 0) nc = M;

                if (!isRange(nr, nc) || arr[nr][nc] == 0) continue;
                if (arr[nr][nc] == n.value) {
                    arr[nr][nc] = 0;
                    checkSameNum = true;
                }
            }

            if (!checkSameNum) continue;

            arr[n.r][n.c] = 0;
            checkRemoved = true;
        }
        return checkRemoved;
    }

    private static void rotate(int circleNum) {
        int count = 0;
        while (count < k) {
            if (d == 0) { // 시계
                int last = arr[circleNum][M];
                for (int i = M; i > 1; i--) {
                    arr[circleNum][i] = arr[circleNum][i - 1];
                }
                arr[circleNum][1] = last;
            } else { // 반시계
                int first = arr[circleNum][1];
                for (int i = 1; i < M; i++) {
                    arr[circleNum][i] = arr[circleNum][i + 1];
                }
                arr[circleNum][M] = first;
            }
            count++;
        }
    }

    private static boolean isRange(int nr, int nc) {
        return nr >= 1 && nr <= N && nc >= 1 && nc <= M;
    }
}
