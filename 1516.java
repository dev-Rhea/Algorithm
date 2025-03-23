import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int time;
        ArrayList<Integer> map = new ArrayList<>();

        public Node(int time) {
            this.time = time;
        }
    }

    static int[] indegree;
    static int[] answer;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        indegree = new int[N+1];
        answer = new int[N+1];
        graph = new Node[N+1];

        for(int i=1;i<=N;i++) {
            graph[i] = new Node(0);
        }

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            graph[i].time = t;

            while(true) {
                int prev = Integer.parseInt(st.nextToken());

                if(prev == -1) break;

                graph[prev].map.add(i);
                indegree[i]++;
            }
        }

        sorting(N);

        for(int i=1;i<=N;i++) {
            System.out.println(answer[i]);
        }
    }

    static void sorting(int N) {
        Queue<Integer> queue = new LinkedList<>();

        for(int i=1;i<=N;i++) {
            // 루트 노드 큐에 삽입 
            if(indegree[i] == 0) {
                queue.offer(i);
                answer[i] = graph[i].time; // 초기 값으로 건물 짓는 시간 
            }
        }

        while(!queue.isEmpty()) {
            int now = queue.poll(); // 현재 건물 번호 

            for(int n : graph[now].map) { // 연결된 건물 탐색 
                answer[n] = Math.max(answer[n], answer[now]+graph[n].time); // 건물 짓는 시간 갱신 
                indegree[n]--; // 연결된 건물 진입 차수 감소 

                if(indegree[n] == 0) queue.offer(n); // 진입 차수가 0 되면 큐에 추가 
            }
        }
    }
}