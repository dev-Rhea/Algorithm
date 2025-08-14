package backtracking.BOJ_16943;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int max = -1;
    static int B;
    static int[] num;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str1 = st.nextToken();
        B = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[str1.length()];

        num = new int[str1.length()];

        for(int i=0;i<str1.length();i++) {
            num[i] = str1.charAt(i) - '0';
        }

        comb(0, 0, visited);

        System.out.println(max);
    }

    static void comb(int now, int cnt, boolean[] visited) {
        if(cnt == num.length) {
            max = Math.max(max, now);
            return;
        }

        for(int i=0;i<num.length;i++) {
            if(visited[i] || (num[i] == 0 && cnt == 0)) continue;

            if(now * 10 + num[i] > B) continue;

            visited[i] = true;
            comb(now * 10 + num[i], cnt + 1, visited);
            visited[i] = false;
        }
    }
}