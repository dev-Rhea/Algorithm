package etc.kmp.BOJ_1786;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String text = br.readLine();
        String pattern = br.readLine();
        
        int[] table = new int[pattern.length()]; // 돌아갈 위치 저장
        int idx = 0;
        for(int i=1;i<pattern.length();i++) {
            while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) idx = table[idx-1];

            if(pattern.charAt(i) == pattern.charAt(idx)) table[i] = ++idx;
        }

        int at = 0; // 현재까지 일치한 패턴 위치 
        int cnt = 0;
        for(int i=0;i<text.length();i++) {
            while(at>0 && text.charAt(i) != pattern.charAt(at)) {
                at = table[at-1];
            }

            if(text.charAt(i) == pattern.charAt(at)) {
                if(at == pattern.length()-1) {
                    sb.append((i-at+1)+" ");
                    cnt++;
                    at = table[at];
                }
                else at += 1;
            }
        }

        System.out.println(cnt);
        System.out.println(sb.toString());
    }
}