package stack.BOJ_1406;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();  // 초기 문자열
        int M = Integer.parseInt(br.readLine());  // 명령 수

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for (char c : input.toCharArray()) {
            left.push(c);
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine();

            switch (str.charAt(0)) {
                case 'L':
                    if (!left.isEmpty()) right.push(left.pop());
                    break;
                case 'D':
                    if (!right.isEmpty()) left.push(right.pop());
                    break;
                case 'B':
                    if (!left.isEmpty()) left.pop();
                    break;
                case 'P':
                    char c = str.charAt(2);  // P x -> x를 가져오기
                    left.push(c);
                    break;
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (char c : left) sb.append(c);
        while (!right.isEmpty()) sb.append(right.pop());
        System.out.println(sb);
    }
}
