package bruteforce.BOJ_22251;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[][] change = {
        { 0, 4, 3, 3, 4, 3, 2, 3, 1, 2 },
        { 4, 0, 5, 3, 2, 5, 6, 1, 5, 4 },
        { 3, 5, 0, 2, 5, 4, 3, 4, 2, 3 },
        { 3, 3, 2, 0, 3, 2, 3, 2, 2, 1 },
        { 4, 2, 5, 3, 0, 3, 4, 3, 3, 2 },
        { 3, 5, 4, 2, 3, 0, 1, 4, 2, 1 },
        { 2, 6, 3, 3, 4, 1, 0, 5, 1, 2 },
        { 3, 1, 4, 2, 3, 4, 5, 0, 4, 3 },
        { 1, 5, 2, 2, 3, 2, 1, 4, 0, 1 },
        { 2, 4, 3, 1, 2, 1, 2, 3, 1, 0 }
    };
    
    static int cnt;
    static int N, K, X, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        cnt = 0;

        // num 현재 만드려는 숫자, tmp 현재 자리의 10의 거듭 제곱, count 변경할 세그먼트 수, depth 현재 탐색 중인 자릿 수
        dfs(0, 1, 0, 0);

        System.out.println(cnt - 1); // X 도 포함되어 있으므로 -1 해줌
    }

    static void dfs(int num, int tmp, int count, int depth) {
        if(num > N) return; // 현재 만든 수가 N 보다 크면 종료
        if(count > P) return; // 사용한 세그먼트 변경수 P 초과 불가
        if(depth == K) { // K 자릿수 까지 숫자 완성
            if(num != 0) { // 0을 제외한 유효한 숫자 카운트
                cnt++;
                return;
            }
        }

        for(int i=0;i<10;i++) {
            // i*tmp+num 현재 자릿수를 더해서 새로운 숫자 생성
            // tmp * 10 다음 자릿수 탐색
            // count + change[X / tmp % 10][i] 세그먼트 변경횟수 추가
            // depth + 1 다음 자리로 이동
            dfs(i*tmp+num, tmp * 10, count + change[X / tmp % 10][i], depth + 1);
        }
    }
}