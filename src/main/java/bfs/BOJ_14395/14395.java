package bfs.BOJ_14395;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    static class Number {
        long n;
        String calculate = "";
        Number(long n, String calculate) {
            this.n = n;
            this.calculate = calculate;
        }
    }

    static long s, t;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        bfs(s, t);
    }

    static void bfs(long s, long t) {
        if(s == t) {
            System.out.println(0);
            return;
        }

        String[] op = new String[] {"*", "+", "-", "/"};
        
        Set<Long> set = new HashSet<>();

        Queue<Number> queue = new LinkedList<>();
        set.add(s);
        queue.add(new Number(s, ""));

        while(!queue.isEmpty()) {
            Number now = queue.poll();

            long num = now.n;
            String opr = now.calculate;

            if (num * num <= t && !set.contains(num * num)) {
                if (num * num == t) {
                    System.out.println(opr + "*");
                    return;
                }
                queue.add(new Number(num * num, opr + "*"));
                set.add(num * num);
            }

            if (num + num <= t && !set.contains(num + num)) {
                if (num + num == t) {
                    System.out.println(opr + "+");
                    return;
                }
                queue.add(new Number(num + num, opr + "+"));
                set.add(num + num);
            }

            long next = num - num;
            if (!set.contains(next)) {
                if (next == t) {
                    System.out.println(opr + "-");
                    return;
                }
                queue.add(new Number(next, opr + "-"));
                set.add(next);
            }

            if (num != 0 && !set.contains(1L)) {
                if (1 == t) {
                    System.out.println(opr + "/");
                    return;
                }
                queue.add(new Number(1L, opr + "/"));
                set.add(1L);
            }
        }
        System.out.println(-1);
        return;
    }
}