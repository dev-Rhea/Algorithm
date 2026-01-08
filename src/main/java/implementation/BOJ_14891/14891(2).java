package implementation.BOJ_14891;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static char[][] cycle = new char[5][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i=1;i<=4;i++) {
            String input = br.readLine();
            for(int j=1;j<=8;j++) {
                cycle[i][j] = input.charAt(j-1);
            }
        }

        int K = Integer.parseInt(br.readLine());

        while(K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            check(num, dir);
        }

        int sum = 0;
        if(cycle[1][1] == '1') sum += 1;
        if(cycle[2][1] == '1') sum += 2;
        if(cycle[3][1] == '1') sum += 4;
        if(cycle[4][1] == '1') sum += 8;
        System.out.println(sum);
    }

    static void check(int num, int dir) {
        int[] dirs = new int[5];
        dirs[num] = dir;
        
        // 왼쪽 전파
        for(int i=num-1;i>=1;i--) {
            if(cycle[i][3] != cycle[i+1][7]) {
                dirs[i] = -dirs[i+1];
            } else break;
        }
        
        // 오른쪽 전파
        for(int i=num+1;i<=4;i++) {
            if(cycle[i-1][3] != cycle[i][7]) {
                dirs[i] = -dirs[i-1];
            } else break;
        }
        
        // 모든 톱니바퀴 동시 회전
        for(int i=1;i<=4;i++) {
            if(dirs[i] != 0) {
                rotation(i, dirs[i]);
            }
        }
    }

    static void rotation(int num, int dir) {
        if(dir == 1) { // 시계방향
            char temp = cycle[num][8];
            for(int i=8;i>=2;i--) {
                cycle[num][i] = cycle[num][i-1];
            }
            cycle[num][1] = temp;
        }
        else { // 반시계방향
            char temp = cycle[num][1];
            for(int i=1;i<=7;i++) {
                cycle[num][i] = cycle[num][i+1];
            }
            cycle[num][8] = temp;
        }
    }
}