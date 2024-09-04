package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.util.Arrays.sort;

public class BOJ_1759 {
    static int L, C;
    static char[] arr;
    static char[] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        result = new char[L];
        visited = new boolean[C];

        // 문자 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        // 사전 순으로 정렬
        sort(arr, 0, C-1);

        dfs(0, 0, 0);
    }

    private static void dfs(int index, int start, int count) {
        if(count == L) {
            int vowel = 0;
            int consonant = 0;

            // 최소 한개의 모음과 최소 두개의 자음
            for(int i=0;i<L;i++) {
                if(result[i] == 'a' || result[i] == 'e' || result[i] == 'i' || result[i] == 'o' || result[i] == 'u') {
                    vowel++;
                } else {
                    consonant++;
                }
            }

            // 조건에 맞는 경우 출력
            if(vowel >= 1 && consonant >= 2) {
                for(int i=0;i<L;i++) {
                    System.out.print(result[i]);
                }
                System.out.println();
            }

            return;
        }

        // 조합
        for(int i=start;i<C;i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[index] = arr[i];
                dfs(index+1, i+1, count+1);
                visited[i] = false;
            }
        }
    }
}
