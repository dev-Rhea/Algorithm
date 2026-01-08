package implementation.BOJ_20055;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K, time;
    static int[] strength;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        strength = new int[2*N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=2*N;i++) {
            strength[i] = Integer.parseInt(st.nextToken());
        }

        robot = new boolean[N+1];
        time = 0;
        
        while(true) {
            time++;
            
            rotation();
            
            move();
            
            up();
            
            if(check()) break;
        }

        System.out.println(time);
    }

    static void rotation() {
        int temp = strength[2*N];
        for(int i=2*N;i>=2;i--) {
            strength[i] = strength[i-1];
        }
        strength[1] = temp;
        
        for(int i=N;i>=2;i--) {
            robot[i] = robot[i-1];
        }
        robot[1] = false;
        
        robot[N] = false;
    }

    static void move() {
        for(int i=N-1;i>=1;i--) {
            if(robot[i] && !robot[i+1] && strength[i+1] >= 1) {
                robot[i] = false;
                robot[i+1] = true;
                strength[i+1]--;
            }
        }
        
        robot[N] = false;
    }

    static void up() {
        if(strength[1] >= 1) {
            robot[1] = true;
            strength[1]--;
        }
    }

    static boolean check() {
        int cnt = 0;
        for(int i=1;i<=2*N;i++) {
            if(strength[i] == 0) cnt++;
        }
        return cnt >= K;
    }
}