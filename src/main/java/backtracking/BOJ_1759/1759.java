package backtracking.BOJ_1759;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int C, L;
    static char[] alpha;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        alpha = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(alpha);
        
        comb(0, 0, new StringBuilder());
        
        System.out.print(sb);
    }

    static void comb(int idx, int depth, StringBuilder current) {
        if(depth == L) {
            if(isValid(current.toString())) {
                sb.append(current).append('\n');
            }
            return;
        }
        
        for(int i = idx; i < C; i++) {
            current.append(alpha[i]);
            comb(i + 1, depth + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
    
    static boolean isValid(String password) {
        int vowel = 0;
        int consonant = 0;
        
        for(char c : password.toCharArray()) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowel++;
            } else {
                consonant++;
            }
        }
        
        return vowel >= 1 && consonant >= 2;
    }
}