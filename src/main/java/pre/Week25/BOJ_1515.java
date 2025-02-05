package pre.Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1515 {
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        int pointer = 0;
        int base = 0;

        while (base++ <= 30000) {
            String temp = String.valueOf(base);

            for(int i=0;i<temp.length();i++) {
                if(temp.charAt(i) == str.charAt(pointer)) pointer++;

                if (pointer == str.length()) {
                    System.out.println(base);
                    return;
                }
            }
        }
    }

}
