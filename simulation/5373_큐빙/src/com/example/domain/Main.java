package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Cube {
        char[][] top = new char[3][3];
        char[][] bottom = new char[3][3];
        char[][] front = new char[3][3];
        char[][] back = new char[3][3];
        char[][] left = new char[3][3];
        char[][] right = new char[3][3];

        public Cube() {
            for (int i = 0; i < 3; i++) {
                Arrays.fill(top[i], 'w');
                Arrays.fill(bottom[i], 'y');
                Arrays.fill(front[i], 'r');
                Arrays.fill(back[i], 'o');
                Arrays.fill(left[i], 'g');
                Arrays.fill(right[i], 'b');
            }
        }

        public void rotate(char c) {
            switch (c) {
                case 'U': up(); break;
                case 'D': down(); break;
                case 'F': front(); break;
                case 'B': back(); break;
                case 'L': left(); break;
                case 'R': right(); break;
            }
        }

        private void up() {
            char[][] tmp = new char[3][3];
            char[] frontTmp = new char[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = top[2 - j][i];
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    top[i][j] = tmp[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                frontTmp[i] = front[0][i];
            }
            for (int i = 0; i < 3; i++) {
                front[0][i] = right[0][i];
                right[0][i] = back[0][i];
                back[0][i] = left[0][i];
                left[0][i] = frontTmp[i];
            }
        }

        private void down() {
            char[][] tmp = new char[3][3];
            char[] frontTmp = new char[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = bottom[2 - j][i];
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    bottom[i][j] = tmp[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                frontTmp[i] = front[2][i];
            }
            for (int i = 0; i < 3; i++) {
                front[2][i] = left[2][i];
                left[2][i] = back[2][i];
                back[2][i] = right[2][i];
                right[2][i] = frontTmp[i];
            }
        }

        private void front() {
            char[][] tmp = new char[3][3];
            char[] topTmp = new char[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = front[2 - j][i];
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    front[i][j] = tmp[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                topTmp[i] = top[2][i];
            }
            for (int i = 0; i < 3; i++) {
                top[2][i] = left[2 - i][2];
                left[2 - i][2] = bottom[0][2 - i];
                bottom[0][2 - i] = right[i][0];
                right[i][0] = topTmp[i];
            }
        }

        private void back() {
            char[][] tmp = new char[3][3];
            char[] topTmp = new char[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = back[2 - j][i];
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    back[i][j] = tmp[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                topTmp[i] = top[0][i];
            }
            for (int i = 0; i < 3; i++) {
                top[0][i] = right[i][2];
                right[i][2] = bottom[2][2 - i];
                bottom[2][2 - i] = left[2 - i][0];
                left[2 - i][0] = topTmp[i];
            }
        }

        private void left() {
            char[][] tmp = new char[3][3];
            char[] topTmp = new char[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = left[2 - j][i];
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    left[i][j] = tmp[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                topTmp[i] = top[i][0];
            }
            for (int i = 0; i < 3; i++) {
                top[i][0] = back[2 - i][2];
                back[2 - i][2] = bottom[i][0];
                bottom[i][0] = front[i][0];
                front[i][0] = topTmp[i];
            }
        }

        private void right() {
            char[][] tmp = new char[3][3];
            char[] topTmp = new char[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[i][j] = right[2 - j][i];
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    right[i][j] = tmp[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                topTmp[i] = top[i][2];
            }
            for (int i = 0; i < 3; i++) {
                top[i][2] = front[i][2];
                front[i][2] = bottom[i][2];
                bottom[i][2] = back[2 - i][0];
                back[2 - i][0] = topTmp[i];
            }
        }

        public void print() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(top[i][j]);
                }
                System.out.println();
            }
        }
    }

    private static Cube cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int r = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            cube = new Cube();
            for (int i = 0; i < r; i++) {
                String s = st.nextToken();
                char dir = s.charAt(1);
                if (dir == '+') {
                    cube.rotate(s.charAt(0));
                } else { // 반시계방향 == 시계방향으로 3번 돌린 것
                    for (int k = 0; k < 3; k++) {
                        cube.rotate(s.charAt(0));
                    }
                }
            }
            cube.print();
        }
    }
}
