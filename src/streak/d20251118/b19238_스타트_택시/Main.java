package streak.d20251118.b19238_스타트_택시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer;
    static int n, m;
    static int taxiY, taxiX, taxiFuel;
    static int[][] board;
    static Person[] personArr;
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
        int dist;
        int sy, sx;
        int ty, tx;

        public Person(int id, int sy, int sx, int ty, int tx) {
            this.id = id;
            this.sy = sy;
            this.sx = sx;
            this.ty = ty;
            this.tx = tx;
        }

        @Override
        public int compareTo(Person o) {
            if (this.sy != o.sy) {
                return this.sy - o.sy;
            } else if (this.sx != o.sx) {
                return this.sx - o.sx;
            } else {
                return this.id - o.id;
            }
        }
    }

    static List<Person> search(int sy, int sx, int fuel) {
        List<Person> candidateList = new ArrayList<>();
        int minDist = 100000;

        boolean[][] visit = new boolean[n][n];
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sy, sx, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (board[cur.y][cur.x] < 0 &&  cur.dist <= minDist) {
                Person person = personArr[board[cur.y][cur.x] * -1];
                person.dist = cur.dist;
                candidateList.add(person);
                minDist = cur.dist;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || board[ny][nx] == 1) continue;
                if (cur.dist + 1 > fuel) continue;

                queue.offer(new Node(ny, nx, cur.dist + 1));
                visit[ny][nx] = true;
            }
        }

        return candidateList;
    }

    // 두 점 사이의 최단 거리 구하기
    static int getDist(int sy, int sx, int ty, int tx) {
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

        answer = -1;
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
        int taxiCnt = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int ty = Integer.parseInt(st.nextToken()) - 1;
            int tx = Integer.parseInt(st.nextToken()) - 1;

            board[sy][sx] = -1 * (i + 1); // 승객
            personArr[i + 1] = new Person(i + 1, sy, sx, ty, tx);
        }

        while (true) {
            // 현재 연료로 태울 수 있는 가장 가까운 손님 찾기
            List<Person> list = search(taxiY, taxiX, taxiFuel);
            Collections.sort(list);

            // 태울 수 있는 승객이 없으면
            if (list.isEmpty()) break;

            // 가장 가까운 손님 태우기
            Person person = list.get(0);
            board[person.sy][person.sx] = 0;
            taxiFuel -= person.dist; // 택시와 승객 사이의 최단 거리만큼 연료 사용

            // 출발지에서 목적지까지 거리 구하기
            int dist = getDist(person.sy, person.sx, person.ty, person.tx);
            if (dist < 0) break;

            // 출발지에서 목적지까지 거리만큼 연료 사용
            taxiFuel -= dist;
            if (taxiFuel < 0) break;

            // 목적지 도착
            taxiY = person.ty;
            taxiX = person.tx;

            // 목적지에 도착했다면 이동거리 x 2만큼 충전
            taxiFuel += (dist * 2);

            // 처리한 손님 수 카운트
            taxiCnt++;

            // 모든 승객을 이동시켰을 경우
            if (taxiCnt == m) {
                answer = taxiFuel;
                break;
            }
        }

        System.out.println(answer);
    }
}
