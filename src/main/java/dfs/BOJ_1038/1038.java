package dfs.BOJ_1038;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

    static int N;
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        for(int i=0;i<10;i++) {
            dfs(i);
        }
        Collections.sort(list);

        System.out.println(N >= list.size() ? -1 : list.get(N));
    }

    static void dfs(long n) {
        list.add(n);
        long mod = n % 10;

        if(mod == 0) return;

        for(long i=mod-1;i>=0;i--) {
            long now = n * 10 + i;
            dfs(now);
        }
    }
}