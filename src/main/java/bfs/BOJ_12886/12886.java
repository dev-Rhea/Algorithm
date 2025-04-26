package bfs.BOJ_12886;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static boolean[][] visited = new boolean[1501][1501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int sum = A + B + C;

        if(sum % 3 != 0) {
            System.out.println(0);
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{A, B, C});
        visited[A][B] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[0] == now[1] && now[1] == now[2]) {
                System.out.println(1);
                return;
            }import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static boolean[][] visited = new boolean[1501][1501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int sum = A + B + C;

        if(sum % 3 != 0) {
            System.out.println(0);
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{A, B, C});
        visited[A][B] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[0] == now[1] && now[1] == now[2]) {
                System.out.println(1);
                return;
            }

            calculate(queue, now[0], now[1], now[2]);
            calculate(queue, now[0], now[2], now[1]);
            calculate(queue, now[1], now[2], now[0]);

        }
        System.out.println(0);
    }

    static void calculate(Queue<int[]> queue,int x, int y, int z) {
        if(x == y) return;

        int nx = x;
        int ny = y;

        if(x < y) {
            nx = x + x;
            ny = y - x;
        }
        else {
            nx = y + y;
            ny = x - y;
        }

        int[] next = new int[]{nx,  ny, z};
        Arrays.sort(next);

        if(!visited[next[0]][next[1]]) {
            visited[next[0]][next[1]] = true;
            queue.add(new int[]{next[0], next[1], next[2]});
        }
        
    }
}

            calculate(queue, now[0], now[1], now[2]);
            calculate(queue, now[0], now[2], now[1]);
            calculate(queue, now[1], now[2], now[0]);

        }
        System.out.println(0);
    }

    static void calculate(Queue<int[]> queue,int x, int y, int z) {
        if(x == y) return;

        int nx = x;
        int ny = y;

        if(x < y) {
            nx = x + x;
            ny = y - x;
        }
        else {
            nx = y + y;
            ny = x - y;
        }

        int[] next = new int[]{nx,  ny, z};
        Arrays.sort(next);

        if(!visited[next[0]][next[1]]) {
            visited[next[0]][next[1]] = true;
            queue.add(new int[]{next[0], next[1], next[2]});
        }
        
    }
}