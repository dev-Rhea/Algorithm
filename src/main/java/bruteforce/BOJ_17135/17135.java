package bruteforce.BOJ_17135;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, D, cnt;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        cnt = 0;

        map = new int[N+1][M+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] arrow = new int[3];
        setArrow(1, 0, arrow);

        System.out.println(cnt);
    }

    static void setArrow(int start, int cntA, int[] arrow) {
        if(cntA == 3) {
            attack(arrow);
            return;
        }

        for(int i=start;i<=M;i++) {
            arrow[cntA] = i;
            setArrow(i+1, cntA+1, arrow);
        }
    }

    static int calD(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static void attack(int[] arrow) {
        int c = 0;
        int[][] status = new int[N+1][M+1];

        for(int line = N+1; line > 1; line--) {
            for(int a=0;a<3;a++) {
                int ar = arrow[a];
                
                for(int d=1;d<=D;d++) {
                    int result = attackAtDistance(status, ar, d, line);
                    if(result < 0) continue;
                    c += result;
                    break;
                }
            }
        }

        cnt = Math.max(cnt, c);
    }

    static int attackAtDistance(int[][] status, int ar, int d, int line) {
        for(int j = ar - d; j <= ar + d; j++) {
            if(j < 1 || j > M) continue;
            
            int i = line - (d - Math.abs(j - ar));
            
            if(i < 1 || i >= line || map[i][j] == 0) continue;
            
            if(status[i][j] == 0) {
                status[i][j] = line;
                return 1;
            } else if(status[i][j] == line) {
                return 0;
            }
        }
        return -1;
    }
}