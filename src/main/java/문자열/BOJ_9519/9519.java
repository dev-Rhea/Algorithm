package 문자열.BOJ_9519;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        String word = br.readLine();
        List<String> words = new ArrayList<>();
        words.add(word);

        int cnt = 0;
        for(int i=1;i<word.length();i++) {
            if(word.charAt(i) != word.charAt(i-1)) cnt++;
        }

        if(cnt == 0) {
            System.out.println(word);
            return;
        }
        
        for(int i=1;i<=X;i++) {
            StringBuilder sb = new StringBuilder();

            for(int j=0;j<word.length();j++) {
                sb.append(word.charAt(j));
                j++;
            }

            if(word.length() % 2 == 0) {
                for(int j=word.length()-1;j>0;j--) {
                    sb.append(word.charAt(j));
                    j--;
                }
            }
            else {
                for(int j=word.length()-2;j>0;j--) {
                    sb.append(word.charAt(j));
                    j--;
                }
            }

            word = sb.toString();

            if(words.contains(word)) break;
            words.add(word);
        }

        int freq = words.size();
        int last = X % freq;
        System.out.println(words.get(last));
    }
}
