package dijkstra.BOJ_13168;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static class Point {
        int city;
        double cost;

        Point(int city, double cost) {
            this.city = city;
            this.cost = cost;
        }
    }

    static class Transport {
        String type;
        int destination;
        double fare;

        Transport(String type, int destination, double fare) {
            this.type = type;
            this.destination = destination;
            this.fare = fare;
        }
    }

    static int N, R, M, K;
    static double r;
    static String[] cities;
    static HashMap<String, Integer> cityMap = new HashMap<>();
    static int[] path;
    
    static double[][] normalDist;
    static double[][] rTicketDist;
    static ArrayList<Transport>[] graph;
    
    static PriorityQueue<Point> pq = new PriorityQueue<>(
        (x, y) -> Double.compare(x.cost, y.cost)
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Double.parseDouble(st.nextToken());

        cities = new String[N+1];
        graph = new ArrayList[N+1];
        normalDist = new double[N+1][N+1];
        rTicketDist = new double[N+1][N+1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                normalDist[i][j] = Double.MAX_VALUE;
                rTicketDist[i][j] = Double.MAX_VALUE;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cities[i] = st.nextToken();
            cityMap.put(cities[i], i);
        }

        M = Integer.parseInt(br.readLine());
        path = new int[M+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            String cityName = st.nextToken();
            path[i] = cityMap.get(cityName);
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String transportName = st.nextToken();
            String startCity = st.nextToken();
            String endCity = st.nextToken();
            double fare = Double.parseDouble(st.nextToken());

            int startIdx = cityMap.get(startCity);
            int endIdx = cityMap.get(endCity);

            graph[startIdx].add(new Transport(transportName, endIdx, fare));
            graph[endIdx].add(new Transport(transportName, startIdx, fare));
        }

        for (int i = 1; i <= N; i++) {
            calculateNormalFare(i);
            calculateRTicketFare(i);
        }

        double normalFare = 0;
        double rTicketFare = r;

        for (int i = 1; i < M; i++) {
            normalFare += normalDist[path[i]][path[i+1]];
            rTicketFare += rTicketDist[path[i]][path[i+1]];
        }

        System.out.println(normalFare > rTicketFare ? "Yes" : "No");
    }

    static void calculateNormalFare(int start) {
        pq.clear();
        pq.add(new Point(start, 0));

        while (!pq.isEmpty()) {
            Point current = pq.poll();
            
            for (Transport next : graph[current.city]) {
                if (normalDist[start][next.destination] > current.cost + next.fare) {
                    normalDist[start][next.destination] = current.cost + next.fare;
                    pq.add(new Point(next.destination, normalDist[start][next.destination]));
                }
            }
        }
    }

    static void calculateRTicketFare(int start) {
        pq.clear();
        pq.add(new Point(start, 0));

        while (!pq.isEmpty()) {
            Point current = pq.poll();
            
            for (Transport next : graph[current.city]) {
                double nextFare = next.fare;
                String transportType = next.type;

                if (transportType.equals("Mugunghwa") || transportType.equals("ITX-Saemaeul") || 
                    transportType.equals("ITX-Cheongchun")) {
                    nextFare = 0;  // 무료
                } else if (transportType.equals("S-Train") || transportType.equals("V-Train")) {
                    nextFare = nextFare / 2;
                }

                if (rTicketDist[start][next.destination] > current.cost + nextFare) {
                    rTicketDist[start][next.destination] = current.cost + nextFare;
                    pq.add(new Point(next.destination, rTicketDist[start][next.destination]));
                }
            }
        }
    }
}