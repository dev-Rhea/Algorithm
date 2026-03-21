import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[] indegree;
    static List<List<Integer>> seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        seq = new ArrayList<>();
        indegree = new int[N+1];

        for(int i=0;i<=N;i++) {
            seq.add(new ArrayList<>());
        }

        Set<Integer> point = new HashSet<>();
        for(int i=0;i<M;i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(line.nextToken());
            int[] singers = new int[len];

            for(int j=0;j<len;j++) {
                singers[j] = Integer.parseInt(line.nextToken());
            }

            for(int j=0;j<len-1;j++) {
                int u = singers[j];
                int v = singers[j+1];

                int key = u * (N+1) + v;
                if(point.contains(key)) continue;
                point.add(key);

                seq.get(u).add(v);
                indegree[v]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++) {
            if(indegree[i] == 0) queue.add(i);
        }

        int cnt = 0;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append('\n');
            cnt++;

            for(int next : seq.get(now)) {
                if(--indegree[next] == 0) queue.add(next);
            }
        }

        System.out.println(cnt == N ? sb.toString() : "0");
    }
}