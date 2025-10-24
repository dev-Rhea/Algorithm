package implementation.BOJ_18808;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node{
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, K, sum;
    static int[][] map;
    static Queue<int[][]> stickers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sum = 0;
        map = new int[N][M];
        stickers = new LinkedList<>();

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];
            for(int j=0;j<r;j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<c;k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            canAttach(sticker);
        }

        System.out.println(sum);
    }

    static void canAttach(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        for(int d=0;d<4;d++) {
            if(r > N || c > M) {
                sticker = turn(sticker);

                r = sticker.length;
                c = sticker[0].length;
                continue;
            }

            for(int i=0;i<=N-r;i++) {
                for(int j=0;j<=M-c;j++){
                    if(check(sticker, i, j)) {
                        attach(sticker, i, j);
                        return;
                    }
                }
            }

            sticker = turn(sticker);
            r = sticker.length;
            c = sticker[0].length;
        }
    }

    static boolean check(int[][] sticker, int x, int y) {
        int n = sticker.length;
        int m = sticker[0].length;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(sticker[i][j] == 1 && map[x+i][y+j] == 1) return false;
            }
        }
        
        return true;
    }

    static int[][] turn(int[][] sticker) {
        int n = sticker.length;
        int m = sticker[0].length;
        int[][] copy = new int[m][n];

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                copy[j][n-1-i] = sticker[i][j];
            }
        }
        return copy;
    }

    static void attach(int[][] sticker, int x, int y) {
        int n = sticker.length;
        int m = sticker[0].length;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(sticker[i][j] == 1) {
                    map[x+i][y+j] = 1;
                    sum++;
                }
            }
        }
    }
}