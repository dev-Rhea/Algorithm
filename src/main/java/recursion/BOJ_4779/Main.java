package recursion.BOJ_4779;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String input = "";

        while(sc.hasNext()) {
            int N = sc.nextInt();

            for(int i=0;i<N+1;i++) {
                if(i == 0) {
                    input = "-";
                }
                else {
                    input = input + " ".repeat(input.length()) + input;
                }
            }
            System.out.println(input);
        }
    }

}
