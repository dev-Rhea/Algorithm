package binarysearch.BOJ_2110;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] homes = new int[N];

        for(int i=0;i<N;i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(homes);

        int left = 1;
        int right = homes[N-1];

        while(left <= right) {
            int mid = (left + right) / 2;
            int sharing = 0; // 공유기 설치
            int cnt = 1; // 공유기 수 
            
            for(int i=1;i<N;i++) {
                if(homes[i] - homes[sharing] >= mid) { // 현재 집과 공유기 설치된 집 사이의 거리가 mid 이상이면 공유기 설치 
                    sharing = i;
                    cnt++;
                }
            }

            if(cnt < C) { // 공유기 갯수가 부족하다면 
                right = mid - 1; // 공유기 거리 즐인 후 다시 탐색 
                continue;
            }
            left = mid + 1; // 공유기 수가 더 많다면, 공유기 거리를 늘림
            
        }
        System.out.println(left - 1);
    }
}