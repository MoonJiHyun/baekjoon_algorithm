package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int L, C;
    private static char[] arr;
    private static String vowel = "aeiou";
    private static List<String> passwordList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        comb(new boolean[C], 0, C, L);
        for (String s : passwordList) {
            System.out.println(s);
        }
    }

    public static void comb(boolean[] isVisited, int depth, int n, int r) {
        if (r == 0) {
            StringBuilder sb = new StringBuilder();
            int vowelCnt = 0;
            int consonantsCnt = 0;
            for (int i = 0; i < C; i++) {
                if (isVisited[i]) {
                    if (vowel.indexOf(arr[i]) > -1) vowelCnt++;
                    else consonantsCnt++;
                    sb.append(arr[i]);
                }
            }

            if (vowelCnt >= 1 && consonantsCnt >= 2) {
                passwordList.add(sb.toString());
            }
            return;
        }

        if (depth == n) return;

        isVisited[depth] = true;
        comb(isVisited, depth + 1, n, r - 1);
        isVisited[depth] = false;
        comb(isVisited, depth + 1, n, r);
    }
}
