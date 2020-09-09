package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N = 0;
    private static long M = 0;

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] tree = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tree);
        solve(tree);
    }

    private static void solve(int[] tree) {
        long left = 1;
        long mid = 0;
        long right = tree[N - 1];

        while (right >= left) {
            long sum = 0;
            mid = (left + right) / 2;

            for (int i = 0; i < N; i++) {
                if (tree[i] > mid) sum += tree[i] - mid;
            }

            if (sum >= M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }
}
