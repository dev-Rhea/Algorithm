package implementation.BOJ_31423;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] univ = new String[N];
        for(int i=0;i<N;i++) {
            univ[i] = br.readLine();
        }

        int[] next = new int[N];
        int[] end = new int[N];
        int[] head = new int[N];
        Arrays.fill(next, -1);

        for(int i=0;i<N;i++) {
            end[i] = i;
            head[i] = i;
        }

        int last = 0;

        for(int t=0;t<N-1;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;

            next[end[i]] = head[j];
            end[i] = end[j];
            last = i;     
        }

        int now = head[last];
        while(now != -1) {
            sb.append(univ[now]);
            now = next[now];
        }

        System.out.println(sb);
    }
}