package implementation.BOJ_2064;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder network = new StringBuilder();
        StringBuilder subnet = new StringBuilder();

        String[][] ip = new String[n][4];
        for(int i=0;i<n;i++) {
            ip[i] = br.readLine().split("\\.");
        }

        boolean flag = false;

        for(int i=0;i<4;i++) {
            int min = Integer.parseInt(ip[0][i]);
            int max = Integer.parseInt(ip[0][i]);

            for(int j=1;j<n;j++) {
                min = min & Integer.parseInt(ip[j][i]);
                max = max | Integer.parseInt(ip[j][i]);
            }

            if(!flag) {
                network.append(min).append('.');
                subnet.append(255 - (max - min)).append('.');
            }
            else {
                network.append(0).append('.');
                subnet.append(0).append('.');
            }

            if(min != max) flag = true;
        }

        network.deleteCharAt(network.length() - 1);
        subnet.deleteCharAt(subnet.length() - 1);
        System.out.println(network);
        System.out.println(subnet);
    }
}