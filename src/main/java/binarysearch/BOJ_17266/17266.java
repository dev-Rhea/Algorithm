package binarysearch.BOJ_17266;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] light = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            light[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(light);

        
        int low = 0;
        int high = N;

        while(low < high) {
            int mid = (low + high) / 2;
            if(cover(light, N, mid)) high = mid;
            else low = mid+1;
        }

        System.out.println(low);
    }

    static boolean cover(int[] light, int N, int h) {
        if(light[0] > h) return false;

        int len = light[0] + h;
        for(int i=1;i<light.length;i++) {
            if(light[i] - h > len) return false;
            len = Math.max(len, light[i] + h);
        }

        return len >= N;
    }
}