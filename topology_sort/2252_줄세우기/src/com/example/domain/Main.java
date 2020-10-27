package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[] degree;
    private static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            list.get(c1).add(c2);
            degree[c2]++;
        }
        
        topologySort();
    }

    private static void topologySort() {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int num : list.get(node)) {
                degree[num]--;
                if (degree[num] == 0) {
                    queue.offer(num);
                }
            }
        }

        result.forEach(e -> {
            if (e != 0) System.out.print(e + " ");
        });
    }
}
