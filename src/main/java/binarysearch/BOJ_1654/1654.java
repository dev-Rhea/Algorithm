package binarysearch.BOJ_1654;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, K;
    static int[] lan;
    static long left = 1;
    static long right = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lan = new int[K];

        for(int i=0;i<K;i++) {
            lan[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, lan[i]); // 최대길이 갱신 
        }

        cutting();
    }

    static void cutting() {

        while(left <= right) {
            long mid = (left + right) / 2;
            long line = 0; // 자른 랜선 갯수

            for(int i=0;i<lan.length;i++) line += (lan[i] / mid);

            if(line >= N) left = mid + 1;
            else if(line < N) right = mid - 1;
        }
        System.out.println(right);
    }
}