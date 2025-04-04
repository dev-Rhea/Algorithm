package greedy.BOJ_24337;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();

        int max=Math.max(a, b);

		Deque<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i < a; i++) {
			queue.add(i);
		}
		queue.add(max);

		for (int i = b - 1; i > 0; i--) {
			queue.add(i);
		}

        // 조건에 맞지 않음 
		if(queue.size() > N) {
			System.out.println(-1);
			return;
		}
		
		//건물 갯수 맞추기
		int first = queue.pollFirst();
		int size = queue.size();

        // 보이지 않는 낮은 건물들 채우기 
		for(int i=1;i<N-size;i++) {
			queue.addFirst(1);
		}

		queue.addFirst(first); // 꺼낸던 값 다시 넣기 

		for (int i = 1; i <= N; i++) {
			System.out.print(queue.pollFirst() + " ");
		}
    }
}