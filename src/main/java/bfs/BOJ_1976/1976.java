package bfs.BOJ_1976;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static List<int[]>[] city;
    static int[] travel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        city = new List[N];
        travel = new int[M];

        
        for(int i=0;i<N;i++) {
            city[i] = new ArrayList<>();
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                int flag = Integer.parseInt(st.nextToken());

                city[i].add(new int[] {j, flag});
            }
        }   

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            travel[i] = Integer.parseInt(st.nextToken());
        }

        bfs();
    }

    static void bfs() {
        int start = travel[0] - 1;
        boolean[] visited = new boolean[city.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int[] next : city[now]) {
                int nextCity = next[0];
                int link = next[1];

                if(link == 1 && !visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }

        boolean end = true;
        for(int i=0;i<travel.length;i++) {
            if(!visited[travel[i] - 1]) {
                end = false;
                break;
            }
        }
        System.out.println(end ? "YES" : "NO");
    }
}