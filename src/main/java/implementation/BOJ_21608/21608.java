package implementation.BOJ_21608;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    
    static class Node {
        int x, y, empty, friend;
        Node(int x, int y, int empty, int friend) {
            this.x = x;
            this.y = y; 
            this.empty = empty;
            this.friend = friend;
        }
    }
    
    static int N;
    static int[][] map;
    static Map<Integer, int[]> hashMap;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int score = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        hashMap = new HashMap<>();
        
        for(int i = 1; i <= N*N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int s = Integer.parseInt(st.nextToken());
            int[] fList = new int[4];
            for(int j = 0; j < 4; j++) {
                fList[j] = Integer.parseInt(st.nextToken());
            }
            hashMap.put(s, fList);
            
            arrangement(s);
        }
        
        sumScore();
        System.out.println(score);
    }
    
    static void arrangement(int student) {
        int[] f = hashMap.get(student);
        List<Node> list = new ArrayList<>();
        
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(map[i][j] != 0) continue;
                
                int fCnt = 0;
                int eCnt = 0;
                
                for(int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    
                    if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                    
                    if(map[nx][ny] == 0) {
                        eCnt++; // 빈자리수
                    } else {
                        for(int k = 0; k < 4; k++) {
                            if(map[nx][ny] == f[k]) {
                                fCnt++; // 인접한 칸 친구수 
                                break;
                            }
                        }
                    }
                }
                list.add(new Node(i, j, eCnt, fCnt));
            }
        }
        
        list.sort((o1, o2) -> {
            if(o1.friend != o2.friend) return o2.friend - o1.friend;
            if(o1.empty != o2.empty) return o2.empty - o1.empty;
            if(o1.x != o2.x) return o1.x - o2.x;
            return o1.y - o2.y;
        });
        
        for(Node node : list) {
            if(map[node.x][node.y] != 0) continue;
            map[node.x][node.y] = student;
            return;
        }
    }
    
    static void sumScore() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int cnt = 0;
                int[] f = hashMap.get(map[i][j]);
                
                for(int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    
                    if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                    
                    for(int k = 0; k < 4; k++) {
                        if(map[nx][ny] == f[k]) {
                            cnt++;
                            break;
                        }
                    }
                }
                
                switch(cnt) {
                    case 1: score += 1; break;
                    case 2: score += 10; break;
                    case 3: score += 100; break;
                    case 4: score += 1000; break;
                }
            }
        }
    }
}