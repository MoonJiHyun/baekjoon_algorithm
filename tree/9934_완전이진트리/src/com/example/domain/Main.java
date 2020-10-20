package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

public class Main {
    private static int K;
    private static int[] num;
    private static int[][] tree = new int[11][512]; // n깊이 트리의 마지막 노드는 2^(n-1) 개
    private static int[] depthIdx = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int end = (int) Math.pow(2, K) - 1;

        num = new int[end + 1];
        for (int i = 1; i <= end; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(1, end, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int count = (int) Math.pow(2, i);
            for (int j = 0; j < count; j++) {
                sb.append(tree[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void makeTree(int start, int end, int depth) {
        if (end - start < 0) return;

        int mid = (start + end) / 2;

        tree[depth][depthIdx[depth]] = num[mid];
        depthIdx[depth] += 1;
        makeTree(start, mid - 1, depth + 1);
        makeTree(mid + 1, end, depth + 1);
    }
}
