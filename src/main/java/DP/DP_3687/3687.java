package DP.DP_3687;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static int[] line = {1, 7, 4, 2, 0, 8}; // 성냥 2-7개로 만들 수 있는 한자리 숫자 
    static long[] min = new long[101]; // 성냥으로 만들 수 있는 최소 값 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        Arrays.fill(min, Long.MAX_VALUE);
        // 성냥개비 수로 만들 수 있는 최소 값 지정 
        min[2] = 1;
        min[3] = 7;
        min[4] = 4;
        min[5] = 2;
        min[6] = 6;
        min[7] = 8;
        min[8] = 10;

        // 성냥 9-100개 
        for(int i=9; i<=100; i++){
            // 성냥 2-7 개를 사용할 수 있는 경우의 수 
            for(int j=2; j<=7; j++){
                // i-j 개의 성냥으로 만든 최소 수와 J 개의 성냥으로 만들 수 있는 수 연결 
                String temp = String.valueOf(min[i-j])+String.valueOf(line[j-2]);
                // 최소 값 갱신 
                min[i] = Math.min(Long.parseLong(temp),min[i]);
            }
        }
        

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            
            sb.append(min[n]).append(" ");
            if(n % 2 == 0) sb.append(Max(n / 2)); // 짝수 인 경우 1로 만 생성하면 최대 값 
            else sb.append("7").append(Max((n - 3) / 2)); // 홀수는 7 만든 후 나머지 숫자로 1 생성하면 최대 값 
        
            sb.append("\n");
        }
        

        System.out.println(sb);
    }

    static String Max(int n) {
        StringBuilder sb = new StringBuilder();
        // 나머지 숫자 1 붙이기 
        for(int i=0;i<n;i++) {
            sb.append("1");
        }

        return sb.toString();
    }
}