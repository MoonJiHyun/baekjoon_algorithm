package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] cases = new int[num + 1];

        cases[0] = 1;
        cases[1] = 1;

        for (int i = 2; i < num + 1; i++) {
            cases[i] = (cases[i - 1] + (2 * cases[i - 2])) % 10007;
        }

        System.out.println(cases[num]);
    }
}