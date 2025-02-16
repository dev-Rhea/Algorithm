package etc.Bipartitematching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] d;
    static boolean[] check;
    static List<Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new ArrayList[n+1];
        check = new boolean[n+1];
        d = new int[n+1];

        // 각 인데스에 대한 객체 생성
        for(int i=1;i<=n;i++) {
            map[i] = new ArrayList<>();
        }

        // 매칭 초기화
        for(int i=1;i<=n;i++) {
            d[i] = -1;
        }

        // 매칭 정보
        for(int i=1;i<=k;i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            map[a].add(b);
        }

        int ans = 0;
        // 정점 마다 매칭 시도
        for(int i=1;i<=n;i++) {
            check = new boolean[n+1]; // 매칭 시도 마다, 방문 여부 리셋
            if(dfs(i)) { // true 반환시 매칭 성공이므로 ans 증가
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean dfs(int n) {
        if(check[n]) return false; // 이미 방문한 정점이면 false

        check[n] = true;

        for(int i=0;i<map[n].size();i++) {
            int next = map[n].get(i);
            if(d[next] == -1 || dfs(d[next])) { // 매칭이 안되어있거나, 다른 정점과 매칭이 되어있는 경우
                d[next] = n; // 매칭
                return true;
            }
        }
        return false;
    }
}
