package daily.y2024.m12.d15;

import java.util.*;

public class Main {

    static List<Integer> nums;
    static List<Integer> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        nums = new ArrayList<>();
        answer = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            nums.add(sc.nextInt());
        }

        Collections.sort(nums);

        for (int i = 0; i < N; i++) {
            int cnt = 0;

            for (int j = 0; j < 5; j++) {
                if(nums.contains(nums.get(i)+j)) {
                    cnt++;
                }
            }

            answer.add(cnt);
        }

        Collections.sort(answer, Collections.reverseOrder());
        System.out.println(5-answer.get(0));
    }
}
