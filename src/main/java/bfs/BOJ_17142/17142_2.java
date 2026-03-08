package bfs.BOJ_17142;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

class Main {
    static int N, M, min;
    static int[][] map;
    static List<int[]> virus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virus = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        min = Integer.MAX_VALUE;
        choice(0, 0, new ArrayList<>());

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void choice(int start, int cnt, List<Integer> select) {
        if(cnt == M) {
            bfs(select);
            return;
        }

        for(int i=start;i<virus.size();i++) {
            select.add(i);
            choice(i+1, cnt+1, select);
            select.remove(select.size()-1);
        }
    }

    static void bfs(List<Integer> list) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] copy = new int[N][N];
        for(int i=0;i<N;i++) {
            copy[i] = map[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for(int idx : list) {
            int[] v = virus.get(idx);
            queue.add(new int[]{v[0], v[1], 0});
            copy[v[0]][v[1]] = 3;
        }       

        int ans = 0;
        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int d=0;d<4;d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || nx >=  N || ny < 0 || ny >= N) continue;
                
                if(copy[nx][ny] == 0) {
                    copy[nx][ny] = 3;
                    ans = now[2] + 1;
                    queue.add(new int[]{nx, ny, ans});
                }
                else if(copy[nx][ny] == 2) {
                    copy[nx][ny] = 3;
                    queue.add(new int[]{nx, ny, now[2]+1});
                }
            }
        }
        if(isEmpty(copy)) min = Math.min(min, ans);
    }

    static boolean isEmpty(int[][] copy) {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(copy[i][j]== 0) return false;
            }
        }

        return true;
    }
}
