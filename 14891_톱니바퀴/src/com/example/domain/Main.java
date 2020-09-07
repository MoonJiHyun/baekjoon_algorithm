package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static int GEAR_NUM = 4;
    public static int GEAR_LEN = 8;
    public static LinkedList<Integer>[] GEAR = new LinkedList[GEAR_NUM];
    public static int RIGHT = 2;
    public static int LEFT = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < GEAR_NUM; i++) {
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            GEAR[i] = new LinkedList<>();
            for (int j = 0; j < GEAR_LEN; j++) {
                GEAR[i].add(Integer.parseInt(s[j]));
            }
        }
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            rotateAllGear(gearNum, direction);
//            for (int j = 0; j < GEAR_NUM; j++) {
//                for (int k = 0; k < GEAR_LEN; k++) {
//                    System.out.print(GEAR[j].get(k));
//                }
//                System.out.println();
//            }
        }

        int sum = 0;
        for (int i = 0; i < GEAR_NUM; i++) {
            if (GEAR[i].get(0) == 1) {
                sum += (int) Math.pow(2, i);
            }
        }
        System.out.println(sum);
    }

    public static void rotateAllGear(int gearNum, int direction) {
        rotateLeft(gearNum, direction);
        rotateRight(gearNum, direction);
        rotate(gearNum, direction);
    }

    public static void rotateLeft(int gearNum, int direction) {
        // 좌측 기어
        int lastLeft = GEAR[gearNum].get(LEFT);
        for (int i = gearNum - 1; i >= 0; i--) {
            if (GEAR[i].get(RIGHT).equals(lastLeft)) break;
            lastLeft = GEAR[i].get(LEFT);
            rotateGear(i, gearNum, direction);
        }
    }

    public static void rotate(int gearNum, int direction) {
        rotateGear(gearNum, gearNum, direction);
    }

    public static void rotateRight(int gearNum, int direction) {
        // 우측 기어
        int lastRight = GEAR[gearNum].get(RIGHT);
        for (int i = gearNum + 1; i < GEAR_NUM; i++) {
            if (GEAR[i].get(LEFT).equals(lastRight)) break;
            lastRight = GEAR[i].get(RIGHT);
            rotateGear(i, gearNum, direction);
        }
    }

    public static void rotateGear(int i, int gearNum, int direction) {
        int currDirection = (i % 2 == gearNum % 2) ? direction : direction * -1;
        if (currDirection == -1) {
            GEAR[i].addLast(GEAR[i].pollFirst());
        } else {
            GEAR[i].addFirst(GEAR[i].pollLast());
        }
    }
}
