package com.example.domain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Integer> minusQ = new PriorityQueue<>();
        Queue<Integer> plusQ = new PriorityQueue<>(Comparator.reverseOrder());

        int max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num < 0) {
                minusQ.add(num);
                max = Math.max(max, num * - 1);
            } else {
                plusQ.add(num);
                max = Math.max(max, num);
            }
        }

        int sum = 0;

        while (!minusQ.isEmpty()) {
            for (int i = 0; i < M; i++) {
                if (minusQ.isEmpty()) break;
                int num = minusQ.poll();
                if (i == 0) {
                    sum += num * -1;
                }
            }
        }

        while (!plusQ.isEmpty()) {
            for (int i = 0; i < M; i++) {
                if (plusQ.isEmpty()) break;
                int num = plusQ.poll();
                if (i == 0) {
                    sum += num;
                }
            }
        }

        System.out.println(sum * 2 - max);
    }
}
