package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static int TIME = 0;
    static int ROW = 3;
    static int COL = 3;
    static int[] count;
    static int[][] input = new int[101][101];
    static ArrayList<MySort> list;
    public static void main(String[] args) throws IOException {
        new Main().solve();
    }
    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (input[r][c] != k) timer();

        System.out.println(TIME);
        return;
    }

    private static void timer() {
        while (input[r][c] != k) {
            if (ROW >= COL) { // 행의 정렬
                COL = sortRow();
            } else { // 열의 정렬
                ROW = sortCol();
            }
            TIME++;

            if (TIME > 100) {
                TIME = -1;
                break;
            }
        }
    }

    private static int sortRow() {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= ROW; i++) {
            count = new int[101]; // 1부터 시작하기 때문에
            for (int j = 1; j <= COL; j++) {
                count[input[i][j]]++;
            }
            sortList();
            max = Math.max(max, list.size() * 2);
            int index = 1;
            for (int j = 0; j < list.size(); j++) {
                input[i][index++] = list.get(j).num;
                input[i][index++] = list.get(j).count;
            }
            for (int j = index; j <= 100; j++) {
                input[i][j] = 0;
            }
        }
        return max;
    }

    private static int sortCol() {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= COL; i++) {
            count = new int[101]; // 1부터 시작하기 때문에
            for (int j = 1; j <= ROW; j++) {
                count[input[j][i]]++;
            }
            sortList();
            max = Math.max(max, list.size() * 2);
            int index = 1;
            for (int j = 0; j < list.size(); j++) {
                input[index++][i] = list.get(j).num;
                input[index++][i] = list.get(j).count;
            }
            for (int j = index; j <= 100; j++) {
                input[j][i] = 0;
            }
        }
        return max;
    }

    private static void addList() {
        list = new ArrayList<MySort>();

        for (int j = 1; j <= 100; j++) {
            if (count[j] > 0) {
                list.add(new MySort(j, count[j]));
            }
        }
    }

    private static void sortList() {
        list = new ArrayList<MySort>();
        addList();
        list.sort((o1, o2) -> {
            if (o1.count == o2.count) return o1.num - o2.num;
            return o1.count - o2.count;
        });
    }
}

class MySort{
    int num;
    int count;

    public MySort(int num, int count) {
        this.num = num;
        this.count = count;
    }
}
