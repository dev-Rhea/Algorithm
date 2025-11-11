import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static final int N = 26;
    static int[] prev, time;
    static List<List<Integer>> works;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        prev = new int[N];
        time = new int[N];
        works = new ArrayList<>();

        for(int i=0;i<N;i++) {
            works.add(new ArrayList<>());
            time[i] = -1;
        }

        String input;
        while((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);
            
            int name = st.nextToken().charAt(0) - 'A';
            int t = Integer.parseInt(st.nextToken());
            time[name] = t;

            if(st.hasMoreTokens()) {
                String preStr = st.nextToken();
                for(char w : preStr.toCharArray()) {
                    prev[name]++;
                    works.get(w - 'A').add(name);
                }
            }
        }

        int[] dp = new int[N];
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<N;i++) {
            if(prev[i]==0 && time[i] != -1) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next : works.get(now)) {
                dp[next] = Math.max(dp[next], dp[now] + time[now]);

                if(--prev[next] == 0) queue.add(next);
            }
        }

        int total = 0;
        for(int i=0;i<N;i++) {
            if(time[i] != -1) {
                total = Math.max(total, dp[i] + time[i]);
            }
        }

        System.out.println(total);
    }
}