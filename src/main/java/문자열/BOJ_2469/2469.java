package 문자열.BOJ_2469;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        char[] start = new char[k];
        char[] list = br.readLine().toCharArray();
        char[][] ladder = new char[n][k-1];

        int idx = 0;

        for(int i=0;i<k;i++) {
            start[i] = (char) ('A' + i);
        }

        for(int i=0;i<n;i++) {
            String input = br.readLine();
            for(int j=0;j<k-1;j++) {
                ladder[i][j] = input.charAt(j);

                if(ladder[i][j] == '?') {
                    idx = i;
                    continue;
                }
            }
        }

        for(int i=0;i<idx;i++) {
            for(int j=0;j<k-1;j++) {
                if(ladder[i][j] == '-') {
                    char temp = start[j];
                    start[j] = start[j+1];
                    start[j+1] = temp;
                }
            }
        }

        for(int i=n-1;i>idx;i--) {
            for(int j=0;j<k-1;j++) {
                if(ladder[i][j] == '-') {
                    char temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }

        boolean flag = true;

        for(int j=0;j<k-1;j++) {
            if(start[j] == list[j]) sb.append('*');
            else if(start[j] == list[j+1] && start[j+1] == list[j]) {
                sb.append('-');
                char temp = start[j];
                start[j] = start[j+1];
                start[j+1] = temp;
            }
            else {
                flag = false;
                break;
            }
        }

        System.out.println(flag ? sb.toString() : "x".repeat(k-1));
    }
}