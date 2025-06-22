package bfs.BOJ_1963;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        boolean[] prime = new boolean[10000];
        prime[0] = prime[1] = true;
        
        for (int i = 2; i < 10000; i++) {
            if (!prime[i]) {
                for (int j = i + i; j < 10000; j += i) {
                    prime[j] = true;
                }
            }
        }
        
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[10000];
            cnt = 0;
            
            queue.add(a);
            visited[a] = true;
            
            boolean found = false;
            
            while(!queue.isEmpty() && !found) {
                int size = queue.size();
                
                for(int s = 0; s < size; s++) {
                    int now = queue.poll();
                    
                    if(now == b) {
                        found = true;
                        break;
                    }
                    
                    for(int i = 0; i < 4; i++) {
                        for(int j = 0; j < 10; j++) {
                            if(i == 0 && j == 0) continue;
                            
                            int next = change(i, j, now);
                            
                            if(!prime[next] && !visited[next]) {
                                queue.add(next);
                                visited[next] = true;
                            }
                        }
                    }
                }
                
                if(!found) {
                    cnt++;
                }
            }
            
            if(found) {
                System.out.println(cnt);
            } else {
                System.out.println("Impossible");
            }
        }
    }
    
    static int change(int position, int digit, int num) {
        int[] digits = new int[4];
        
        digits[3] = num % 10;          
        digits[2] = (num / 10) % 10;  
        digits[1] = (num / 100) % 10;   
        digits[0] = (num / 1000) % 10;
        
        digits[position] = digit;
        
        return digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];
    }
}