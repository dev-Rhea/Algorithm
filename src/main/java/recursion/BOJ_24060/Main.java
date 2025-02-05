package recursion.BOJ_24060;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        int[] arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }

        Merge(arr, 0, N-1);

        if(K > 0) {
            System.out.println(-1);
        }
    }

    private static void Merge(int[] arr, int p, int r) {

        if(p == r) {
            return;
        }

        int mid = (p + r) / 2;

        Merge(arr, p, mid);
        Merge(arr, mid + 1, r);

        if(K <= r - p + 1 && K > 0) {
            Arrays.sort(arr, p, r+1);

            System.out.println(arr[p+K-1]);
            K = 0;
        }
        K -= (r - p + 1);
    }

}
