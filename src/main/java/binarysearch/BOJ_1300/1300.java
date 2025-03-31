package binarysearch.BOJ_1300;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int left = 1;
        int right = k;
        int cnt = 0; // mid 이하의 수를 세기 위한 변수 

        while(left < right) {
            int mid = (left + right) >> 1;
            cnt = 0;

           for(int i=1;i<=N;i++) {
            // mid/i i행에서 mid 이하인 숫자의 갯수 
            cnt += Math.min(mid/i, N);
           }

           if(k <= cnt) right = mid;
           else left = mid + 1;
        }
        System.out.println(left);
    }
}