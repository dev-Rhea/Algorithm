package implementation.BOJ_14891;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[][] wheel;
    static int[] dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        wheel = new int[4][8];

        for(int i=0;i<4;i++) {
            String str = br.readLine();
            for(int j=0;j<8;j++) {
                wheel[i][j] = str.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());

        while(K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken());

            dir = new int[4];
            dir[a] = b;
            checkDir(a);
            turn();
        }

        int sum = 0;

        if(wheel[0][0] == 1) sum += 1;
        if(wheel[1][0] == 1) sum += 2;
        if(wheel[2][0] == 1) sum += 4;
        if(wheel[3][0] == 1) sum += 8;

        System.out.println(sum);
    }
    
    static void checkDir(int n) {
        for(int i=n-1;i>=0;i--) {
            if(wheel[i][2] != wheel[i + 1][6]) dir[i] = -dir[i+1];
            else break;
        }
        for(int i=n+1;i<4;i++) {
            if(wheel[i][6] != wheel[i-1][2]) dir[i] = -dir[i-1];
            else break;
        }
    }

    static void turn() {
        for(int j=0;j<4;j++) {
            if(dir[j] != 0) { 
                int[] temp = new int[8];
                
                if(dir[j] == 1) { 
                    for(int i=0;i<8;i++) {
                        temp[(i + 1) % 8] = wheel[j][i];
                    }
                } else { 
                    for(int i=0;i<8;i++) {
                        temp[i] = wheel[j][(i + 1) % 8];
                    }
                }
                
                for(int i=0;i<8;i++) {
                    wheel[j][i] = temp[i];
                }
            }
        }
    }
}