import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static long[] nums, tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int h = (int)Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[1 << (h + 1)];
        nums = new long[N + 1];

        for(int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }
        init(1, 1, N);

        for(int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) {
                update(1, 1, N, b, c - nums[b]); // 기존 값에 차만 더해서 연산 효율화
                nums[b] = c;
            } else {
                sb.append(query(1, 1, N, b, (int)c)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static long init(int node, int start, int end) {
        if(start == end)
            return tree[node] = arr[start];
        
        return tree[node] = init(node << 1, start, (start + end) >> 1)
                          + init((node << 1) + 1, ((start + end) >> 1) + 1, end);
    }

    static void update(int node, int start, int end, int idx, long diff) {
        if(idx < start || idx > end) return;

        tree[node] += diff;

        if(start != end) {
            update(node << 1, start, (start + end) >> 1, idx, diff);
            update((node << 1) + 1, ((start + end) >> 1) + 1, end, idx, diff);
        }
    }

    static long query(int node, int start, int end, int left, int right) {
        if(right < start || end < left) return 0;

        if(left <= start && end <= right) return tree[node];

        return query(node << 1, start, (start + end) >> 1, left, right)
             + query((node << 1) + 1, ((start + end) >> 1) + 1, end, left, right);
    }
}