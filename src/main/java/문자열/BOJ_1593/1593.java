package 문자열.BOJ_1593;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int wl = Integer.parseInt(s[0]);
        int sl = Integer.parseInt(s[1]);
        int cnt = 0;

        String W = br.readLine();
        String S = br.readLine();

        int[] ws = new int[52];
        int[] ss = new int[52];

        for(int i=0;i<wl;i++) {
            char idx = W.charAt(i);
            perm(idx, 1, ws);
        }

        for(int i=0;i<wl;i++) {
            char idx = S.charAt(i);
            perm(idx, 1, ss);
        }

        if(Arrays.equals(ws, ss)) cnt++;

        for(int i=wl;i<sl;i++) {
            perm(S.charAt(i-wl), -1, ss);

            perm(S.charAt(i), 1, ss);

            if(Arrays.equals(ws, ss)) cnt++;
        }

        System.out.println(cnt);
    }

    static void perm(char idx, int n, int[] arr) {
        if(idx >= 'a') arr[idx - 'a'] += n;
        else arr[idx - 'A'+26] += n;
    }
}