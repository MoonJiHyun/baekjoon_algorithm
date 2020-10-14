package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int index;
        int height;
        public Node(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
    private static int N;
    private static int[] arr;
    private static Stack<Node> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                // 스택이 비어있지 않으면
                Node left = stack.peek();
                if (left.height > arr[i]) {
                    sb.append(left.index).append(" ");
                    break;
                }

                // 왼쪽 노드가 더 낮거나 현재 노드와 높이가 같다면 왼쪽 노드는 더이상 필요없어지므로 pop
                stack.pop();
            }
            if (stack.isEmpty()) sb.append(0 + " ");
            stack.push(new Node(i, arr[i]));
        }
        System.out.println(sb.toString());
    }
}
