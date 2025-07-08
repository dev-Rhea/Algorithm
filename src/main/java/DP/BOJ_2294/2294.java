package DP.BOJ_2294;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        for(int i=0;i<n;i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coin);

        int[] arr = new int[k+1];
        Arrays.fill(arr, Integer.MAX_VALUE-1);
        arr[0] = 0;

        /**
         * arr[j] -> j원을 만들기 위해 필요한 최소 동전 수
         * arr[j - coin[i]] -> i번째 동전을 사용했을 때의 동전 수 
         */

        for(int i=0;i<n;i++) { // 각 동전 종류 별로 사용 가능한 상황 처리 
            for(int j=coin[i];j<=k;j++) { // i 번째 동전 미만 금액은 만들 수 없으므로 coin[i]부터 시작 
                arr[j] = Math.min(arr[j], arr[j - coin[i]] + 1);
            }
        }

        System.out.println(arr[k] == Integer.MAX_VALUE-1 ? -1 : arr[k]);
    }
}