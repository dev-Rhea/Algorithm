package backtracking.BOJ_7490;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;
    static char[] operator = {' ', '+', '-'};
    static char[] op; // 현재 선택 연산자 저장
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());
            op = new char[N];
            backtracking(1);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void backtracking(int depth) {
        if(N == depth) { // depth : 현재 선택한 숫자의 위치
            if(check()) {
                // 결과가 0이면 숫자와 연산자 sb 에 저장
                for(int i=1;i<N;i++) {
                    sb.append(i).append(op[i]);
                }
                sb.append(N).append("\n");
            }
            return;
        }
        
        // 다음 연산자 선택
        for(char o : operator) {
            op[depth] = o;
            backtracking(depth + 1);
        }
    }

    // 수식 계산 
    static boolean check() {
        int sum = 1; // 최종 값
        int before = 1; // 직전 숫자

        for(int i=1, num=2;i<N;i++, num++) {
            if(op[i] == '+') {
                sum += num;
                before = num;
            }
            else if(op[i] == '-') {
                sum -= num;
                before = -num;
            }
            else {// 공백 처리 
                int flag = num; // flag 현재 숫자 
                // 현재 숫자가 음수면, num도 음수로 변환 (연산 순서 유지)
                if(before < 0) flag = -num;
                // 기존에 before를 sum에 연산하였으므로, 보정 필요
                // before * 9 + flag 기존 before 값 제거 + 새로운 두자리 수로 변환
                sum += before * 9 + flag;
                // 숫자 이어붙이기
                before = before * 10 + flag;
            }
        }
        return sum == 0;
    }
}