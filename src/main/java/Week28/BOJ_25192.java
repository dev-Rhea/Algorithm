package Week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_25192 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Set<String> members = new HashSet<>();
        int cnt = 0;

        while(N --> 0) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            if(name.equals("ENTER")) {
                cnt += members.size();
                members.clear();
                continue;
            }
            members.add(name);
        }
        cnt += members.size();
        System.out.println(cnt);
    }

}
