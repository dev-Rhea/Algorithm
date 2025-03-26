package stack.BOJ_9935;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();
        
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<str.length();i++) {
            stack.push(str.charAt(i));
            
            // 폭발 문자열의 크기 보다 크거나 같을 때만 탐색 진행 
            if(stack.size() >= bomb.length()) {
                boolean flag = true;

                for(int j=0;j<bomb.length();j++) {
                    // 스택의 끝에서 부터 폭발 문자열 크기만큼 비교
                    if(stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                // 폭발 문자열 제거 
                if(flag) {
                    for(int j=0;j<bomb.length();j++) stack.pop();
                }
            }
        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            for(Character c : stack) sb.append(c);
            System.out.println(sb);
        }
        

    }
}