package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, k;
    private static boolean visit[][] = new boolean[2][500001];

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visit[0][n] = true;

        System.out.println(n == k ? 0 : solution());
    }

    private static int solution() {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(n);

        int leng, mod, time = 0;

        while(!queue.isEmpty()) {
            leng = queue.size();
            time++;
            mod = time % 2;

            for (int i = 0; i < leng; i++) {
                int p = queue.poll();
                if (p - 1 >= 0) {
                    if (!visit[mod][p - 1]) {
                        queue.offer(p - 1);
                        visit[mod][p - 1] = true;
                    }
                }
                if (p + 1 <= 500000) {
                    if (!visit[mod][p + 1]) {
                        queue.offer(p + 1);
                        visit[mod][p + 1] = true;
                    }
                }
                if (p * 2 <= 500000) {
                    if (!visit[mod][p * 2]) {
                        queue.offer(p * 2);
                        visit[mod][p * 2] = true;
                    }
                }
            }
            int pos = getPos(time);
            if (pos > 500000) break;
            if (visit[mod][getPos(time)]) return time;
        }

        return -1;
    }

    private static int getPos(int time) {
        return k + (time * (time + 1) / 2);
    }
}
