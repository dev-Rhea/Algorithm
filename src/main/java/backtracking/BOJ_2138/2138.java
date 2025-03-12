package backtracking.BOJ_2138;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N, cnt;
    static boolean[] to; // 최종  상태 저장 
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        String now = br.readLine(); // 현재 전구 상태 
        String be = br.readLine(); // 목표 전구 상태 
        to = new boolean[N];

        boolean[] status1 = new boolean[N]; // 첫번째 전구 끄고 시작
        boolean[] status2 = new boolean[N]; // 첫번째 전구 키고 시작 

        for(int i=0;i<N;i++) {
            status1[i] = now.charAt(i) != '0';
            status2[i] = now.charAt(i) != '0';
            to[i] = be.charAt(i) != '0';
        }

        // 첫번째 전구 끔, 두번째 전구부터 탐색 
        make(1, 0, status1);

        // 천번째 전구 킴, 현재 검사 중인 전구 위치부터 탐색.
        // useSwitch 인접한 전구 반전 
        make(1, 1, useSwitch(0, status2));

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void make(int idx, int cnt, boolean[] status1) {
        if(idx == N) {
            // 마지막 전구 상태와 목표 상태라면 최소 횟수 갱신 
            if(status1[idx - 1] == to[idx - 1]) min = Math.min(min, cnt);
            return;
        }

        // 목표 상태를 만족하지 못하면, 스위치를 사용해서 인접한 전구 반전 
        // 인접 전구 값, 스위치 횟수 증가, 반전한 상태 
        if(status1[idx - 1] != to[idx - 1]) make(idx + 1, cnt + 1, useSwitch(idx, status1));
        // 목표 상태와 동일하면, 다음 전구로 진행 
        else make(idx + 1, cnt, status1);
    }

    private static boolean[] useSwitch(int idx, boolean[] s) {
        // idx 전구 누르면 인접한 전구 상태 변경 
        for(int i=idx-1;i<=idx+1;i++) {
            // 범위 확인하면서 상태 반전 
            if( 0<= i && i< N) s[i] = !s[i];
        }
        return s;
    }
}