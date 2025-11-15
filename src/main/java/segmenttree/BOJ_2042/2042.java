package segmenttree.BOJ_2042;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static long[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        tree = new long[N*4];
        for(int i=1;i<=N;i++) {
            long temp = Long.parseLong(br.readLine());
            update(1, 1, N, i, temp);
        }

        for(int i=0;i<M+K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) update(1, 1, N, b, c);
            else {
                long res = query(1, 1, N, b, (int) c);
                sb.append(res).append("\n");
            }
        }

        System.out.println(sb);
    }

    static long update(int node, int start, int end, int idx, long value) {
        if(idx < start || idx > end) return tree[node];

        if(start == end) return tree[node] = value;

        int mid = (start + end) / 2;
        long left = update(node*2, start, mid,idx,value);
        long right = update(node*2+1, mid+1, end, idx, value);

        return tree[node] = left+right;
    }

    static long query(int node, int start, int end, int left, int right) {
        if(right < start || end <left) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return query(node*2, start, mid, left, right) + 
                query(node*2+1, mid+1, end, left, right);
    }
}