package unionfind.BOJ_1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] node, connect;
    static int N, M, now; 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        node = new int[N + 1];
        connect = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            node[i] = i;       // 자기 자신을 부모로 초기화
            connect[i] = 1;    // 초기 랭크(또는 트리의 높이)를 1로 설정
        }

        // 도시 연결 처리 
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        now = find(Integer.parseInt(st.nextToken())); // 첫번째 도시의 루트를 기준으로 함 
        for(int i=1;i<M;i++) {
            int m = Integer.parseInt(st.nextToken());
            // 같은 집합에 속하지 않는 경우 
            if(find(m) != now) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
 
    static void union(int x, int y) {
        // 각 도시의 root 찾기 
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return;  // 이미 같은 집합이면 합치지 않음
    
        // 비교해서 더 높은 랭크에 할당 
        if (connect[rootX] < connect[rootY]) {
            node[rootX] = rootY;
        } else if (connect[rootX] > connect[rootY]) {
            node[rootY] = rootX;
        } else {
            node[rootY] = rootX;
            connect[rootX]++;  // 두 트리의 랭크가 같으면 하나의 랭크를 증가시킴
        }
    }

    static int find(int x) {
        // 경로 압축 
        // 탐색 중 만난 모든 노드들이 바로 루트를 참조하도록 갱신.
        if(node[x] != x) node[x] = find(node[x]);
        return node[x];
    }
}
