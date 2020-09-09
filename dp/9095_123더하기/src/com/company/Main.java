package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        int[] cases;

        for (int i = 0; i < testCase; i++) {
            int num = Integer.parseInt(br.readLine());
            cases = new int[12];

            cases[0] = 1;
            cases[1] = 1;
            cases[2] = 2;

            for (int j = 3; j < num + 1; j++) {
                cases[j] = cases[j - 1] + cases[j - 2] + cases[j - 3];
            }
            sb.append(cases[num]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}