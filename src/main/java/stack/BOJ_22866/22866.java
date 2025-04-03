package stack.BOJ_22866;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        
        int[] heights = new int[N+1];
        int[] cnt = new int[N+1];
        int[] near = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            near[i] = -1000000;
        }

        // 왼쪽에 있는 건물 탐색 
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i=1;i<=N;i++) {
            // 현재 건물 보다 낮거나 같은 건물 제거 
            while(!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            // 보이는 왼쪽 건물 중 가장 가까운 건물 저장 
            cnt[i] = stack.size();
            if(cnt[i] > 0) near[i] = stack.peek();
            stack.push(i);
        }

        stack = new ArrayDeque<>();
        // 오른쪽에 있는 건물 탐색 
        for(int i=N;i>0;i--) {
            // 현재 건물 보다 낮거나 같은 높이 건물 제거 
            while(!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            int n = stack.size(); // 보이는 건물 수 
            cnt[i] += n; // 왼, 오 보이는 건물 수 합
            // 더 가까운 건물로 오른쪽 건물 갱신 
            // stack.peek() - i 오른쪽 건물까지의 거리
            // i - near[i] 왼쪽 건뭋까지의 거리 
            if(n > 0 && stack.peek() - i < i - near[i]) near[i] = stack.peek();
            stack.push(i);
        }

        for(int i=1;i<=N;i++) {
            sb.append(cnt[i]);
            if(cnt[i] > 0) sb.append(" ").append(near[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}