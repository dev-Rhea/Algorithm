package bruteforce.BOJ_17281;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int ans, N;
    static int[][] score;
    static int[] order = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        score = new int[N][10];
        ans = 0;

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=1;j<=9;j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[4] = 1;

        setOrder(1, 1<<1); 

        System.out.println(ans);
    }

    static void setOrder(int idx, int visit) {
        if(idx == 10) { 
            int temp = play();
            ans = Math.max(ans, temp);
            return;
        }

        if(idx == 4) {
            setOrder(idx + 1, visit);
            return;
        }

        for(int i=2; i<=9; i++) { 
            int bit = 1<<i;
            if((visit & bit) == 0) {  
                visit |= bit; 
                order[idx] = i;
                setOrder(idx+1, visit);
                visit ^= bit; 
            }
        }
    }

    static int play() {
        int total = 0;
        int now = 1;

        for(int i=0; i<N; i++) {
            int out = 0;
            int ground = 0;
            while(out < 3) {
                int player = order[now];
                int shift = score[i][player];

                if(shift == 0) {
                    out++;
                } else {
                    int next = (ground<<shift) + (1<<(shift-1));
                    ground = next & ((1<<3) - 1);  
                    total += Integer.bitCount(next) - Integer.bitCount(ground);  
                }
                
                now = (now % 9) + 1; 
            }
        }
        return total;
    }
}