package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] minCnt = new int[num];

        minCnt[0] = 0;

        for (int i = 1; i < num; i++) {
            minCnt[i] = minCnt[i - 1] + 1;

            if ((i + 1) % 3 == 0) {
                minCnt[i] = Math.min(minCnt[i], minCnt[i / 3] + 1);
            }
            if ((i + 1) % 2 == 0) {
                minCnt[i] = Math.min(minCnt[i], minCnt[i / 2] + 1);
            }
        }

        System.out.println(minCnt[num - 1]);
    }
}