package 문자열.BOJ_16719;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static String input;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();
        visited = new boolean[input.length()];

        arr(0, input.length()-1);
        System.out.println(sb);
    }

    static void arr(int left, int right) {
        if(left > right) return;

        int min = left;
        for(int i=min;i<=right;i++) {
            if(input.charAt(min) > input.charAt(i)) min = i;
        }

        visited[min] = true;

        for(int i=0;i<input.length();i++) {
            if(visited[i]) sb.append(input.charAt(i));
        }
        sb.append("\n");

        arr(min+1, right);
        arr(left, min-1);
    }
}