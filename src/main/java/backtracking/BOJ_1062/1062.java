package backtracking.BOJ_1062;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K;
    static String[] words;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        visited = new boolean[26];

        for(int i=0;i<N;i++) {
            String input = br.readLine();

            input = input.replace("anta", "");
            input = input.replace("tica", "");

            words[i] = input;
        }

        if(K < 5) {
            System.out.println(0);
            return;
        }
        else if(K == 26) {
            System.out.println(N);
            return;
        }

        visited['a' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['c' - 'a'] = true;

        study(0, 0);
        System.out.println(max);
    }

    static void study(int alpha, int len) {

       if(len == (K - 5)) {
        int cnt = 0;

        for(int i=0;i<N;i++) {
            boolean read = true;
            for(int j=0;j<words[i].length();j++) {
                if(!visited[words[i].charAt(j) - 'a']) {
                    read = false;
                    break;
                }
            }
            if(read) cnt++;
        }
        max = Math.max(max, cnt);
        return;
       }

       for(int i=alpha;i<26;i++) {
        if(!visited[i]) {
            visited[i] = true;
            study(i, len+1);
            visited[i] = false;
        }
       }
    }

} 