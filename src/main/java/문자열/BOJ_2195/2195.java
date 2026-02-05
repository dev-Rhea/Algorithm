package 문자열.BOJ_2195;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String P = br.readLine();

        
        int s = 0;
        int e = P.length();
        int cnt = 0;

        while(s < e) {
            int max = 0;

            for(int i=1;i+s<=e;i++) {
                if(S.contains(P.substring(s, s+i))) max = i;
                else break;
            }

            s += max;
            cnt++;
        }
        System.out.println(cnt);
    }
}