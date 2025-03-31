package implementation.BOJ_2179;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Map<String, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        List<String> max = new ArrayList<>(); // 공통 접두사 저장 
        int ans = 1; // 최대 길이 저장 

        // 각 단어에서 등장가능한 모든 접두사를 map 에 저장
        // 이미 map 에 있다면 횟수 +1, 두번 이상 등장 시에는 ans 갱신
        for(int i=0;i<N;i++) {
            words[i] = br.readLine();

            for(int j=ans;j<=words[i].length();j++) {
                String s = words[i].substring(0, j); // 접두사 추출 
                int value = map.getOrDefault(s, 0) + 1;
                ans = value != 1 ? Math.max(ans, s.length()) : ans; // 2번이상 등장시 값 갱신 
                map.put(s, value); // 접두사 저장 
            }
        }

        // 최대 접두사 후보들 저장 
        // 두번 이상 등장한 ans 와 길이가 같은 것들 저장
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() != 1 && entry.getKey().length() == ans) max.add(entry.getKey());
        }

        int cnt = 0;

        // 해당 접두사를 가지는 단어 두개 출력 
        for(String s : words) {
            if(s.length() < ans) continue;

            // 공통 접두사 리스트에 있다면 
            if(max.contains(s.substring(0, ans))) {
                cnt++;
                max.clear(); // 동일 접두사만 남기기 위해 비움 
                max.add(s.substring(0, ans)); // 현재 접두사 추가 
                System.out.println(s);
                if(cnt == 2) break; // 2개 출력하면 종료 
            }
        }
    }
}