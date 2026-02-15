package 문자열.BOJ_1334;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();
        String l = N.substring(0, (N.length()+1)/2);
        
        StringBuilder sb = new StringBuilder(l);

        for(int i=N.length()/2-1;i>=0;i--) {
            sb.append(sb.charAt(i));
        }

        if(sb.toString().compareTo(N) <= 0) {
            int mid = (N.length()+1)/2;
            int idx;

            for(idx=mid-1;idx>=0;idx--) {
                if(sb.charAt(idx) != '9') break;
            }

            if(idx < 0) {
                System.out.println("1" + "0".repeat(N.length()-1)+"1");
                return;
            }

            sb.setCharAt(idx, (char)(sb.charAt(idx)+1));
            for(idx=idx+1;idx<mid;idx++) {
                sb.setCharAt(idx, '0');
            }

            for(idx=N.length()/2-1;idx>=0;idx--) {
                sb.setCharAt(N.length()-1-idx, sb.charAt(idx));
            }
        }

        System.out.println(sb);
    }
}