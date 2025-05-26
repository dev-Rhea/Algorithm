package implementation.BOJ_16935;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<R;i++) {
            int T;
            switch(T = Integer.parseInt(st.nextToken())) {
                case 1: operation1(); break;
                case 2: operation2(); break;
                case 3: operation3(); break;
                case 4: operation4(); break;
                case 5: operation5(); break;
                case 6: operation6(); break;
            }
                
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void operation1() {
        int[][] result = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                result[i][j] = map[N - 1 - i][j];
            }
        }
        map = result;
    }

    static void operation2() {
        int[][] result = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                result[i][j] = map[i][M - 1 - j];
            }
        }
        map = result;
    }

    static void operation3() {
        int[][] result = new int[M][N];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                result[i][j] = map[N - 1 - j][i];
            }
        }

        int temp = N;
        N = M;
        M = temp;
        
        map = result;
    }

    static void operation4() {
        int[][] result = new int[M][N];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                result[i][j] = map[j][M - 1 - i];
            }
        }

        int temp = N;
        N = M;
        M = temp;
        
        map = result;
    }

    static void operation5() {
        int[][] result = new int[N][M];
        int halfN = N / 2;
        int halfM = M / 2;
        
        // 1번 그룹 -> 2번 그룹
        for(int i = 0; i < halfN; i++) {
            for(int j = 0; j < halfM; j++) {
                result[i][j + halfM] = map[i][j];
            }
        }
        
        // 2번 그룹 -> 3번 그룹
        for(int i = 0; i < halfN; i++) {
            for(int j = halfM; j < M; j++) {
                result[i + halfN][j] = map[i][j];
            }
        }
        
        // 3번 그룹 -> 4번 그룹
        for(int i = halfN; i < N; i++) {
            for(int j = halfM; j < M; j++) {
                result[i][j - halfM] = map[i][j];
            }
        }
        
        // 4번 그룹 -> 1번 그룹
        for(int i = halfN; i < N; i++) {
            for(int j = 0; j < halfM; j++) {
                result[i - halfN][j] = map[i][j];
            }
        }
        
        map = result;
    }

    static void operation6() {
        int[][] result = new int[N][M];
        int halfN = N / 2;
        int halfM = M / 2;
        
        // 1번 그룹 -> 4번 그룹
        for(int i = 0; i < halfN; i++) {
            for(int j = 0; j < halfM; j++) {
                result[i + halfN][j] = map[i][j];
            }
        }
        
        // 4번 그룹 -> 3번 그룹
        for(int i = halfN; i < N; i++) {
            for(int j = 0; j < halfM; j++) {
                result[i][j + halfM] = map[i][j];
            }
        }
        
        // 3번 그룹 -> 2번 그룹
        for(int i = halfN; i < N; i++) {
            for(int j = halfM; j < M; j++) {
                result[i - halfN][j] = map[i][j];
            }
        }
        
        // 2번 그룹 -> 1번 그룹
        for(int i = 0; i < halfN; i++) {
            for(int j = halfM; j < M; j++) {
                result[i][j - halfM] = map[i][j];
            }
        }
        
        map = result;
    }

}