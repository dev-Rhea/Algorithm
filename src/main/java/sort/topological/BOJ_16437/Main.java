package sort.topological.BOJ_16437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N]; // 부모 인덱스 저장 
        int[] indegree = new int[N]; // 진입 차수 저장 
        long[] A = new long[N]; // 각 섬의 늑대/양 수 저장 

        for(int i=1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            char t = st.nextToken().charAt(0);
            long a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken()) - 1;

            if(t == 'W') a *= -1; // 늑대인 경우, 음수로 저장 
            parent[i] = p; // i번 섬의 부모 저장 
            A[i] = a; // 해당섬의 동물 수 저장 
            indegree[p]++; // 부모 섬 진입 차수 증가 (자식 섬 존재)
        }

        indegree[0] = -1; // 루트 노드
    
        //  BFS
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i<N;i++) {
            if(indegree[i] == 0) { // 진입 차수가 0인, 리프 노드들 추가 
                A[i] = Math.max(A[i], 0); // 리프 노드가 음수라면, 0으로 갱신 (부모노드로 전달 X)
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int v = queue.poll(); // 큐에 있는 노드들 탐색 시작 

            A[parent[v]] += Math.max(A[v], 0); // 현재 섬의 양수를 부모 섬으로 전달 
            if(--indegree[parent[v]] == 0) queue.offer(parent[v]); // 부모노드 진입 차수 감소 후, 진입 차수가 0이 되면 큐에 추가 (재탐색)
        }
        System.out.println(A[0]); // 1번 섬까지 전달된 양의 수 
    }
}
