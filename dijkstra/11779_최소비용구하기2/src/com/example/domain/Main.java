package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Bus implements Comparable<Bus> {
        int end;
        int cost;

        public Bus(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }
    private static int n, m;
    private static List<Bus>[] busList;
    private static int[] dist, parent;
    private static int start, end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        initArr();

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            busList[start].add(new Bus(end, cost));
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();
        getCity();
    }

    private static void dijkstra() {
        boolean[] isVisited = new boolean[n + 1];

        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Bus currBus = pq.poll();
            System.out.println(currBus.end + " " + currBus.cost);
            int currEnd = currBus.end;

            if (isVisited[currEnd]) continue;
            isVisited[currEnd] = true;

            for (Bus bus : busList[currEnd]) {
                if (dist[bus.end] > dist[currEnd] + bus.cost) {
                    dist[bus.end] = dist[currEnd] + bus.cost;
                    pq.offer(new Bus(bus.end, dist[bus.end]));
                    parent[bus.end] = currEnd;
                }
            }
        }
        System.out.println(dist[end]);
    }

    private static void getCity() {
        Stack<Integer> stack = new Stack<>();
        int index = end;
        int cityCount = 0;

        while (true) {
            stack.push(index);
            cityCount++;
            if (index == start) break;
            index = parent[index];
        }
        System.out.println(cityCount);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void initArr() {
        busList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            busList[i] = new ArrayList<>();
        }
        dist = new int[n + 1];
        parent = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
    }
}
