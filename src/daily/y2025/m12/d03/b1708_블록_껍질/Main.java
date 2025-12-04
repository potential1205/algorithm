package daily.y2025.m12.d03.b1708_블록_껍질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Node> list;

    static class Node implements Comparable<Node>{
        int y, x;
        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }

    static long ccw(Node a, Node b, Node c) {
        return (long)(b.x - a.x) * (c.y - a.y) - (long)(b.y - a.y) * (c.x - a.x);
    }

    static List<Node> convexHull(List<Node> list) {
        List<Node> lower = new ArrayList<>();
        for (Node node : list) {
            while (lower.size() >= 2 && ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), node) <= 0) {
                lower.remove(lower.get(lower.size() - 1));
            }
            lower.add(node);
        }

        List<Node> upper = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            Node node = list.get(i);
            while (upper.size() >= 2 && ccw(upper.get(upper.size() - 2), upper.get(upper.size() - 1), node) <= 0) {
                upper.remove(upper.get(upper.size() - 1));
            }
            upper.add(node);
        }

        lower.remove(lower.size() - 1);
        upper.remove(upper.size() - 1);
        lower.addAll(upper);

        return lower;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Node(y, x));
        }

        Collections.sort(list);
        List<Node> result = convexHull(list);
        System.out.println(result.size());
    }
}
