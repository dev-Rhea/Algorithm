package pre.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2529 {
    static int K;
    static char[] arr; // 부등호 배열
    static boolean[] visit = new boolean[10]; // 중복 방문 검사
    static List<String> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 부등호 개수 읽기
        K = Integer.parseInt(br.readLine());
        arr = new char[K]; // 부등호 배열 크기 초기화

        // 부등호 배열 채우기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        // dfs 호출하여 가능한 숫자 조합 찾기
        dfs("", 0);

        // 정렬
        Collections.sort(ans);

        // 최대값과 최소값 출력
        System.out.println(ans.get(ans.size() - 1)); // 최대
        System.out.println(ans.get(0)); // 최소
    }

    private static void dfs(String num, int idx) {
        if (idx == K + 1) { // K+1 숫자가 필요
            ans.add(num);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visit[i]) continue;
            if (idx == 0 || check(Character.getNumericValue(num.charAt(idx - 1)), i, arr[idx - 1])) {
                visit[i] = true;
                dfs(num + i, idx + 1);
                visit[i] = false;
            }
        }
    }

    private static boolean check(int a, int b, char c) {
        if (c == '<') {
            return a < b;
        } else {
            return a > b;
        }
    }
}
