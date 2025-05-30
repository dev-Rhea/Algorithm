package implementation.BOJ_16967;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] B = new int[H+X][W+Y];

        for(int i = 0; i < H+X; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W+Y; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = X; i < H; i++) {
            for(int j = Y; j < W; j++) {
                B[i][j] = B[i][j] - B[i-X][j-Y];
            }
        }

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                sb.append(B[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}