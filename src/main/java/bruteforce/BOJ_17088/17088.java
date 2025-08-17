package bruteforce.BOJ_17088;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        if(N == 1) {
            System.out.println(0);
            return;
        }

        int[] B = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;

        // 가능한 연산 -1, 0, 1
        for(int i=-1;i<=1;i++) {
            for(int j=-1;j<=1;j++) {
                int temp = Math.abs(i) + Math.abs(j); // 연산 횟수 비용 
                int diff = (B[1] + j) - (B[0] + i); // 공차 계산 
                boolean isValid = true;
                int prev = B[1] + j; // 이전 항의 값에 연산 

                for(int k=2;k<N;k++) {
                    int next = prev + diff; // K번째 등차수열 값 (이전항 + 등차)

                    if(Math.abs(next - B[k]) > 1) { // 연산 1번 초과 불가능 
                        isValid = false;
                        break;
                    }

                    if(next != B[k]) temp++; // 연산시 횟수 추가 
                    prev = next;
                }

                if(isValid) min = Math.min(min, temp);
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}