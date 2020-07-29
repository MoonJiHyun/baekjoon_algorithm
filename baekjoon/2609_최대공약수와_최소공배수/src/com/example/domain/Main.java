package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n1 = Integer.parseInt(s[0]); // 큰 수
        int n2 = Integer.parseInt(s[1]); // 작은 수

        if (n1 < n2) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        int greatest = 1;
        int least = n1 * n2;
        for (int i = 1; i <= n2; i++) {
            if (n2 % i == 0 && n1 % i == 0) {
                if (greatest < i) {
                    greatest = i;
                    least = n1 * n2 / greatest;
                }
            }
        }
        System.out.println(greatest);
        System.out.println(least);
    }
}
