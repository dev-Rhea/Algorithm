package bitmask.BOJ_13701;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        BitSet visited = new BitSet(1 << 25);
        boolean first = true;

        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            if(!visited.get(num)) {
                visited.set(num);
                if(!first) sb.append(' ');
                sb.append(num);
                first = false;
            }
        }

        
        System.out.println(sb);
    }
}