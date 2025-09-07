package implementation.BOJ_3190;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Snake {
        int x, y, l, t;
        Snake(int x, int y, int l, int t) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.t = t;
        }
    }

    static class Turn {
        int time;
        char dir;
        Turn(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }
    
    static int N, K, L;
    static Queue<Snake> snake;
    static int[][] map;
    static Queue<Turn> queue;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        snake = new LinkedList<>();
        queue = new LinkedList<>();

        K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for(int i=0;i<L;i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            queue.add(new Turn(time, dir));
        }

        System.out.println(move());
    }

    static int move() {
        Deque<int[]> body = new ArrayDeque<>();
        body.add(new int[]{1, 1});

        int d = 0;
        int t = 0;

        while(true) {
            t++;
            
            int[] head = body.peekFirst();
            int nx = head[0] + dx[d];
            int ny = head[1] + dy[d];

            if(nx < 1 || nx > N || ny < 1 || ny > N) break;

            for(int[] b : body) {
                if(b[0] == nx && b[1] == ny) return t;
            }

            body.addFirst(new int[]{nx, ny});

            if(map[nx][ny] == 1) map[nx][ny] = 0;
            else body.removeLast();

            if(!queue.isEmpty() && queue.peek().time == t) {
                Turn turn = queue.poll();

                if(turn.dir == 'L') d = (d + 3) % 4;
                else d = (d + 1) % 4;
            }
        }
        return t;
    }
}