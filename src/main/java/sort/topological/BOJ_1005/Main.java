package sort.topological.BOJ_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int time, max = 0;
        boolean check = false;
        Queue<Node> nodes = new LinkedList<>();

        Node(int t) {
            time = t;
        }

        void addNodes(Node n) {
            nodes.add(n);
        }
    }

    static Node[] buildings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            buildings = new Node[n];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < buildings.length; i++) {
                buildings[i] = new Node(Integer.parseInt(st.nextToken()));
            }
            
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                buildings[to].addNodes(buildings[from]);
            }
            
            int target = Integer.parseInt(br.readLine()) - 1;
            sb.append(bfs(buildings[target])).append("\n");
        }
        
        System.out.println(sb);
    }

    static int bfs(Node node) {
        if(!node.check) {
            while(!node.nodes.isEmpty()) {
                int t = bfs(node.nodes.remove());
                node.max = t > node.max ? t : node.max;
            }
        }

        node.check = true;

        return node.max + node.time;
    }
    
}