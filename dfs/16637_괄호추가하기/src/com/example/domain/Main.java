package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int MAX = Integer.MIN_VALUE;
    private static int N = 0;

    private static List<Integer> numList = new ArrayList<>();
    private static List<Character> opList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        String s = sc.next();

        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                numList.add(s.charAt(i) - '0');
            } else {
                opList.add(s.charAt(i));
            }
        }

        dfs(0, numList.get(0));
        System.out.println(MAX);
    }

    private static void dfs(int index, int sum) {
        if (index >= opList.size()) {
            MAX = Math.max(MAX, sum);
            return;
        }

        dfs(index + 1, calculate(sum, numList.get(index + 1), opList.get(index)));

        if (index + 1 < opList.size()) {
            int num = calculate(numList.get(index + 1), numList.get(index + 2), opList.get(index + 1));
            dfs(index + 2, calculate(sum, num , opList.get(index)));
        }
    }

    private static int calculate(int a, int b, char operation) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }
}
