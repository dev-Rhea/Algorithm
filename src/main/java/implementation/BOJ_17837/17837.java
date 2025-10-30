package implementation.BOJ_17837;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static class Horse {
        int x;
        int y;
        int d;

        public Horse(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int N, K;
    static int[][] map;
    static ArrayList<Integer>[][] list;
    static Horse[] horses;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        list = new ArrayList[N+1][N+1];
        horses = new Horse[K+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                list[i][j] = new ArrayList<>();
            }
        }

        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(x, y, d);
            list[x][y].add(i);
        }

        int t = 0;
        boolean isEnd = false;
        while(++t <= 1000) {
            if(!moveHorses()) {
                isEnd = true;
                break;
            }
        }

        System.out.println(isEnd ? t : -1);
    }

    static boolean moveHorses() {
        for(int i=1; i<=K; i++) {
            Horse h = horses[i];
            ArrayList<Integer> moves = new ArrayList<>();
            int x = h.x;
            int y = h.y;

            int now = list[x][y].size();
            for(int j=0; j<list[x][y].size(); j++) {
                if(list[x][y].get(j) == i) {
                    now = j;
                }
                if(j >= now) {
                    moves.add(list[x][y].get(j));
                }
            }

            int nx = x + dx[h.d];
            int ny = y + dy[h.d];

            if(nx < 1 || nx > N || ny < 1 || ny > N || map[nx][ny] == 2) {
                h.d = (h.d % 2 != 0) ? h.d + 1 : h.d - 1;
                nx = x + dx[h.d];
                ny = y + dy[h.d];
                if(nx < 1 || nx > N || ny < 1 || ny > N || map[nx][ny] == 2) continue;
            }

            if(map[nx][ny] == 0) {
                for(Integer num : moves) {
                    list[nx][ny].add(num);
                    horses[num].x = nx;
                    horses[num].y = ny;
                }
            } else if(map[nx][ny] == 1) {
                for(int j=moves.size()-1; j>=0; j--) {
                    list[nx][ny].add(moves.get(j));
                    horses[moves.get(j)].x = nx;
                    horses[moves.get(j)].y = ny;
                }
            }

            for(int j=list[x][y].size()-1; j>=now; j--) {
                list[x][y].remove(j);
            }

            if(list[nx][ny].size() >= 4) {
                return false;
            }
        }
        return true;
    }
}