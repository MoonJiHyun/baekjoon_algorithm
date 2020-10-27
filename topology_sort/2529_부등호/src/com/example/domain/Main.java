package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int k;
    private static int[] indegree = new int[10];
    private static int[] reverseIndegree = new int[10];
    private static List<List<Integer>> list = new ArrayList<>();
    private static List<List<Integer>> reverseList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            String ch = st.nextToken();
            if (ch.equals("<")) fill(i, i + 1);
            else fill(i + 1, i);
        }

        getMax();
        getMin();
    }

    private static void fill(int a, int b) {
        indegree[b]++;
        reverseIndegree[a]++;
        list.get(a).add(b);
        reverseList.get(b).add(a);
    }

    private static void getMin() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        boolean[] isVisited = new boolean[10];
        int[] min = new int[10];
        for (int i = 0; i <= k; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < 10; i++) {
                if (isVisited[i]) continue;
                min[node] = i;
                isVisited[i] = true;
                break;
            }

            for (int num : list.get(node)) {
                indegree[num]--;
                if (indegree[num] == 0) queue.offer(num);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            sb.append(min[i]);
        }
        System.out.println(sb);
    }

    private static void getMax() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        boolean[] isVisited = new boolean[10];
        int[] max = new int[10];

        for (int i = 0; i <= k; i++) {
            if (reverseIndegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 9; i >= 0; i--) {
                if (isVisited[i]) continue;
                max[node] = i;

                isVisited[i] = true;
                break;
            }

            for (int num : reverseList.get(node)) {
                reverseIndegree[num]--;
                if (reverseIndegree[num] == 0) queue.offer(num);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            sb.append(max[i]);
        }
        System.out.println(sb);
    }
}
