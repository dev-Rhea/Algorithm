package implementation.BOJ_8972;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int x, y, m;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C;
    static char[][] map, ans;
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        ans = new char[R][C];

        Queue<Node> arduino = new LinkedList<>();
        int x = 0, y = 0;

        for(int i=0;i<R;i++) {
            String str = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'I') {
                    x = i;
                    y = j;
                }
                if(map[i][j] == 'R'){
                    arduino.add(new Node(i, j));
                }
            }
        }

        String nums = br.readLine();
        Queue<Integer> d = new LinkedList<>();
        for(int i=0;i<nums.length();i++) {
            d.add(nums.charAt(i) - '0');
        }

        int res = move(x, y, arduino, d);

        if(res != -1) System.out.println("kraj " + res);
        else {
            for(int i=0;i<R;i++) {
                for(int j=0;j<C;j++) {
                    System.out.print(ans[i][j]);
                }
                System.out.println();
            }
        }
    }

    static int move(int x, int y, Queue<Node> arduino, Queue<Integer> d) {
        int cnt = 0;
        
        while(!d.isEmpty()) {
            cnt++;
            int dir = d.poll();

            if(dir != 5) {
                x += dx[dir - 1];
                y += dy[dir - 1];
                
                if(check(x, y, arduino)) return cnt;
            }

            arduino = crazy(arduino, x, y);
            if(arduino == null) return cnt;
        }

        setting(x, y, arduino);
        return -1;
    }

    static boolean check(int x, int y, Queue<Node> arduino) {
        for(Node r : arduino) {
            if(r.x == x && r.y == y) return true;
        }
        return false;
    }

    static Queue<Node> crazy(Queue<Node> arduino, int x, int y) {
        int[][] temp = new int[R][C];
        Queue<Node> ardu = new LinkedList<>();

        while(!arduino.isEmpty()) {
            Node now = arduino.poll();

            int min = Integer.MAX_VALUE;
            int minX = 0, minY = 0;

            for(int i=0;i<9;i++) {
                if(i == 4) continue;
                
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                int dist = Math.abs(x - nx) + Math.abs(y - ny);

                if(dist < min) {
                    min = dist;
                    minX = nx;
                    minY = ny;
                }
            }

            if(minX == x && minY == y) {
                return null;
            }
            
            temp[minX][minY] += 1;
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(temp[i][j] == 1) {
                    ardu.add(new Node(i, j));
                }
            }
        }

        return ardu;
    }

    static void setting(int x, int y, Queue<Node> arduino) {
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                ans[i][j] = '.';
            }
        }

        ans[x][y] = 'I';

        for(Node r : arduino) {
            ans[r.x][r.y] = 'R';
        }
    }
}