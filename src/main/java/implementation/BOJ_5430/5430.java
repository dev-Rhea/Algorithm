package implementation.BOJ_5430;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();
            
            Deque<Integer> deque = new ArrayDeque<>();
            if(n > 0) {
                String[] nums = input.substring(1, input.length() - 1).split(",");
                for(String num : nums) {
                    deque.add(Integer.parseInt(num));
                }
            }
            
            boolean isReversed = false;
            boolean isError = false;
            
            for(char cmd : p.toCharArray()) {
                if(cmd == 'R') {
                    isReversed = !isReversed;
                } else if(cmd == 'D') {
                    if(deque.isEmpty()) {
                        isError = true;
                        break;
                    }
                    
                    if(isReversed) {
                        deque.removeLast();
                    } else {
                        deque.removeFirst();
                    }
                }
            }
            
            if(isError) {
                sb.append("error\n");
            } else {
                sb.append('[');
                
                if(!deque.isEmpty()) {
                    if(isReversed) {
                        sb.append(deque.pollLast());
                        while(!deque.isEmpty()) {
                            sb.append(',').append(deque.pollLast());
                        }
                    } else {
                        sb.append(deque.pollFirst());
                        while(!deque.isEmpty()) {
                            sb.append(',').append(deque.pollFirst());
                        }
                    }
                }
                
                sb.append("]\n");
            }
        }
        
        System.out.print(sb);
    }
}