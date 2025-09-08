package implementation.BOJ_16235;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
    static class Tree implements Comparable<Tree> {
        int age, num;
        
        Tree(int age, int num) {
            this.age = age;
            this.num = num;
        }
        
        @Override
        public int compareTo(Tree other) {
            return this.age - other.age;
        }
    }

    static int N, M, K;
    static int[][] map, origin;
    static TreeSet<Tree>[][] trees;
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        origin = new int[N+1][N+1];
        trees = new TreeSet[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                trees[i][j] = new TreeSet<>();
            }
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = 5;
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            trees[x][y].add(new Tree(age, 1));
        }

        for(int year = 0; year < K; year++) {
            spring();
            fall();
            winter();
        }

        int result = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for(Tree tree : trees[i][j]) {
                    result += tree.num;
                }
            }
        }

        System.out.println(result);
    }

    static void spring() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(trees[i][j].isEmpty()) continue;

                TreeSet<Tree> newTrees = new TreeSet<>();
                int deadNutrient = 0;

                for(Tree tree : trees[i][j]) {
                    Tree newTree = new Tree(tree.age + 1, 0);
                    
                    while(tree.num > 0) {
                        if(map[i][j] < tree.age) {
                            deadNutrient += tree.num * (tree.age / 2);
                            break;
                        }
                        tree.num--;
                        map[i][j] -= tree.age;
                        newTree.num++;
                    }
                    
                    if(newTree.num > 0) {
                        newTrees.add(newTree);
                    }
                }

                trees[i][j] = newTrees;
                map[i][j] += deadNutrient;
            }
        }
    }

    static void fall() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int reproduct = 0;
                
                for(Tree tree : trees[i][j]) {
                    if(tree.age % 5 == 0) {
                        reproduct += tree.num;
                    }
                }
                
                if(reproduct > 0) {
                    for(int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                            if(!trees[nx][ny].isEmpty() && trees[nx][ny].first().age == 1) {
                                trees[nx][ny].first().num += reproduct;
                            } else {
                                trees[nx][ny].add(new Tree(1, reproduct));
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                map[i][j] += origin[i][j];
            }
        }
    }
}