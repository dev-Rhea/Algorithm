package Tree.BOJ_14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
class Node {
    TreeMap<String, Node> child;
    String name;

    public Node(String name) {
        this.name = name;
        this.child = new TreeMap<>();
    }
}

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        Node root = new Node(""); // 루트 노드 생성

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            Node cur = root; // 경로
            for(int j=0;j<K;j++) {
                String input = st.nextToken();
                // 자식 노드에 없으면 생성
                if(!cur.child.containsKey(input)) {
                    cur.child.put(input, new Node(input));
                }
                // 자식 노드로 이동
                cur = cur.child.get(input);
            }
        }
        dfs(root, 0);
        System.out.println(sb);
    }

    static void dfs(Node node, int depth) {
        for(Node n : node.child.values()) {
            for(int i=0;i<depth;i++) {
                sb.append("--");
            }
            sb.append(n.name).append("\n");
            dfs(n, depth + 1);
        }
    }

}
