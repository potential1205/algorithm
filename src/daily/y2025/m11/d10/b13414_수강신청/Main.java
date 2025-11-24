package daily.y2025.m11.d10.b13414_수강신청;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 순서 보장되는 set
        LinkedHashSet<String> set = new LinkedHashSet<>();

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            if (set.contains(str)) {
                set.remove(str);
            }
            set.add(str);
        }


        Iterator<String> it = set.iterator();
        for (int i = 0; i < k && it.hasNext(); i++) {
            System.out.println(it.next());
        }
    }
}
