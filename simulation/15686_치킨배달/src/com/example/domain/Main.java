package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static int N, M;
    private static int minDist = Integer.MAX_VALUE;
    private static int[][] arr;
    private static List<Node> house = new ArrayList<>();
    private static List<Node> chickenHouse = new ArrayList<>();
    private static Stack<Node> selectedChickenHouse = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    house.add(new Node(i, j));
                } else if (arr[i][j] == 2) {
                    chickenHouse.add(new Node(i, j));
                }
            }
        }
        comb(0, 0);
        System.out.println(minDist);
    }

    private static void comb(int start, int depth) {
        if (depth == M) {
            setMinDist();
            return;
        }

        for (int i = start; i < chickenHouse.size(); i++) {
            selectedChickenHouse.push(chickenHouse.get(i));
            comb(i + 1, depth + 1);
            selectedChickenHouse.pop();
        }
    }

    private static void setMinDist() {
        int sum = 0;
        for (Node n : house) {
            int min = Integer.MAX_VALUE;
            for (Node chicken : selectedChickenHouse) {
                int dist = Math.abs(n.r - chicken.r) + Math.abs(n.c - chicken.c);
                min = Math.min(min, dist);
            }
            sum += min;
        }
        minDist = Math.min(minDist, sum);
    }
}
