package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int[] years = new int[s.length];

        final int E = 15;
        final int S = 28;
        final int M = 19;

        for (int i = 0; i < s.length; i++) {
            years[i] = Integer.parseInt(s[i]);
        }

        int x = 0;
        while (x <= E * S * M) {
            int z = (((E * x) + years[0]) - years[2]) / M;
            int y = (((M * z) + years[2]) - years[1]) / S;

            if (x == 0 && y == 0 && z == 0) {
                if (years[0] != years[1] || years[1] != years[2]) {
                    x++;
                    continue;
                }
            }
            if (((((S * y) + years[1]) - years[0]) / E) == x) {
                years[0] += E * x;
                System.out.println(years[0]);
                break;
            }
            x++;
        }

    }
}
