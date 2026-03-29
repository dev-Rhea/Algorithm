package datastructure.deque.BOJ_11003;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] nums = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=N;i++) {
            while(!deque.isEmpty() && nums[deque.peekLast()] >= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);

            if(deque.peekFirst() < i-L+1) deque.pollFirst();
            sb.append(nums[deque.peekFirst()]).append(' ');
        }

        System.out.print(sb);
    }
}