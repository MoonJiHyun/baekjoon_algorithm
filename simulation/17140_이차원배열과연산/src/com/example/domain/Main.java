import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static int TIME = 0;
    static int ROW = 3;
    static int COL = 3;
    static int[] count;
    static int[][] input = new int[101][101];
    static PriorityQueue<MySort> queue;
    public static void main(String[] args) throws IOException {
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
            addQueue();
            max = Math.max(max, queue.size() * 2);
            int index = 1;
            while (!queue.isEmpty()) {
                MySort element = queue.poll();
                input[i][index++] = element.num;
                input[i][index++] = element.count;
                if (index > 100) break;
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
            addQueue();
            max = Math.max(max, queue.size() * 2);
            int index = 1;
            while (!queue.isEmpty()) {
                MySort element = queue.poll();
                input[index++][i] = element.num;
                input[index++][i] = element.count;
                if (index > 100) break;
            }
            for (int j = index; j <= 100; j++) {
                input[j][i] = 0;
            }
        }
        return max;
    }

    private static void addQueue() {
        queue = new PriorityQueue<MySort>();

        for (int j = 1; j <= 100; j++) {
            if (count[j] > 0) {
                queue.offer(new MySort(j, count[j]));
            }
        }
    }
}

class MySort implements Comparable<MySort> {
    int num;
    int count;

    public MySort(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(MySort o) {
        if (this.count < o.count) {
            return -1;
        } else if (this.count == o.count) {
            return this.num - o.num;
        }
        return 1;
    }
}
