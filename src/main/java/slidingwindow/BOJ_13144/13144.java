package slidingwindow.BOJ_13144;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] nums, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        cnt = new int[100001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        finder();
    }

    static void finder() {
        long ans = 0;

        int now = 1;
        int a = 0;

        while(now <= N) {
            // 다음 위치가 배열 범위 내면서 현재 값과 동일하지 않다면
            while(a+1 <= N && cnt[nums[a+1]] == 0) {
                a++; // 위치 이동
                cnt[nums[a]]++; // 숫자 등장 횟수 증가 
            }
            // 현재 범위 내에서 중복되지 않는 부분 수열 갯수 누적 
            ans += a - now + 1;
            // 현재 범위 시작 부분 제거, 등장 횟수 감소
            // 시작점 이동 
            cnt[nums[now++]]--;
        }
        System.out.println(ans);
    }
}