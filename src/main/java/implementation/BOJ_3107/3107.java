package implementation.BOJ_3107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringTokenizer st = new StringTokenizer(input, ":");
        char[] ip = input.toCharArray();

        int cnt = 0;
        while(st.hasMoreTokens()) {
            st.nextToken();
            cnt++;
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        int idxCnt = 0; // 현자 자리 원소 개수
        int part = 0; // 현재 까지 확인한 자리 개수

        while(idx < ip.length) {
            if(ip[idx] == ':') {
                if(idx == 0) idx++;
                if(ip[idx-1] == ':') {
                    for(int i=0;i<8 - cnt;i++) {
                        part++;
                        if(part == 0) sb.append("0000");
                        else sb.append("0000:");
                    }
                    idx++;
                }
                else {
                    part++;

                    for(int i=0;i<4-idxCnt;i++) {
                        sb.append("0");
                    }
                    for(int i=idx-idxCnt;i<idx;i++) {
                        sb.append(ip[i]);
                    }
                    sb.append(":");
                    idxCnt = 0;
                    idx++;
                }
            }
            else {
                idxCnt++;
                if(idx == ip.length-1) {
                    for(int i=0;i<4 - idxCnt;i++) {
                        sb.append("0");
                    }
                    for(int i=idx - idxCnt+1;i<=idx;i++) {
                        sb.append(ip[i]);
                    }
                    idxCnt = 0;
                }
                idx++;
            }
        }
        System.out.println(sb);
    }
}