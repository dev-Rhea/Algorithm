package pre.Week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BOJ_1662 {
    static String[] substr;
    static Stack<String> stack = new Stack<>();

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자 순회
        for(char ch : br.readLine().toCharArray()) {
            if(ch != ')') {
                stack.add(ch + ""); // 문자열로 스택에 추가
            }
            else {
                int cnt = 0; // 문자열 길이 저장용 변수
                while(!stack.peek().equals("(")) { // 스택의 top 요소가 ( 일때 까지
                    cnt += stack.pop().equals("*") ? Integer.parseInt(stack.pop()) : 1;
                }
                stack.pop(); //  ( 제거

                stack.push(String.valueOf(cnt * Integer.parseInt(stack.pop()))); // 이전의 숫자와 cnt 곱함
                stack.push("*"); // 문자열 반복 표시
            }
        }

        long ans = 0; // 문자열 길이
        while(!stack.isEmpty()) {
            ans += stack.pop().equals("*") ? Integer.parseInt(stack.pop()) : 1;
        }

        System.out.print(ans);
    }
}
