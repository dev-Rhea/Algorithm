package stack.BOJ_1863;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int ans = 0;
        int before = 0; // 이전 값 

        Stack<Integer> stack = new Stack<>();
        stack.add(0); // 초기 값 

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 이전 값보다 현재 값이 크다면 새로운 건물 
            if(before< b) ans ++;
            // 이전 값이 더 큰 경우 
            else {
                while(stack.peek() > b) stack.pop(); // 스택의 최상단 값이 현재 값 보다 크다면 제거 
                if(stack.peek() != b) ans++; // 스택의 최상단 값과 b가 다르다면 새로운 건물 
                else stack.pop(); // 스택의 최상단 값과 현재 건물 높이가 같다면, 스택에서 제거 
            }
            stack.push(b); // 현재 건물 스택에 넣음
            before = b; // 이전 값 업데이트 
        }
        System.out.println(ans);
    }
}