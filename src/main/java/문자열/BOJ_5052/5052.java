package 문자열.BOJ_5052;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            int n = Integer.parseInt(br.readLine());

            String[] nums = new String[n];
            for(int i=0;i<n;i++) {
                nums[i] = br.readLine();
            }
            Arrays.sort(nums);
            
            boolean isValid = true;

            for(int i=0;i<n-1;i++) {
                if(nums[i+1].startsWith(nums[i])) {
                    isValid = false;
                    break;
                }
            }

            System.out.println(isValid ? "YES" : "NO");
        }
    }
}