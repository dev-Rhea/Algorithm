import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Move implements Comparable<Move>{
        int n, sx, sy, ex, ey, dist;
        Move(int n, int sx, int sy, int ex, int ey, int dist) {
            this.n = n;
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.dist = dist;
        }

        @Override
        public int compareTo(Move o) { 
            if(this.dist != o.dist) return this.dist - o.dist;
            if(this.sx != o.sx) return this.sx - o.sx;
            return this.sy - o.sy;
        }
    }

    static int N, M, fuel;
    static int[][] map;
    static List<Move> moves;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int s, e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        moves = new ArrayList<>();

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            moves.add(new Move(i, sx, sy, ex, ey, 0));
        }

        for(int i=0;i<M;i++) {
            Move nearest = findNearestMove();
            
            if(nearest == null) {
                System.out.println(-1);
                return;
            }
            
            int distToDest = drive(nearest);
            
            if(distToDest == -1) {
                System.out.println(-1);
                return;
            }
            
            fuel += distToDest * 2;
            s = nearest.ex;
            e = nearest.ey;
            
            moves.remove(nearest);
        }

        System.out.println(fuel);
    }

    static Move findNearestMove() {
        PriorityQueue<Move> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N+1][N+1];
        
        pq.add(new Move(-1, s, e, 0, 0, 0));
        visited[s][e] = true;
        
        while(!pq.isEmpty()) {
            Move cur = pq.poll();
            
            for(Move m : moves) {
                if(m.sx == cur.sx && m.sy == cur.sy) {
                    if(fuel < cur.dist) return null;
                    
                    fuel -= cur.dist;
                    return m;
                }
            }
            
            for(int i=0;i<4;i++) {
                int nx = cur.sx + dx[i];
                int ny = cur.sy + dy[i];
                
                if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                if(map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                pq.add(new Move(-1, nx, ny, 0, 0, cur.dist + 1));
            }
        }
        
        return null;
    }

    static int drive(Move target) {
        if(target.sx == target.ex && target.sy == target.ey) return 0;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];
        
        queue.add(new int[]{target.sx, target.sy, 0});
        visited[target.sx][target.sy] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], dist = now[2];
            
            for(int i=0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                if(map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;
                
                if(nx == target.ex && ny == target.ey) {
                    int distToDest = dist + 1;
                    
                    if(fuel < distToDest) return -1;
                    
                    fuel -= distToDest;
                    return distToDest;
                }
                
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, dist + 1});
            }
        }
        
        return -1;
    }
}