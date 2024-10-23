package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_10431 {
    static int P;
    static int[] arr = new int[20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = Integer.parseInt(st.nextToken());

        for(int i=0;i<P;i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            for(int j=0;j<20;j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int cnt = insertionSort(arr);
            System.out.println(T + " " + cnt);
        }
    }

    // 삽입 정렬
    private static int insertionSort(int[] arr) {
        int moveCnt = 0;
        for(int i=1;i<arr.length;i++) {
            int key = arr[i];

            int j = i-1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
                moveCnt++;
            }
            arr[j+1] = key;
        }
        return moveCnt;
    }
}
