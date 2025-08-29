package implementation.BOJ_17144;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int R, C, T;
    static int[][] room;
    static int[] airCleaner;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        room = new int[R][C];
        int[][] copy = new int[R][C];
        airCleaner = new int[2];
        int idx = 0;

        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                
                if(room[i][j] == -1) airCleaner[idx++] = i;
            }
        }

        for(int i=0;i<T;i++) {
            spread();
            cleanup();
        }

        System.out.println(sumDust());
    }

    static void spread() {
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        int[][] copy = new int[R][C];
        copy[airCleaner[0]][0] = -1;
        copy[airCleaner[1]][0] = -1;

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(room[i][j] > 0) {
                    int dust = room[i][j];
                    int cnt = 0;
                    int spreadDust = dust / 5;

                    for(int k=0;k<4;k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
            
                        if(nx >= 0 && nx < R && ny >= 0 && ny < C && room[nx][ny] != -1) {
                            copy[nx][ny] += spreadDust;
                            cnt++;
                        } 
                    }

                    copy[i][j] += dust - (spreadDust * cnt);
                }
            }
        }
        room = copy;
    }

    static void cleanup() {
        acw(); // 반시계
        ccw(); // 시계
    }

    static void acw() {
        int top = airCleaner[0];

        // 위로 이동
        for(int i=top-1;i>0;i--) {
            room[i][0] = room[i-1][0];
        }

        // 좌로 이동
        for(int i=0;i<C-1;i++) {
            room[0][i] = room[0][i+1];
        }

        // 아래로 이동
        for(int i=0;i<top;i++) {
            room[i][C-1] = room[i+1][C-1];
        }

        // 우로 이동
        for(int i=C-1;i>1;i--) {
            room[top][i] = room[top][i-1];
        }

        room[top][1] =  0;
    }

    static void ccw() {
        int bottom = airCleaner[1];

        // 위로 이동
        for(int i=bottom+1;i<R-1;i++) {
            room[i][0] = room[i+1][0];
        }

        // 좌로 이동
        for(int i=0;i<C-1;i++) {
            room[R-1][i] = room[R-1][i+1];
        }

        // 아래로 이동
        for(int i=R-1;i>bottom;i--) {
            room[i][C-1] = room[i-1][C-1];
        }

        // 우로 이동
        for(int i=C-1;i>1;i--) {
            room[bottom][i] = room[bottom][i-1];
        }

        room[bottom][1] =  0;
    }

    static int sumDust() {
        int total = 0;
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(room[i][j] > 0) total += room[i][j];
            }
        }
        return total;
    }
}