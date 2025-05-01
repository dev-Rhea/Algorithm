import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int[] queue, pre;
    static char[] register, buffer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            pre = new int[10000];
            queue = new int[10000];
            register = new char[10000];
            buffer = new char[10000];

            bfs(A, B);
        }
        System.out.println(sb);
    }

    static void bfs(int A, int B) {
        for(int i=0;i<10000;i++) {
            pre[i] = -1;
        }

        int front = 0;
        int back = 0;

        queue[front++] = A;
        pre[A] = A;

        boolean found = false;
        while(front != back && !found) {
            int now = queue[back++];

            int d = (now << 1) % 10000;
            if(pre[d] == -1) {
                pre[d] = now;
                register[d] = 'D';

                if(d == B) found = true;
                else queue[front++] = d;
            }

            int s = (now == 0) ? 9999 : now - 1;
            if (!found && pre[s] == -1) {
                pre[s] = now;
                register[s] = 'S';
                if (s == B) found = true;
                else queue[front++] = s;
            }

            int l = (now % 1000) * 10 + (now / 1000);
            if (!found && pre[l] == -1) {
                pre[l] = now;
                register[l] = 'L';
                if (l == B) found = true;
                else queue[front++] = l;
            }

            int r = (now / 10) + (now % 10) * 1000;
            if (!found && pre[r] == -1) {
                pre[r] = now;
                register[r] = 'R';
                if (r == B) found = true;
                else queue[front++] = r;
            }
        }

        int idx = buffer.length;
            int node = B;
            while (node != A) {
                buffer[--idx] = register[node];
                node = pre[node];
            }
        sb.append(buffer, idx, buffer.length - idx).append('\n');
    }
}