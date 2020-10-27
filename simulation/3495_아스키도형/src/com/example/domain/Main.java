package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int h, w;
    private static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        for (int i = 0; i < h; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        int dot = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == '\\' || map[i][j] == '/') count++;
                if (count % 2 == 1) {
                    if (map[i][j] == '.') dot++;
                }
            }
        }
        System.out.println((count / 2) + dot);
    }
}
