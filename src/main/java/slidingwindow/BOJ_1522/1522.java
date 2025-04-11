package slidingwindow.BOJ_1522;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int cnt = 0;

        for(int i=0;i<input.length();i++) {
            if(input.charAt(i) == 'a') cnt++;
        }

        int max = 0; // 특정 구간의 a 갯수 저장 
        for(int i=0;i<input.length();i++) {
            int cntA = 0; // a 갯수 

            for(int j=0;j<cnt;j++) {
                // 원형 순환 
                // i 에서 j 만큼 이동했을 때 길이를 넘지 않는다면, 문자열 내의 구간
                // 길이를 넘는다면 원형 순환 구조 이므로, 길이 만큼 빼줘서 순환 인덱스로 
                int idx = (i + j < input.length() ? i+j : i + j - input.length());
                
                if(input.charAt(idx) == 'a') cntA++;
                if(cntA > max) max = cntA;
            }
        }
        // a 가 가장 많이 모여 있는 구간 외에선 교환 필요 
        System.out.println(cnt - max);
    }
}