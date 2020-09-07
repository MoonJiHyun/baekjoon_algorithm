package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int NUM = 10000;

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            solve(A, B);
        }
    }

    private static void solve(int A, int B) {
        String[] register = new String[NUM];
        boolean[] isVisited = new boolean[NUM];

        Queue<Integer> queue = new LinkedList<>();

        isVisited[A] = true;
        queue.add(A);
        Arrays.fill(register, "");
        while (!queue.isEmpty() && !isVisited[B]) {
            int curr = queue.poll();
            int D = (2 * curr) % 10000;
            int S = (curr == 0) ? 9999 : curr - 1;
            int L = ((curr % 1000) * 10) + (curr / 1000);
            int R = ((curr % 10) * 1000) + (curr / 10);

            if (!isVisited[D]) {
                queue.add(D);
                isVisited[D] = true;
                register[D] = register[curr] + "D";
            }

            if (!isVisited[S]) {
                queue.add(S);
                isVisited[S] = true;
                register[S] = register[curr] + "S";
            }

            if (!isVisited[L]) {
                queue.add(L);
                isVisited[L] = true;
                register[L] = register[curr] + "L";
            }

            if (!isVisited[R]) {
                queue.add(R);
                isVisited[R] = true;
                register[R] = register[curr] + "R";
            }
        }
        System.out.println(register[B]);
    }
}
