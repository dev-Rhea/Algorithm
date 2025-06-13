package backtracking.BOJ_2239;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int[][] sdoku;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sdoku = new int[9][9];

        for(int i=0;i<9;i++) {
            String input = br.readLine();
            for(int j=0;j<9;j++) {
                sdoku[i][j] = input.charAt(j) - '0';
            } 
        }

        find(0);

        for(int[] row : sdoku) {
            for(int c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    static void find(int d) {
        if(d == 81) {
            check = true;
            return;
        }
        
        int u = d / 9;
        int v = d % 9;

        if(sdoku[u][v] != 0) find(d+1);
        else {
            for(int i=1;i<10;i++) {
                if(!isValid(u, v, i)) continue;
                sdoku[u][v] = i;
                find(d + 1);

                if(check) return;
                sdoku[u][v] = 0;
            }
        }

    }

    static boolean isValid(int x, int y, int n) {
        for(int i=0;i<9;i++) {
            if(sdoku[i][y] == n || sdoku[x][i] == n) return false;
        }

        int nx = x/3 * 3;
        int ny = y - y % 3;

        for(int i=nx;i<nx+3;i++) {
            for(int j=ny;j<ny+3;j++) {
                if(sdoku[i][j] == n) return false;
            }
        }
        return true;
    }
}