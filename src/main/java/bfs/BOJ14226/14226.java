package bfs.BOJ14226;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Main {

    static class Node {
        int emo, clip, op;
        Node(int emo, int clip, int op) {
            this.emo = emo;
            this.clip = clip;
            this.op = op;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        int emoticon = 0;

        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[2001][2001];

        queue.add(new Node(1, 0, 0 ));
        visited[1][0] = true;


        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.emo == S) {
                emoticon = now.op;
                break;
            }

            if(!visited[now.emo][now.emo] && now.emo > 0) {
                queue.add(new Node(now.emo, now.emo, now.op+1));
                visited[now.emo][now.emo] = true;

            }
            if(now.clip > 0 && (now.emo + now.clip) < 2000 && !visited[now.emo + now.clip][now.clip]) {
                queue.add(new Node(now.emo + now.clip, now.clip, now.op + 1));
                visited[now.emo][now.clip] = true;

            }
            if(now.emo > 0 && !visited[now.emo - 1][now.clip]) {
                queue.add(new Node(now.emo - 1, now.clip, now.op+1));
                visited[now.emo - 1][now.clip] = true;
            }

            
        }

        System.out.println(emoticon);
    }
}

/**
 * 이모티콘 S 개
 * 
 * 입력 1부터 시작,
 * 화면의 이모티콘을 모두 복사하여 클립보드에 저장
 * 
 * 클립보드 이모티콘 화면에 붙여넣기
 * 
 * 이모티콘 중 하나 삭제
 * 
 * 복사하면 덮어쓰기 됨
 * 
 * 
 * 이모티콘 연산 
 * 1. 복사
 * 2. 붙여넣기
 * 3. 삭제
 * 
 */