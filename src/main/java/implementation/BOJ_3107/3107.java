package implementation.BOJ_3107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        if(input.contains("::")) input = input.replace("::", ":zero:");

        List<String> ip = Stream.of(input.split(":")).collect(Collectors.toList());
        List<String> ans = new ArrayList<>();

        for(int i=0;i<ip.size();i++) {
            String str = ip.get(i);
            if(str.isEmpty()) continue;

            if(!str.equals("zero")) {
                while(str.length() < 4) {
                    str = "0" + str;
                }
            }
            ans.add(str);
        }

        int len = 8 - ans.size() + 1;
        String[] fullIp = new String[8];
        int idx = 0;

        for(String s : ans) {
            if(s.equals("zero")) {
                while(len-- > 0) {
                    fullIp[idx++] = "0000";
                }
            }
            else fullIp[idx++] = s;
        }

        System.out.println(String.join(":", fullIp));
    }
}