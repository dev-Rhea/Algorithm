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
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++) {
            int n  = Integer.parseInt(br.readLine());

            int[] prev = new int[n+1];
            int[] indegree = new int[n+1];
            boolean[][] higher = new boolean[n+1][n+1]; // a -> b
            List<List<Integer>> graph = new ArrayList<>();
            for(int i=0;i<=n;i++) {
                graph.add(new ArrayList<>());
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1;i<=n;i++) {
                prev[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1;i<=n;i++) {
                for(int j=i+1;j<=n;j++) {
                    graph.get(prev[i]).add(prev[j]);
                    indegree[prev[j]]++;
                    higher[prev[i]][prev[j]] = true;

                }
            }
            

            int m = Integer.parseInt(br.readLine());
            for(int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(higher[a][b]) {
                    graph.get(a).remove(Integer.valueOf(b));
                    indegree[b]--;
                    graph.get(b).add(a);
                    indegree[a]++;
                    higher[a][b] = false;
                    higher[b][a] = true;
                }
                else {
                    graph.get(b).remove(Integer.valueOf(a));
                    indegree[a]--;
                    graph.get(a).add(b);
                    indegree[b]++;
                    higher[b][a] = false;
                    higher[a][b] = true;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for(int i=1;i<=n;i++) {
                if(indegree[i] == 0) queue.add(i);
            }

            List<Integer> ans = new ArrayList<>();
            boolean ambi = false;
            while(!queue.isEmpty()) {
                if(queue.size() > 1) ambi = true;
                int now = queue.poll();
                ans.add(now);

                for(int next : graph.get(now)) {
                    if(--indegree[next] == 0) queue.add(next);
                }
            }
            
            if(ans.size() != n) sb.append("IMPOSSIBLE").append('\n');
            else if(ambi) sb.append('?').append('\n');
            else {
                for(int i=0;i<n;i++) {
                    sb.append(ans.get(i));
                    if(i < n-1) sb.append(' ');
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }
}