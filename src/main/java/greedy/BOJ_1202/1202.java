package greedy.BOJ_1202;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static class Treasure implements Comparable<Treasure>{
        int M;
        int V;

        Treasure(int M, int V) {
            this.M = M;
            this.V = V;
        }

        @Override
        public int compareTo(Treasure o) {
            return this.M - o.M;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Treasure[] treasure = new Treasure[N];
        int[] bags = new int[K];

        // 보석 정보
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            treasure[i] = new Treasure(m, v);
        }

        // 가방 무게 정보 
        for(int i=0;i<K;i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(treasure);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2 - o1));

        long ans = 0;
        int idx = 0; // 현재 가방 위치 

        for(int i=0;i<K;i++) {
            while(idx < N && treasure[idx].M <= bags[i]) {
                pq.add(treasure[idx].V);
                idx++;
            }
            if(!pq.isEmpty()) ans += pq.poll();
        }

        System.out.println(ans);
    }
}