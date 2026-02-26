package bitmask.BOJ_15661;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;

        // mask: N명의 사람 중 하나 
        for(int mask=0;mask<(1 << N)-1;mask++) {
            int start = 0;
            int link = 0;

            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(i == j) continue;

                    boolean startI = (mask & (1 << i)) != 0;
                    boolean startJ = (mask & (1 << j)) != 0;

                    if(startI && startJ) start += map[i][j];
                    else if(!startI && !startJ) link += map[i][j];
                }
            }

            ans = Math.min(ans, Math.abs(start - link));
            if(ans == 0) break;
        }

        System.out.println(ans);
    }
}