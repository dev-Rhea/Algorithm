package twopointer.BOJ_17609;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        String[] arr = new String[T];
        for(int t=0;t<T;t++) {
            arr[t] = br.readLine();
        }

        for(int i=0;i<T;i++) {
            String str = arr[i];
            System.out.println(check(0, str.length()-1, str, 0));
        }
    }

    static int check(int start, int end, String str, int cnt) {
        if(cnt >= 2) return 2;

        while(start<end) {
            if(str.charAt(start) == str.charAt(end)) {
                start++;
                end--;
            }
            else return Math.min(check(start+1, end, str, cnt+1), check(start, end-1, str, cnt+1));
        }

        return cnt;
    }
}