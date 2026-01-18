package implementation.BOJ_1022;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    
    static int r1, c1, r2, c2;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        int row = r2 - r1 + 1;
        int col = c2 - c1 + 1;

        map = new int[row][col];
        int max = 0;

        for(int i=r1;i<=r2;i++) {
            for(int j=c1;j<=c2;j++) {
                map[i-r1][j-c1] = getValue(i, j);
                max = Math.max(max, map[i-r1][j-c1]);
            }
        }

        int len = String.valueOf(max).length();

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                String num = String.valueOf(map[i][j]);
                
                int cnt = len - num.length();
                sb.append(" ".repeat(cnt)).append(num).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }

    static int getValue(int i, int j) {
        int n = Math.max(Math.abs(i), Math.abs(j));

        if(j == n) {
            if(i == n) return (int) Math.pow(2 * n + 1, 2);
            return (int) (Math.pow(2*n-1, 2)) + 1 + (n-1)-i;
        }
        else if(i == -n) return (int) Math.pow(2*n-1, 2) + 1 + (2*n-1) + n - j;
        else if(j == -n) return (int) Math.pow(2*n-1, 2) + 1 + (2*n-1) + 2*n + i + n;
        else return (int) Math.pow(2*n-1, 2) + 1 + (2*n-1) + 4*n+j+n;
    }
}