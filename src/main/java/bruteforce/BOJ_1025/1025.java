package bruteforce.BOJ_1025;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[][] map;
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                for(int u =-N;u<N;u++) {
                    for(int v=-M;v<M;v++) {
                        if(u == 0 && v == 0) continue;

                        int t = 0;
                        int ni = i;
                        int nj = j;

                        while(ni >= 0 && ni < N && nj >= 0 && nj < M) {
                            t = 10 * t + map[ni][nj];

                            if(Math.abs(Math.sqrt(t) - (int) Math.sqrt(t)) < 1e-10) {
                                max = Math.max(max, t);
                            }
                            ni += u;
                            nj += v;
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}