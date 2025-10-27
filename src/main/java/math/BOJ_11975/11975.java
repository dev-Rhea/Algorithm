package math.BOJ_11975;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int door = 0;
    static final int OFFSET = 1000;
    static final int SIZE = 2000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        Set<Integer> vertices = new HashSet<>();
        Set<Integer> edges = new HashSet<>();
        
        int x = OFFSET;
        int y = OFFSET;
        int now = y * SIZE + x;
        vertices.add(now);
        
        char[] dir = br.readLine().toCharArray();
        for (int i = 0; i < dir.length; i++) {
            int d = -1;
            if (dir[i] == 'N') d = 0;
            else if (dir[i] == 'E') d = 1;
            else if (dir[i] == 'S') d = 2;
            else if (dir[i] == 'W') d = 3;
            
            y += dy[d];
            x += dx[d];
            
            int next = y * SIZE + x;
            vertices.add(next);
            
            int min = Math.min(now, next);
            int max = Math.max(now, next);
            edges.add(max * SIZE + min);
            
            now = next;
        }
        
        // 오일러 정리 적용: 필요한 문의 개수 = E - V + 1
        door = edges.size() - vertices.size() + 1;
        
        door = Math.max(0, door);
        System.out.println(door);
    }
}