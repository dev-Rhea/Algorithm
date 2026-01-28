package 문자열.BOJ_6443;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static StringBuilder sb;
    static StringBuilder output;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        output = new StringBuilder();
        
        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            
            visited = new boolean[chars.length];
            sb = new StringBuilder();
            
            permutation(chars, 0);
        }
        
        System.out.print(output);
    }
    
    static void permutation(char[] chars, int depth) {
        if(depth == chars.length) {
            output.append(sb).append('\n');
            return;
        }
        
        char prev = 0;
        
        for(int i = 0; i < chars.length; i++) {
            if(visited[i] || chars[i] == prev) continue;
            
            visited[i] = true;
            sb.append(chars[i]);
            prev = chars[i];
            
            permutation(chars, depth + 1);
            
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
}