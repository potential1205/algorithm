package daily.y2025.m11.d19.b19238_스타트_택시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer = -1;
    static int n, m;
    static Person[] personArr;
    static int[][] board;
    static int taxiY, taxiX, taxiFuel;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Node {
        int y, x;
        int dist;

        Node(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    static class Person implements Comparable<Person> {
        int id;
        int sy, sx;
        int ty, tx;
        int dist;

        public Person(int id, int sy, int sx, int ty, int tx) {
            this.id = id;
            this.sy = sy;
            this.sx = sx;
            this.ty = ty;
            this.tx = tx;
        }

        @Override
        public int compareTo(Person o) {
            if (this.sy != o.sy) return this.sy - o.sy;
            else if (this.sx != o.sx) return this.sx - o.sx;
            else return this.id - o.id;
        }
    }

    static List<Person> find(int sy, int sx, int fuel) {
        List<Person> result = new ArrayList<>();
        int minDist = 100000;

        boolean[][] visit = new boolean[n][n];
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sy, sx, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (board[cur.y][cur.x] < 0 && cur.dist <= minDist) {
                int idx = board[cur.y][cur.x] * -1;
                Person person = personArr[idx];
                person.dist = cur.dist;
                result.add(person);
                minDist = cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || board[ny][nx] == 1 || cur.dist + 1 > fuel) continue;
                queue.offer(new Node(ny, nx, cur.dist + 1));
                visit[ny][nx] = true;
            }
        }

        return result;
    }

    static int calDist(int sy, int sx, int ty, int tx) {
        boolean[][] visit = new boolean[n][n];
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sy, sx, 0));
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.y == ty && cur.x == tx) {
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || board[ny][nx] == 1) continue;
                queue.offer(new Node(ny, nx, cur.dist + 1));
                visit[ny][nx] = true;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        taxiFuel = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        personArr = new Person[m + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxiY = Integer.parseInt(st.nextToken()) - 1;
        taxiX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int ty = Integer.parseInt(st.nextToken()) - 1;
            int tx = Integer.parseInt(st.nextToken()) - 1;

            board[sy][sx] = -1 * (i + 1); // 승객
            personArr[i + 1] = new Person(i + 1, sy, sx, ty, tx);
        }

        int cnt = 0;

        while (true) {
            List<Person> personList = find(taxiY, taxiX, taxiFuel);
            Collections.sort(personList);

            // 태울 손님이 없으면
            if (personList.isEmpty()) break;

            // 손님 태우기
            Person person = personList.get(0);
            board[person.sy][person.sx] = 0;
            taxiFuel -= person.dist;

            // 목적지까지 거리 계산 후 이동
            int dist = calDist(person.sy, person.sx, person.ty, person.tx);
            taxiFuel -= dist;

            if (taxiFuel < 0) break;
            taxiFuel += (dist * 2);
            taxiY = person.ty;
            taxiX = person.tx;
            cnt++;

            if (cnt == m) {
                answer = taxiFuel;
                break;
            }
        }

        System.out.println(answer);
    }
}
