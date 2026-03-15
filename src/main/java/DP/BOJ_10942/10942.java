package DP.BOJ_10942;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];
        boolean[][] dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++) {
            for(int j=i;j>=1;j--) {
                if(nums[i] == nums[j]) {
                    if(i - j < 2 || dp[j+1][i-1]) dp[j][i] = true;
                }import java.io.BufferedReader;
                import java.io.IOException;
                import java.io.InputStreamReader;
                import java.util.StringTokenizer;
                
                class Main {
                    public static void main(String[] args) throws IOException {
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        StringBuilder sb = new StringBuilder();
                
                        int N = Integer.parseInt(br.readLine());
                        int[] nums = new int[N+1];
                        boolean[][] dp = new boolean[N+1][N+1];
                
                        StringTokenizer st = new StringTokenizer(br.readLine());
                        for(int i=1;i<=N;i++) {
                            nums[i] = Integer.parseInt(st.nextToken());
                        }
                
                        for(int i=1;i<=N;i++) {
                            for(int j=i;j>=1;j--) {
                                if(nums[i] == nums[j]) {
                                    if(i - j < 2 || dp[j+1][i-1]) dp[j][i] = true;
                                }
                            }
                        }
                
                        int M = Integer.parseInt(br.readLine());
                        for(int i=0;i<M;i++) {
                            st = new StringTokenizer(br.readLine());
                            int S = Integer.parseInt(st.nextToken());
                            int E = Integer.parseInt(st.nextToken());
                
                            sb.append(dp[S][E] ? 1 : 0).append('\n');
                        }
                
                        System.out.print(sb);
                    }
                }import java.io.BufferedReader;
                import java.io.IOException;
                import java.io.InputStreamReader;
                import java.util.StringTokenizer;
                
                class Main {
                    public static void main(String[] args) throws IOException {
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        StringBuilder sb = new StringBuilder();
                
                        int N = Integer.parseInt(br.readLine());
                        int[] nums = new int[N+1];
                        boolean[][] dp = new boolean[N+1][N+1];
                
                        StringTokenizer st = new StringTokenizer(br.readLine());
                        for(int i=1;i<=N;i++) {
                            nums[i] = Integer.parseInt(st.nextToken());
                        }
                
                        for(int i=1;i<=N;i++) {
                            for(int j=i;j>=1;j--) {
                                if(nums[i] == nums[j]) {
                                    if(i - j < 2 || dp[j+1][i-1]) dp[j][i] = true;
                                }
                            }
                        }
                
                        int M = Integer.parseInt(br.readLine());
                        for(int i=0;i<M;i++) {
                            st = new StringTokenizer(br.readLine());
                            int S = Integer.parseInt(st.nextToken());
                            int E = Integer.parseInt(st.nextToken());
                
                            sb.append(dp[S][E] ? 1 : 0).append('\n');
                        }
                
                        System.out.print(sb);
                    }
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(dp[S][E] ? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }
}