package pre.Week7;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class BOJ18352 {
    static int N, M, K, X;
    static List<Integer>[] arr;
    static int[] distance;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new List[N+1];
        distance = new int[N+1];

        for(int i=1;i<=N;i++){
            arr[i] = new ArrayList<>();
        }
        Arrays.fill(distance, Integer.MAX_VALUE); // 배열 초기화

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(X);
        distance[X] = 0;

        List<Integer> ans = new ArrayList<>();

        while(!queue.isEmpty()){ // BFS
            int now = queue.poll();
            if(distance[now] > K){ break;} // 거리 K 넘으면 종료
            if(distance[now] == K){ // 출발시점에 거리가 K 라면 리스트에 넣기
                ans.add(now);
                }
            for(int n : arr[now]){ // 엣지
                if(distance[n] != Integer.MAX_VALUE){ // 방문 체크
                    continue;
            }
                distance[n] = distance[now] + 1; // 다음 위치로 이동
                queue.add(n);
            }
        }
        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        for(int n : ans) {
            sb.append(n).append("\n");
        }
        System.out.println(ans.isEmpty()? -1 : sb.toString());

    }
}
