package bfs.BOJ_4991;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Cleaning{
        int x, y, cnt;
        int visited;
        
        Cleaning(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.visited = 0;
        }
        
        Cleaning(int x, int y, int cnt, int visited) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.visited = visited;
        }
    }

    static int w, h;
    static char[][] map;
    static List<Cleaning> cleaning;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            
            if(w == 0 && h == 0) break;

            cleaning = new ArrayList<>();
            int sx = 0;
            int sy = 0;

            map = new char[h][w];
            for(int i = 0; i < h; i++) {
                String input = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = input.charAt(j);

                    if(map[i][j] == 'o') {
                        sx = i;
                        sy = j;
                    }
                    if(map[i][j] == '*') {
                        cleaning.add(new Cleaning(i, j, 0));
                    }
                }
            }

            System.out.println(bfs(sx, sy));
        }
    }

    static int bfs(int x, int y) {
        if(cleaning.isEmpty()) return 0;
        
        Queue<Cleaning> queue = new LinkedList<>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int target = (1 << cleaning.size()) - 1;
        boolean[][][] visited = new boolean[h][w][1 << cleaning.size()];

        queue.add(new Cleaning(x, y, 0, 0));
        visited[x][y][0] = true;

        while(!queue.isEmpty()) {
            Cleaning now = queue.poll();
            
            if(now.visited == target) {
                return now.cnt;
            }

            for(int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(map[nx][ny] == 'x') continue;
                
                int newVisited = now.visited;
                
                for(int i = 0; i < cleaning.size(); i++) {
                    int cx = cleaning.get(i).x;
                    int cy = cleaning.get(i).y;
                    
                    if(nx == cx && ny == cy) {
                        newVisited |= (1 << i);
                        break;
                    }
                }
                
                if(!visited[nx][ny][newVisited]) {
                    visited[nx][ny][newVisited] = true;
                    queue.add(new Cleaning(nx, ny, now.cnt + 1, newVisited));
                }
            }
        }
        
        return -1;
    }
}