package backtracking.BOJ_15651;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        visited = new boolean[N];

        permutation(0);
        System.out.println(sb);
    }

    static void permutation(int depth) {

        if (depth == M) {
            for (int n : numbers) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
        }
        else {
            for(int i=1;i<=N;i++) {
                numbers[depth] = i;
                permutation(depth + 1);
            }
        }
    }
}
