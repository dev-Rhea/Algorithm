package bfs.BOJ_9019;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            System.out.println(bfs(A, B));

        }
    }

    static String bfs(int A, int B) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(A);
        
        String[] register = new String[10000];
        register[A] = "";

        int n, d, s, l, r;

        while(!queue.isEmpty() && register[B] == null) {
            n = queue.remove();

            d = (n * 2) % 10000;
            if(register[d] == null) {
                register[d] = register[n] + "D";
                queue.add(d);
            }

            s = n == 0 ? 9999 : n-1;
            if(register[s] == null) {
                register[s] = register[n] + "S";
                queue.add(s);
            }

            l = n % 1000 * 10 + n / 1000;
            if(register[l] == null) {
                register[l] = register[n] + "L";
                queue.add(l);
            }

            r = n % 10 * 1000 + n / 10;
            if(register[r] == null) {
                register[r] = register[n] + "R";
                queue.add(r);
            }
        }
        return register[B];
    }
}