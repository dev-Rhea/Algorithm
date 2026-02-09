package implementation.BOJ_3678;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static class Node {
        int x, y;
        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[][] map = new int[2001][2001];
    static int[] ans = new int[10001];
    static int[] nums = new int[6];
    static boolean[] visited = new boolean[6];

    public static void main(String[] args) throws IOException {
        find();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int c = Integer.parseInt(br.readLine());

        for(int i=0;i<c;i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(ans[n]).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void find() {
        int dy[] = { -1, 1, 2, 1, -1, -2 };
	    int dx[] = { -1, -1, 0, 1, 1, 0 };

        nums[1] = 1;
        nums[2] = 1;

        int x = 1000;
        int y = 1000;
        int d = 5;

        map[y][x] = 1;
        y = y-1;
        x = x+1;
        map[y][x] = 2;
        ans[1] = 1;
        ans[2] = 2;

        for(int i=2;i<10000;i++) {
            Arrays.fill(visited, false);

            int nd = (d+1) % 6;
            int ny = y + dy[nd];
            int nx = x + dx[nd];

            if(map[ny][nx] != 0) {
                ny = y + dy[d];
                nx = x + dx[d];
                nd = d;
            }

            y = ny;
            x = nx;
            d = nd;
            for(int dir = 0;dir<6;dir++) {
                ny = y + dy[dir];
                nx = x + dx[dir];
                visited[map[ny][nx]] = true;
            }

            int t = 1;
            for(t=1;t<=5;t++) {
                if(!visited[t]) break;
            }

            int min = nums[t];
            for(int j=t+1;j<=5;j++) {
                if(min > nums[j] && !visited[j]) {
                    min = nums[j];
                    t = j;
                }
            }

            map[y][x] = t;
            ans[i+1] = t;
            nums[t]++;
           
        }
    }
}