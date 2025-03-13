package divideandconquer.BOJ_2740;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static int[][] A, B;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        st.nextToken();
        K = Integer.parseInt(st.nextToken());
        B = new int[M][K];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<K;j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<K;j++) {
                int temp = 0;
                for(int k=0;k<M;k++) {
                    temp += A[i][k] * B[k][j];
                }
                sb.append(temp).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}