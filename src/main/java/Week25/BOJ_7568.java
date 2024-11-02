package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7568 {
    static int N;
    static int[][] people;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        people = new int[N][2];
        rank = new int[N];

        // 키, 몸무게 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());
            people[i][1] = Integer.parseInt(st.nextToken());
        }

        // 덩치 비교
        for(int i=0;i<N;i++) {
            int cnt = 1;
            for(int j=0;j<N;j++) {
                if(people[i][0] < people[j][0] && people[i][1] < people[j][1]) {
                    cnt++;
                }
            }
            rank[i] = cnt;
        }

        for(int i=0;i<N;i++) {
            System.out.println(rank[i]);
        }
    }

}
