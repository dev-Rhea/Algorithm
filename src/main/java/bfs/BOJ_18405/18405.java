package bfs.BOJ_18405;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Virus {
        int x, y, n;
        
        Virus(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }
    
    static int N, K, S;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][N+1];
        ArrayList<Virus> viruses = new ArrayList<>();
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) {
                    viruses.add(new Virus(i, j, map[i][j]));
                }
            }
        }
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        int tx = Integer.parseInt(st.nextToken());
        int ty = Integer.parseInt(st.nextToken());
        
        Collections.sort(viruses, (o1, o2) -> o1.n - o2.n);
        
        Queue<Virus> queue = new LinkedList<>(viruses);
        
        for(int time = 0; time < S; time++) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                Virus now = queue.poll();
                
                for(int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    
                    if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                    if(map[nx][ny] == 0) {
                        map[nx][ny] = now.n;
                        queue.add(new Virus(nx, ny, now.n));
                    }
                }
            }
        }
        
        System.out.println(map[tx][ty]);
    }
}