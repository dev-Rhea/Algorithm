package twopointer.BOJ_2923;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[101];
        int[] B = new int[101];
        int[] AA = new int[101];
        int[] BB = new int[101];

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken())]++;
            B[Integer.parseInt(st.nextToken())]++;

            System.arraycopy(A, 0, AA, 0, 101);
            System.arraycopy(B, 0, BB, 0, 101);

            int left = 1, right = 100, max = 0;

            while (true) {
                while (left <= 100 && AA[left] == 0) left++;
                while (right >= 1 && BB[right] == 0) right--;
                if (left > 100 || right < 1) break;

                max = Math.max(max, left + right);
                int min = Math.min(AA[left], BB[right]);
                AA[left] -= min;
                BB[right] -= min;
            }

            sb.append(max).append('\n');
        }

        System.out.print(sb);
    }
}