package daily.y2025.m08.d30.b1049_기타줄;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int min;

        int[] unit = new int[m];
        int[] pack = new int[m];
        for(int i = 0; i < m; i++){
            pack[i] = sc.nextInt();
            unit[i] = sc.nextInt();
        }
        Arrays.sort(unit);
        Arrays.sort(pack);

        min = Math.min(((n / 6) + 1) * pack[0], n * unit[0]);
        min = Math.min(min, ((n / 6)) * pack[0] + (n % 6) * unit[0]);

        System.out.println(min);
    }
}