package bruteforce.BOJ_27172;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] origin = new int[N];
        int[] arr = new int[1000001];
        int[] score = new int[1000001];
        int max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[origin[i] = Integer.parseInt(st.nextToken())] = 1;
            if(max < origin[i]) {
                max = origin[i];
            }
        }
        
        for(int i = 1; i <= max; i++) {
            if(arr[i] != 0) {
                for(int j = i << 1; j <= max; j += i) {
                    if(arr[j] != 0) {
                        ++score[i];
                        --score[j];
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int o : origin) {
            sb.append(score[o]).append(' ');
        }
        System.out.print(sb.toString());
    }
}