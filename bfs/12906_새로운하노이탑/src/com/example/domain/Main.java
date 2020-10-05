package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Hanoi {
        Stack<Character>[] tower;

        public Hanoi() {
            this.tower = new Stack[3];
            for (int i = 0; i < 3; i++) {
                this.tower[i] = new Stack<>();
            }
        }

        public Hanoi(Hanoi prev) {
            this.tower = new Stack[3];

            for (int i = 0; i < 3; i++) {
                this.tower[i] = new Stack<>();
                for (char c : prev.tower[i]) {
                    this.tower[i].push(c);
                }
            }
        }

        public String getStatus() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(this.tower[i]);
            }
            return sb.toString();
        }
    }

    private static Queue<Hanoi> queue = new LinkedList<>();
    private static Set<String> set = new HashSet<>();
    private static String result;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int countA = 0, countB = 0, countC = 0;

        Hanoi start = new Hanoi();

        for (int i = 0; i < 3; i++) {
             st = new StringTokenizer(br.readLine());
             if (Integer.parseInt(st.nextToken()) == 0) continue;

             char[] arr = st.nextToken().toCharArray();

             for (char c : arr) {
                 switch (c) {
                     case 'A':
                         countA++;
                         break;
                     case 'B':
                         countB++;
                         break;
                     case 'C':
                         countC++;
                         break;
                 }
                 start.tower[i].push(c);
             }
        }

        Hanoi end = new Hanoi();

        for(int i = 0; i < countA; i++) {
            end.tower[0].push('A');
        }

        for(int i = 0; i < countB; i++) {
            end.tower[1].push('B');
        }

        for(int i = 0; i < countC; i++) {
            end.tower[2].push('C');
        }

        result = end.getStatus();

        queue.offer(start);
        set.add(start.getStatus());

        bfs();

        System.out.println(count);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Hanoi now = queue.poll();
                if (now.getStatus().equals(result)) return;
                for (int from = 0; from < 2; from++) {
                    for (int to = 0; to < 3; to++) {
                        hanoiTower(now, to, from);
                        hanoiTower(now, from, to);
                    }
                }
            }
            count++;
        }
    }

    private static void hanoiTower(Hanoi now, int from, int to) {
        if (!now.tower[to].isEmpty()) {
            Hanoi next = new Hanoi(now);
            next.tower[from].push(next.tower[to].pop());

            if (set.contains(next.getStatus())) return;

            set.add(next.getStatus());
            queue.offer(next);
        }
    }
}
