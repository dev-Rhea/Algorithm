import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] cows = new int[N];

        for(int i=0;i<N;i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }

        long min = Long.MAX_VALUE;

        for(int start=0;start<N;start++) {
            long temp = 0;
            boolean[] occupied = new boolean[N];
            
            for(int i=0;i<N;i++) {
                int now = (start + i) % N;

                for(int j=0;j<cows[now];j++) {
                    int dist = 0;
                    int idx = now;
                    
                    while(dist < N) {
                        if(!occupied[idx]) {
                            occupied[idx] = true;
                            temp += (long) dist * dist;
                            break;
                        }
                        
                        idx = (idx + 1) % N;
                        dist++;
                    }
                }
            }
            
            min = Math.min(min, temp);
        }

        System.out.println(min);
    }
}