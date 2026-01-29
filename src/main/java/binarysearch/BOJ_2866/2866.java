package binarysearch.BOJ_2866;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    static int R, C;
    static char[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            String input = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        int left = 0;
        int right = R - 1;
        int ans = 0;

        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(check(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static boolean check(int start) {
        Set<String> set = new HashSet<>();
        
        for(int j = 0; j < C; j++) {
            StringBuilder sb = new StringBuilder();
            for(int i = start; i < R; i++) {
                sb.append(map[i][j]);
            }
            String col = sb.toString();
            if(!set.add(col)) return false;
        }
        return true;
    }
}