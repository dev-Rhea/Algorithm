package binarysearch.BOJ_2805;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int M;
    static int[] tree;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tree[i]);
        }

        cutting();
    }

    static void cutting() {
        int start = 0;
        int end = max;

        while(start < end) {
            int mid = (start + end) / 2;
            int sum = 0;

            for(int t : tree) {
                if(t - mid > 0) sum += t - mid;
            }

            if(sum >= M) start = mid + 1;
            else end = mid;

        }
        System.out.println(start - 1);
    }
}