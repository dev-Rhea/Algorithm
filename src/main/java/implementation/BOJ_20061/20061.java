package implementation.BOJ_20061;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Block {
        int t, x, y;
        Block(int t, int x, int y) {
            this.t = t;
            this.x = x;
            this.y = y;
        }
    }

    static List<Block> block;
    static int[][] red, blue, green;
    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        red = new int[4][4];
        blue = new int[4][6];
        green = new int[6][4];
        score = 0;

        block = new ArrayList<>();
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            block.add(new Block(t, x, y));
        }

        for(int i=0;i<block.size();i++) {
            int t = block.get(i).t;
            int x = block.get(i).x;
            int y = block.get(i).y;

            moveBlue(t, x, y);
            moveGreen(t, x, y);
        }

        int cnt = 0;
        for(int i=0;i<blue.length;i++) {
            for(int j=0;j<blue[i].length;j++) {
                if(blue[i][j] == 1) cnt++;
            }
        }
        for(int i=0;i<green.length;i++) {
            for(int j=0;j<green[i].length;j++) {
                if(green[i][j] == 1) cnt++;
            }
        }
        
        System.out.println(score);
        System.out.println(cnt);
    }
    
    static void moveBlue(int t, int x, int y) {
        int ny = 0;

        if(t == 1) {
            while(ny < 5 && blue[x][ny+1] == 0) {
                ny++;
            }
            
            blue[x][ny] = 1;
        }
        else if(t == 2) { 
            while(ny < 4 && blue[x][ny+1] == 0 && blue[x][ny+2] == 0) {
                ny++;
            }
            
            blue[x][ny] = 1;
            blue[x][ny+1] = 1;
        }
        else if(t == 3) {
            while(ny < 5 && blue[x][ny+1] == 0 && blue[x+1][ny+1] == 0) {
                ny++;
            }
            
            blue[x][ny] = 1;
            blue[x+1][ny] = 1;
        }
        
        checkFullColBlue();
        checkSpecialBlue();
    }
    
    static void moveGreen(int t, int x, int y) {
        int nx = 0;

        if(t == 1) {
            while(nx < 5 && green[nx+1][y] == 0) {
                nx++;
            }
            
            green[nx][y] = 1;
        }
        else if(t == 2) {
            while(nx < 5 && green[nx+1][y] == 0 && green[nx+1][y+1] == 0) {
                nx++;
            }
            
            green[nx][y] = 1;
            green[nx][y+1] = 1;
        }
        else if(t == 3) {
            while(nx < 4 && green[nx+1][y] == 0 && green[nx+2][y] == 0) {
                nx++;
            }
            
            green[nx][y] = 1;
            green[nx+1][y] = 1;
        }
        
        checkFullRowGreen();
        checkSpecialGreen();
    }
    
    static void checkFullColBlue() {
        for(int j=0;j<6;j++) {
            boolean isFull = true;
            
            for(int i=0;i<4;i++) {
                if(blue[i][j] == 0) {
                    isFull = false;
                    break;
                }
            }
            
            if(isFull) {
                score++;
                
                for(int col=j;col>0;col--) {
                    for(int row=0;row<4;row++) {
                        blue[row][col] = blue[row][col-1];
                    }
                }
                
                for(int row=0;row<4;row++) {
                    blue[row][0] = 0;
                }
                j--;
            }
        }
    }
    
    static void checkFullRowGreen() {
        for(int i=0;i<6;i++) {
            boolean isFull = true;
            
            for(int j=0;j<4;j++) {
                if(green[i][j] == 0) {
                    isFull = false;
                    break;
                }
            }
            
            if(isFull) {
                score++;
                
                for(int row=i;row>0;row--) {
                    for(int col=0;col<4;col++) {
                        green[row][col] = green[row-1][col];
                    }
                }
                
                for(int col=0;col<4;col++) {
                    green[0][col] = 0;
                }
                i--;
            }
        }
    }
    
    static void checkSpecialBlue() {
        int cnt = 0;
        
        for(int j=0;j<2;j++) {
            boolean exist = false;
            for(int i=0;i<4;i++) {
                if(blue[i][j] == 1) {
                    exist = true;
                    break;
                }
            }
            if(exist) cnt++;
        }
        
        for(int k=0;k<cnt;k++) {
            for(int j=5;j>0;j--) {
                for(int i=0;i<4;i++) {
                    blue[i][j] = blue[i][j-1];
                }
            }
            
            for(int i=0;i<4;i++) {
                blue[i][0] = 0;
            }
        }
    }
    
    static void checkSpecialGreen() {
        int cnt = 0;
        
        for(int i=0;i<2;i++) {
            boolean exist = false;
            for(int j=0;j<4;j++) {
                if(green[i][j] == 1) {
                    exist = true;
                    break;
                }
            }
            if(exist) cnt++;
        }
        
        for(int k=0;k<cnt;k++) {
            for(int i=5;i>0;i--) {
                for(int j=0;j<4;j++) {
                    green[i][j] = green[i-1][j];
                }
            }
            
            for(int j=0;j<4;j++) {
                green[0][j] = 0;
            }
        }
    }
}