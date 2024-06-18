package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15723 {
    static int n, m;
    static int[][] map;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[26][26];

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                if(i != j){
                    map[i][j] = 1000;
                }
            }
        }

        // 플로이드 워셜
        for(int k=0;k<26;k++){
            for(int i=0;i<26;i++){
                if(i == k) continue;
                for(int j=0;j<26;j++){
                    if(i == j|| k == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = st.nextToken().charAt(0) - 'a';
            st.nextToken();
            int to = st.nextToken().charAt(0) - 'a';
            if(map[from][to] != 0 && map[from][to] < 1000){
                System.out.println("true");
            }
            else{
                System.out.println("false");
            }
        }

    }
}
