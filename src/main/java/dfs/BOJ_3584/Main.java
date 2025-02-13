package dfs.BOJ_3584;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int i=0;i<T;i++) {
            N = sc.nextInt();
            parent = new int[N + 1];

            for(int j=1;j<=N-1;j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                parent[b] = a;
            }

            int node1 = sc.nextInt();
            int node2 = sc.nextInt();

            visited = new boolean[N + 1];
            findParent(node1);
            System.out.println(sameParent(node2));
        }
    }

    static void findParent(int node) {
        int n = node;
        while (n != 0) {
            visited[n] = true;
            n = parent[n];
        }
    }

    static int sameParent(int node) {
        int n = node;
        int answer = 0;

        while (n != 0) {
            if(visited[n]) {
                answer = n;
                break;
            }
            n  = parent[n];
        }
        return answer;
    }
}