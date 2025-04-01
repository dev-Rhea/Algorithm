package bruteforce.BOJ_14658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] stars = new int[K][2];

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for(int i=0;i<K;i++) {
            for(int j=i;j<K;j++) {
                int cnt = 0;

                // 좌측 하단 좌표 설정 
                int x = Math.min(stars[i][0], stars[j][0]);
                int y = Math.min(stars[i][1], stars[j][1]);

                // 좌측 하단 좌표를 포함하는 L*L 크기의 사각형 만들기 (트램펄린)
                for(int k=0;k<K;k++) {
                    int nowX = stars[k][0];
                    int nowY = stars[k][1];

                    if(nowX >= x && nowY >= y && nowX <= x+L && nowY <= y+L) cnt++;
                }
                // 가장 많은 별을 포함하면 갱신 
                max = Math.max(cnt, max);
            }
        }
        System.out.println(K - max);
    }
}
