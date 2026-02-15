package dfs.BOJ_2251;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

    static int A, B, C;
    static boolean[][] check;
    static Set<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        check = new boolean[201][201];
        ans = new TreeSet<>();

        dfs(0, 0, C);

        for(int n : ans) {
            System.out.print(n + " ");
        }
    }

    static void dfs(int ta, int tb, int tc) {
        if(check[ta][tb]) return;

        if(ta == 0) ans.add(tc);
        check[ta][tb] = true;

        if(ta+tb > B) dfs((ta+tb)-B, B, tc);
        else dfs(0, ta+tb, tc);

        if(ta+tb > A) dfs(A, ta+tb-A, tc);
        else dfs(ta+tb, 0, tc);

        if(ta+tc > A) dfs(A, tb, ta+tc-A);
        else dfs(ta+tc, tb, 0);

        if(tb+tc > B) dfs(ta, B, tb+tc-B);
        else dfs(ta, tb+tc, 0);

        dfs(ta, 0, tb+tc);
        dfs(0, tb, ta+tc);
    }
}