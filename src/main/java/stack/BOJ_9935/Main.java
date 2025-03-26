package stack.BOJ_9935;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        int top = 0;  // 현재 유효한 문자열의 끝 위치
        int idx = 0;  // 입력 문자열을 순차적으로 탐색하는 인덱스

        while(idx < str.length) {
            str[top++] = str[idx++];  // 문자열의 현재 문자를 스택처럼 저장 후 인덱스를 증가
            
            if(top >= bomb.length) {  // 현재 스택의 크기가 폭발 문자열보다 크거나 같다면 비교
                boolean isBomb = true;

                for(int i = 0; i < bomb.length; i++) {
                    if(str[top - bomb.length + i] != bomb[i]) {  // 끝에서부터 bomb 비교
                        isBomb = false;
                        break;
                    }
                }

                if(isBomb) {  // 폭발 문자열을 찾은 경우
                    top -= bomb.length;  // 유효한 문자열의 끝을 bomb의 길이만큼 줄임
                }
            }
        }

        if(top == 0) System.out.println("FRULA");  // 남은 문자가 없는 경우
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(str, 0, top);  // 남은 문자열을 출력
            System.out.println(sb);
        }
    }
}
