package com.example.domain;
import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int count = 0;
        int a = 0;
        int b = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        while (b != A.length) {
            if (A[a] < B[b]) {
                a++;
                count++;
            }
            b++;
        }
        return count;
    }
}