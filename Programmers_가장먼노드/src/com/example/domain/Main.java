package com.example.domain;

import java.util.*;

class Solution {
    public static int solution(int n, int[][] edge) {
        int count = 1;
        int max = 0;

        boolean[][] linkedMap = new boolean[n][n]; // 연결된 노드는 true인 map
        int[] distance = new int[n]; // 1번 노드에서 각 노드까지의 최단 거리

        for (int[] e : edge) {
            linkedMap[e[0] - 1][e[1] - 1] = linkedMap[e[1] - 1][e[0] - 1] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int p = queue.poll();
            for (int i = 1; i < n; i++) {
                if (distance[i] == 0 && linkedMap[p][i]) {
                    distance[i] = distance[p] + 1;
                    queue.add(i);
                    if (max < distance[i]) {
                        max = distance[i];
                        count = 1;
                    } else if (max == distance[i]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(
            Solution.solution(6, new int[][]{
                new int[]{3, 6},
                new int[]{4, 3},
                new int[]{3, 2},
                new int[]{1, 3},
                new int[]{1, 2},
                new int[]{2, 4},
                new int[]{5, 2}
            }) == 3
        );
    }
}
