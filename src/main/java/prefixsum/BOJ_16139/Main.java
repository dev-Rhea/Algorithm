package prefixsum.BOJ_16139;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        int len = str.length();

        // 알파벳 누적합 배열
        int[][] prevSum = new int[26][len + 1];

        // i 번째 알파벳의 등장 횟수 계산
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= len; j++) {
                int c = str.charAt(j - 1) - 'a';
                prevSum[i][j] = prevSum[i][j - 1] + (c == i ? 1 : 0);
            }
        }

        int q = Integer.parseInt(br.readLine());
        
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = st.nextToken().charAt(0) - 'a'; // 문자 -> 인덱스 변환
            int s = Integer.parseInt(st.nextToken()) + 1;
            int e = Integer.parseInt(st.nextToken()) + 1;

            // 결과 저장
            sb.append(prevSum[idx][e] - prevSum[idx][s - 1]).append("\n");
        }

        // 최종 출력
        System.out.print(sb);
    }
}
