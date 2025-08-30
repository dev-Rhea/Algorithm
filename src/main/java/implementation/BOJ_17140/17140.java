package implementation.BOJ_17140;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    static class Number implements Comparable<Number> {
        int n, cnt;
        Number(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number o) {
            if(this.cnt == o.cnt) return this.n - o.n;
            else return this.cnt - o.cnt;
        }
    }

    static int[][] A = new int[101][101];
    static int r, c, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());

        for(int i=0;i<3;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(op());
    }

    static int op() {
        int row =3, col = 3;
        int T = 0;

        while(true) {
            if(A[r][c] == k) break;
            if(T > 100) {
                T = -1;
                break;
            }

            int[][] temp = new int[101][101];
            if(row >= col) {
                int newCol = 0;
                for(int i=0;i<row;i++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for(int j=0;j<col;j++) {
                        if(A[i][j] != 0) map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                        
                        List<Number> list = new ArrayList<>();
                        for(Integer key : map.keySet()) {
                            list.add(new Number(key, map.get(key)));
                        }
                        Collections.sort(list);
                        

                        int idx = 0;
                        for(Number num : list) {
                            if(idx >= 100) break;
                            temp[i][idx++] = num.n;
                            if(idx >= 100) break;
                            temp[i][idx++] = num.cnt;
                        }
                        newCol = Math.max(newCol, idx);
                    }
                }
                col = newCol;
            }
            else {
                int newRow = 0;
                for(int i=0;i<col;i++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for(int j=0;j<row;j++) {
                        if(A[j][i] != 0) map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);

                        List<Number> list = new ArrayList<>();
                        for(Integer key : map.keySet()) {
                            list.add(new Number(key, map.get(key)));
                        }
                        Collections.sort(list);

                        int idx = 0;
                        for(Number num : list) {
                            if(idx >= 100) break;
                            temp[idx++][i] = num.n;
                            if(idx >= 100) break;
                            temp[idx++][i] = num.cnt;
                        }
                        newRow = Math.max(newRow, idx);
                    }
                }
                row = newRow;
            }
            A = temp;
            T++;
        }
        return T;
    }
}