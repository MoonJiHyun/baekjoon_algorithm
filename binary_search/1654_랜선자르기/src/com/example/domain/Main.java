package com.example.domain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] arr = new int[K];

        long max = 0;
        long min = 1;
        long mid;

        for (int i = 0; i < K; i++) {
            arr[i] = sc.nextInt();
            max += arr[i];
        }

        max /= N;
        while (max >= min) {
            mid = (max + min) / 2;

            long count = 0;

            for (int i = 0; i < K; i++) {
                count += arr[i] / mid;
            }

            if (count < N) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(max);
    }
}
