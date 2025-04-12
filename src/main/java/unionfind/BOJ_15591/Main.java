package unionfind.BOJ_15591;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // 트리 정보
    static class Edge {
        int p, q, r;
        Edge(int p, int q, int r) {
            this.p = p;
            this.q = q;
            this.r = r;
        }
    }

    // 질문정보 
    static class Query {
        int idx, k, video;
        Query(int idx, int k, int video) {
            this.idx = idx;
            this.k = k;
            this.video = video;
        }
    }

    static int[] parent, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        Query[] queries = new Query[Q];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int video = Integer.parseInt(st.nextToken());
            queries[i] = new Query(i, k, video);
        }

        int[] ans = new int[Q];

        // USADO 기준 내림차순 정렬
        edges.sort((e1, e2) -> e2.r - e1.r);
        Arrays.sort(queries, (q1, q2) -> q2.k - q1.k);

        // Union-Find 초기화
        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int edgeIdx = 0;
        for (Query query : queries) {
            while (edgeIdx < edges.size() && edges.get(edgeIdx).r >= query.k) {
                union(edges.get(edgeIdx).p, edges.get(edgeIdx).q); // 연관 동영상 
                edgeIdx++;
            }
            int groupSize = size[find(query.video)];
            ans[query.idx] = groupSize - 1; // 자기 자신 제외
        }

        for (int n : ans) {
            sb.append(n).append("\n");
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) return;

        // 작은 집합을 큰 집합에 붙임 (집합 크기 기준)
        if (size[ra] < size[rb]) {
            parent[ra] = rb;
            size[rb] += size[ra];
        } else {
            parent[rb] = ra;
            size[ra] += size[rb];
        }
    }
}
