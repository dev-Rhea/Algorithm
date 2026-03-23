import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[N+1];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            indegree[B]++;
        }

        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++) {
            if(indegree[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();
            ans.add(now);

            for(int next : graph.get(now)) {
                if(--indegree[next] == 0) queue.add(next);
            }
        }

        for(int i=0;i<ans.size();i++) {
            sb.append(ans.get(i)).append(' ');
        }

        System.out.println(sb);
    }
}