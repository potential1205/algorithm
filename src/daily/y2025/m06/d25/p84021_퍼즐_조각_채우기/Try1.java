package daily.y2025.m06.d25.p84021_퍼즐_조각_채우기;

import java.util.*;

class Try1 {

    static int n, blockId;
    static List<Block> blockList = new ArrayList<>();

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] tableVisit;

    static class Block {
        int id;
        List<Point> pointList = new ArrayList<>();

        Block(int blockId, List<Point> pointList) {
            this.id = blockId;
            this.pointList = pointList;
        }
    }

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static List<Point> createBlockInfo(int sy, int sx, int[][] table) {
        List<Point> pointList = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sy, sx));
        tableVisit[sy][sx] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            pointList.add(point);

            for (int i = 0; i < 4; i++) {
                int ny = point.y + dy[i];
                int nx = point.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n || table[ny][nx] == 0 || tableVisit[ny][nx]) {
                    continue;
                }

                queue.offer(new Point(ny, nx));
                tableVisit[ny][nx] = true;
            }
        }

        return pointList;
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        n = game_board.length;

        tableVisit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!tableVisit[i][j] && table[i][j] == 1) {
                    List<Point> pointList = createBlockInfo(i, j, table);
                    Block block = new Block(blockId, pointList);
                    blockList.add(block);
                    blockId++;
                }
            }
        }

        for (int i = 0; i < blockId; i++) {
            Block block = blockList.get(i);
            System.out.println("블록 번호 : " + block.id + " ");
            for (Point point : block.pointList) {
                System.out.print(point.y + " " + point.x + " ");
            }
            System.out.println();
        }

        return answer;
    }
}