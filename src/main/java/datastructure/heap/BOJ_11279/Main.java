package datastructure.heap.BOJ_11279;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Heap heap = new Heap(N);
        
        while(N-- > 0) {
            int x = Integer.parseInt(br.readLine());

            if(x == 0) {
                sb.append(heap.poll()).append("\n");
                continue;
            }
            heap.offer(x);
        }
        System.out.println(sb);
    }
}

class Heap {
    int[] heap;
    int size;
    
    Heap(int size) {
        heap = new int[size + 1];
    }

    void offer(int n) {
        heap[++size] = n;

        int m = size << 1;
        while((m >>= 1) > 1) {
            if(!swap(m)) break;
        }
    }

    int poll() {
        if(size == 0) return 0;

        int n = heap[1];
        heap[1] = heap[size--];

        int m = 1;
        while((m <<= 1) <= size) {
            if(m < size && heap[m + 1] > heap[m]) m++;
            if(!swap(m)) break;
        }
        return n;
    }

    boolean swap(int n) {
        int m = n >> 1;
        int k = heap[m];
        int p = heap[n];

        if(k > p) return false;

        heap[m] = p;
        heap[n] = k;
        return true;
    }
}
