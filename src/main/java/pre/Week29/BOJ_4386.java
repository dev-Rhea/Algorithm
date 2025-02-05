package pre.Week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4386 {
    static int n;
    static float[][] stars;
    static float[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        stars = new float[n][2];
        dist = new float[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Float.parseFloat(st.nextToken());
            stars[i][1] = Float.parseFloat(st.nextToken());
            dist[i] = Float.MAX_VALUE;
        }

        dist[0] = 0;
        float result = 0;

        for (int i=0;i<n;i++) {
            float min = Float.MAX_VALUE;
            int idx = 0;
            for(int j=0;j<n;j++) {
                if(!visited[j] && min > dist[j]) {
                    min = dist[j];
                    idx = j;
                }
            }

            result += min;
            visited[idx] = true;

            for(int k=0;k<n;k++) {
                float d = (float) Math.sqrt((Math.pow(stars[idx][0] - stars[k][0],2)) + (Math.pow(stars[idx][1] - stars[k][1],2)));

                if(!visited[k] && d != 0 && d < dist[k]) {
                    dist[k] = d;
                }
            }
        }
        System.out.printf("%.2f", result);
    }
}
