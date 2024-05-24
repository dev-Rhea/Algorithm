package Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931 {
    static int N;
    static int[][] arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][2];
        int cnt = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        // 정렬 (겹치지 않게)
        Arrays.sort(arr, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int end = -1; // 종료시간 기록

        // 그리디
        for(int i=0;i<N;i++){
            // 회의 시작 시간과 end 비교
            if(arr[i][0] >= end){
                end = arr[i][1];
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
