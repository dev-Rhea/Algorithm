package Week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_11899 {
    static Stack<Character> S = new Stack<>();
    static int ans; // 추가해야할 괄호 수

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        // 문자열 -> 문자배열로 변환하여 반복문을 통해 전체적으로 탐색
        for(char s : str.toCharArray()) {
            if(s == ')') {
                if(!S.isEmpty() && S.peek() == '(') { // 짝이 맞으면 pop
                    S.pop();
                }
                else  { // 짝이 맞지 않으면 스택에 추가
                    S.push(s);
                }
            }
            else { // 짝이 맞지 않으니 스택에 추가
                S.push(s);
            }
        }
        ans = S.size(); // 짝이 맞지 않는 괄호 수
        System.out.println(ans);

    }

}
