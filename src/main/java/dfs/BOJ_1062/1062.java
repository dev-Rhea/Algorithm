import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K, ans;
    static int[] words;
    static int[] alpha = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(K < 5) {
            System.out.println(0);
            return;
        }
        if(K == 26) {
            System.out.println(N);
            return;
        }

        words = new int[N];
        for(int i=0;i<N;i++) {
            String w = br.readLine();
            for(char c : w.toCharArray()) {
                words[i] |= (1 << (c - 'a'));
            }
        } 

        int fix = (1<<('a'-'a')) | (1<<('n'-'a')) | (1<<('t'-'a')) | (1<<('i'-'a')) | (1<<('c'-'a'));

        int[] remain = new int[21];
        int idx = 0;
        for(int i=0;i<26;i++) {
            if((fix & (1<<i)) == 0) remain[idx++] = i;
        }

        ans = 0;
        dfs(remain, fix, 0, K-5);


        System.out.println(ans);
    }

    static void dfs(int[] remain, int pick, int start, int left) {
        if(left == 0) {
            int cnt = 0;
            for(int w : words) {
                if((w & pick) == w) cnt++;
            }
            ans = Math.max(ans, cnt);
            return;
        }

        if(start == remain.length) return;
        if(remain.length - start < left) return;

        dfs(remain, pick | (1 << remain[start]), start+1, left-1);
        dfs(remain, pick, start+1, left);
    }
}