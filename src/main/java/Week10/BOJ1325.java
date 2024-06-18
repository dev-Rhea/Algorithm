package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1325 {
    static int N, M; // 노드, 엣지
    static boolean[] visit; // 중복 방문 검사
    static List<Integer>[] map; // 인접 리스트
    static int[] trust; // 신뢰도 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trust = new int[N + 1];
        visit = new boolean[N + 1];
        map = new ArrayList[N + 1];

        for(int i=1;i<=N;i++){
            map[i] = new ArrayList<>(); // 인접 리스트 초기화
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(br.readLine()); // 시작 노드
            int end = Integer.parseInt(br.readLine()); // 마지막 노드

            map[start].add(end);
        }
        // 노드 방문체크하면서 탐색
        for(int i=1;i<=N;i++) {
            visit = new boolean[N + 1];
            BFS(i);
        }
        int ans = 0;
        for(int i=1;i<=N;i++) {
            ans = Math.max(ans, trust[i]); // 최대 해킹 수 탐색
            // 최대값과 동일한 경우

        }
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=N;i++) {
            if(trust[i] == ans) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void BFS(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n); // 노드
        visit[n] = true; // 방문 체크
        // queue가 비어있을 때 까지 반복
        while(!queue.isEmpty()){
            int now = queue.poll(); // 큐의 최상위 값 탐색
            for(int i=0;i<map[now].size();i++) {
                int next = map[now].get(i);
                if(!visit[next]) { // 방문하지 않았다면
                    visit[next] = true; // 방문 후
                    queue.add(next); // 큐에 넣기
                    trust[next]++; // 신뢰 관계 추가
                }
            }
        }
    }
}
