package daily.y2025.m11.d11.b13414_수강신청;

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
        int l = Integer.parseInt(st.nextToken());
        for (int i = 0; i < l; i++) {
            String str = br.readLine();
            set.remove(str);
            set.add(str);
        }

        Iterator iter = set.iterator();
        for (int i = 0; i < k && iter.hasNext(); i++) {
            System.out.println(iter.next());
        }
    }
}
