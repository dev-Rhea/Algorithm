package greedy.BOJ_12904;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

       StringBuilder s = new StringBuilder(S);
       StringBuilder t = new StringBuilder(T);
       for(int i=t.length()-1;i>=0;i--) {
            if(i == S.length()-1) break;

            if(t.charAt(i) == 'B') {
                t.deleteCharAt(i);
                t.reverse();
            }
            else t.deleteCharAt(i);
        }

        System.out.println(s.compareTo(t) == 0 ? 1:0);

    }
}