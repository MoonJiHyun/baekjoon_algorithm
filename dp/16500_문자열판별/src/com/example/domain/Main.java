package com.example.domain;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String[] words = new String[100];
        int[] dp = new int[101];

        int length = Integer.parseInt(br.readLine());

        for (int i = 0; i < length; i++) {
            words[i] = br.readLine();
        }

        dp[s.length()] = 1;

        for (int pos = s.length()-1; pos >= 0; pos--) {
            for (int j = 0; j < length; j++) {
                if (s.indexOf(words[j], pos) == pos && dp[pos + words[j].length()] == 1) {
                    dp[pos] = 1;
                    break;
                } else {
                    dp[pos] = 0;
                }
            }
        }

        System.out.println(dp[0]);
    }
}