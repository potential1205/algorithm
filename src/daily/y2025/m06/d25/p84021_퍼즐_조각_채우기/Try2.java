package daily.y2025.m06.d25.p84021_퍼즐_조각_채우기;

import java.util.*;

class Try2 {

    static int answer;

    static int n, blockId;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] tableVisit;

    static class Block {
        int id;
        boolean[][] pointList;

        Block(int id, boolean[][] pointList) {
            this.id = id;
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

    public static boolean[][] rotate(boolean[][] before) {
        boolean[][] after = new boolean[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                after[j][5 - i] = before[i][j];
            }
        }

        return after;
    }

    static boolean check(int sy, int sx, int[][] board, Block block) {
        return true;
    }

    static void dfs(int depth, int value, List<List<Block>> info, int[][] board) {
        if (depth == blockId) {
            answer = Math.max(answer, value);
            return;
        }

        List<Block> listBlock = info.get(depth);

        for (int i = 0; i < 4; i++) {
            Block block = listBlock.get(i);

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (check(j, k, board, block)) {




                        dfs(depth + 1, value, info, board);
                    }
                }
            }

        }
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        n = game_board.length;
        List<List<Block>> list = new ArrayList<>();
        tableVisit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!tableVisit[i][j] && table[i][j] == 1) {
                    List<Point> pointList = createBlockInfo(i, j, table);
                    int minY = Integer.MAX_VALUE;
                    int minX = Integer.MAX_VALUE;

                    for (Point point : pointList) {
                        if (minY > point.y) {
                            minY = point.y;
                        }

                        if (minX > point.x) {
                            minX = point.x;
                        }
                    }

                    boolean[][] temp = new boolean[6][6];
                    List<Block> blockList = new ArrayList<>();

                    for (Point point : pointList) {
                        temp[point.y - minY][point.x - minX] = true;
                    }

                    blockList.add(new Block(blockId, temp)); // 0도

                    temp = rotate(temp);
                    blockList.add(new Block(blockId, temp)); // 90도

                    temp = rotate(temp);
                    blockList.add(new Block(blockId, temp)); // 180도

                    temp = rotate(temp);
                    blockList.add(new Block(blockId, temp)); // 270도

                    list.add(blockList);
                    blockId++;
                }
            }
        }

        dfs(0, 0, list, game_board);

        return answer;
    }
}