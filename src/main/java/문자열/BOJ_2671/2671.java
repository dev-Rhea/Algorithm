package 문자열.BOJ_2671;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String pattern = "(100+1+|01)+";
        
        if(str.matches(pattern)) System.out.println("SUBMARINE");
        else System.out.println("NOISE");
    }
}