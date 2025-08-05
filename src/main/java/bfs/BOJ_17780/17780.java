package bfs.BOJ_17780;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int x, y, d;
        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int N, K;
    static int[][] map;
    static List<Integer>[][] game; // 각 칸에 쌓인 말들을 저장
    static Node[] horses; // 각 말의 정보
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        game = new List[N][N];
        horses = new Node[K];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                game[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; 
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            
            horses[i] = new Node(x, y, d);
            game[x][y].add(i); // 말 번호
        }

        System.out.println(move());
    }

    static int move() {
        int turn = 0;
        
        while(turn <= 1000) {
            turn++;
            
            for(int i = 0; i < K; i++) {
                Node h = horses[i];
                
                if(game[h.x][h.y].get(0) != i) continue; // 가장 아래인지 
                
                int nx = h.x + dx[h.d];
                int ny = h.y + dy[h.d];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) {

                    h.d = reverseD(h.d);
                    nx = h.x + dx[h.d];
                    ny = h.y + dy[h.d];
                    
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) continue;
                }
                
                List<Integer> moving= new ArrayList<>();
                boolean found = false;
                for(int j = 0; j < game[h.x][h.y].size(); j++) {
                    if(game[h.x][h.y].get(j) == i) found = true;
                    if(found) {
                        moving.add(game[h.x][h.y].get(j));
                    }
                }
                
                for(int n : moving) game[h.x][h.y].remove(Integer.valueOf(n));
                
                for(int n : moving) {
                    horses[n].x = nx;
                    horses[n].y = ny;
                }
                
                if(map[nx][ny] == 0) { 
                    game[nx][ny].addAll(moving);
                } else if(map[nx][ny] == 1) { 
                    Collections.reverse(moving);
                    game[nx][ny].addAll(moving);
                }
                
                if(game[nx][ny].size() >= 4) {
                    return turn;
                }
            }
        }
        
        return -1;
    }
    
    static int reverseD(int d) {
        if(d == 0) return 1; 
        if(d == 1) return 0; 
        if(d == 2) return 3;
        return 2;
    }
}