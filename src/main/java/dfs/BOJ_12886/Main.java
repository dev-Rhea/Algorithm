package dfs.BOJ_12886;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int ans = 0;
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

        dfs(A, B, C);
        System.out.println(ans);
    }

    static void dfs(int a, int b, int c) {
        int[] arr = {a, b, c};

        Arrays.sort(arr);
        a = arr[0];
        b = arr[1];
        c = arr[2];

        if(visited[a][b]) return;
        visited[a][b] = true;

        if(a == b && b == c) {
            ans = 1;
            return;
        }

        if(a != b) dfs(a + a, b - a, c);
        if(b != c) dfs(a, b + b, c - b);
        if(a != c) dfs(a + a, b, c - a);
    }
}
