package dfs.BOJ_2310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int to;
    Node next;
    Node(int to, Node next) {
        this.to = to;
        this.next = next;
    }
}

public class Main {

    static int n;
    static int[] arr;
    static Node[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while((n = Integer.parseInt(br.readLine())) != 0) {
            arr = new int[n + 1];
            adj = new Node[n + 1];
            visited = new boolean[n + 1];

            for(int i=1;i<=n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                char type = st.nextToken().charAt(0);
                int cost = Integer.parseInt(st.nextToken());

                if(type == 'T') arr[i] -= cost;
                else arr[i] = cost;

                int v;
                while((v = Integer.parseInt(st.nextToken())) != 0) adj[i] = new Node(v, adj[i]);
            }

            visited[1] = true;
            System.out.println(dfs(1, 0) ? "Yes":"No");
        }
    }

    static boolean dfs(int u, int money) {
        if(arr[u] < 0) money += arr[u];
        else if(arr[u] > money) money = arr[u];
        
        if(money < 0) return false;

        if(u == n) return true;

        for(Node node = adj[u];node != null;node = node.next) {
            int v = node.to;
            if(!visited[v]) {
                visited[v] = true;
                if(dfs(v, money)) return true;
                visited[v] = false;
            }
        }
        return false;
    }
}
