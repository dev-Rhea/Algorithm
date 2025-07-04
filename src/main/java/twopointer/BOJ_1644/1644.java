package twopointer.BOJ_1644;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    
    static int N;
    static List<Integer> primes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        primes = new ArrayList<>();
        
        boolean[] isPrime = new boolean[N + 1];
        for(int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }
        
        for(int i = 2; i * i <= N; i++) {
            if(isPrime[i]) {
                for(int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        for(int i = 2; i <= N; i++) {
            if(isPrime[i]) {
                primes.add(i);
            }
        }
        
        System.out.println(calculate());
    }
    
    static int calculate() {
        int cnt = 0;
        int left = 0;
        int sum = 0;
        
        for(int right = 0; right < primes.size(); right++) {
            sum += primes.get(right);
            
            while(sum > N && left <= right) {
                sum -= primes.get(left);
                left++;
            }
            
            if(sum == N) {
                cnt++;
            }
        }
        
        return cnt;
    }
}