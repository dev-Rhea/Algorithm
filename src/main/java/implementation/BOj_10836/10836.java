package implementation.BOj_10836;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[][] map;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        for(int i=0; i<N; i++) {
            Arrays.fill(map[i], 1);
        }
        
        for(int day=0; day<M; day++) {
            st = new StringTokenizer(br.readLine());
            int zeros = Integer.parseInt(st.nextToken());
            int ones = Integer.parseInt(st.nextToken());
            int twos = Integer.parseInt(st.nextToken());
            
            queue = new LinkedList<>();
            
            for(int k=0; k<zeros; k++) queue.add(0);
            for(int k=0; k<ones; k++) queue.add(1);
            for(int k=0; k<twos; k++) queue.add(2);
            
            int[][] energy = new int[N][N];
            
            for(int i=N-1; i>=0; i--) {
                int growth = queue.poll();
                map[i][0] += growth;
                energy[i][0] = growth;
            }
            
            for(int j=1; j<N; j++) {
                int growth = queue.poll();
                map[0][j] += growth;
                energy[0][j] = growth;
            }
            
            for(int i=1; i<N; i++) {
                for(int j=1; j<N; j++) {
                    int max = Math.max(energy[i][j-1],
                                   Math.max(energy[i-1][j-1],
                                   energy[i-1][j]));
                    
                    energy[i][j] = max;
                    map[i][j] += max;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}