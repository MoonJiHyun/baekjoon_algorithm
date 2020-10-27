package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        int[] parents = new int[N + 1];
        dfs(list, parents, 1, 0);

        for (int j = 2; j <= N; j++) {
            System.out.println(parents[j]);
        }
    }

    public static void dfs(ArrayList<ArrayList<Integer>> list, int[] parents, int start, int parent) {
        parents[start] = parent;

        for (int item : list.get(start)) {
            if (item != parent) dfs(list, parents, item, start);
        }
    }
}
