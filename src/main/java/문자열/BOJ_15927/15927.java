package 문자열.BOJ_15927;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] str = br.readLine().toCharArray();
        int idx = -1;

        for(int i=1;i<str.length;i++) {
            if(str[i] != str[i-1]) {
                idx = i;
                break;
            }
        }

        if(idx == -1) sb.append(-1);
        else {
            idx = -1;
            for(int i=0;i<str.length/2;i++) {
                if(str[i] != str[str.length-1-i]) {
                    idx=i;
                    break;
                }
            }
            sb.append(idx == -1 ? str.length-1:str.length);
        }
        System.out.println(sb);
    }
}