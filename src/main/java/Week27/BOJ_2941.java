package Week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        String str = br.readLine();
        String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        for(int i=0;i<croatia.length;i++) {
            if(str.contains(croatia[i])) {
                str = str.replaceAll(croatia[i], "#");
            }
        }
        System.out.println(str.length());
    }
}
