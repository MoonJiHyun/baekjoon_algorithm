package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static String[] arr;
    private static int[] alphabet = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            int digit = (int) Math.pow(10, arr[i].length() - 1);
            for (int j = 0; j < arr[i].length(); j++) {
                alphabet[arr[i].charAt(j) - 'A'] += digit;
                digit /= 10;
            }
        }
        
        Arrays.sort(alphabet);
        
        int sum = 0;
        for (int i = alphabet.length - 1, num = 9; i >= 0; i--, num--) {
            if (alphabet[i] == 0) break;
            sum += alphabet[i] * num;
        }

        System.out.println(sum);
    }
}
