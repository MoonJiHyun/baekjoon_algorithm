package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Tries {
        Tries[] children = new Tries[10];
        boolean isEnd;

        public Tries() {
            for (int i = 0; i < 10; i++) {
                children[i] = null;
            }
        }
    }

    private static void insert(Tries root, String key) {
        Tries currTries = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - '0';
            if (currTries.children[index] == null) {
                currTries.children[index] = new Tries();
            }
            currTries = currTries.children[index];
        }
        currTries.isEnd = true;
    }

    private static boolean isConsistent(Tries root, String key) {
        Tries currTries = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - '0';
            if (currTries.isEnd) return false;
            currTries = currTries.children[index];
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int size = Integer.parseInt(br.readLine());
            Tries root = new Tries();
            String[] str = new String[size];
            for (int j = 0; j < size; j++) {
                str[j] = br.readLine();
                insert(root, str[j]);
            }
            boolean answer = true;
            for (int j = 0; j < size; j++) {
                if (!isConsistent(root, str[j])) {
                    answer = false;
                    break;
                }
            }
            if (answer) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
}
