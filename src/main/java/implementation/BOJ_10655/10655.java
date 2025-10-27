package implementation.BOJ_10655;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] point = new int[N+1][2];

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for(int i=1;i<N;i++) {
            sum += Math.abs(point[i][0] - point[i+1][0]) + Math.abs(point[i][1] - point[i+1][1]);
        }

        int min = 0;

        for(int i=2;i<N;i++) {
            int dist = (Math.abs(point[i-1][0] - point[i][0]) + Math.abs(point[i-1][1] - point[i][1]))
            + (Math.abs(point[i+1][0] - point[i][0]) + Math.abs(point[i+1][1] - point[i][1]));

            int skip = Math.abs(point[i-1][0] - point[i+1][0]) + Math.abs(point[i-1][1] - point[i+1][1]);

            int diff = dist - skip;

            min = Math.max(min, diff);
        }

        System.out.println(sum - min);
    }
}