package implementation.BOJ_14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int max, ans;
    static int[] top;
    static int[] block;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        block = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        top = new int[block.length]; // 왼쪽에서 가장 높은 블록 저장

        // 왼쪽에서 부터 최대 높이 저장
        for (int i = 0; i < block.length; i++) {
            max = Math.max(block[i], max);
            top[i] = max;
        }

        max = 0;

        // 오른 쪽에서 부터 최대 높이 갱신 + 빗물 계산
        for (int i = block.length - 1; i >= 0; i--) {
            max = Math.max(max, block[i]);
            ans += Math.max(0, Math.min(max, top[i]) - block[i]);
        }
        System.out.println(ans);
    }

}
