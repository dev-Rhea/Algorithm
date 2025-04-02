package datastructure.queue.BOJ_11279;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        for(int i=0;i<N;i++) {
            int x = Integer.parseInt(br.readLine());

            if(x > 0) queue.add(x);
            else {
                if(queue.isEmpty()) System.out.println(0);
                else System.out.println(queue.poll());
            }
            
        }
    }
}