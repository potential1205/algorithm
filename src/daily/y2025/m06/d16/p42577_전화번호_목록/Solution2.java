package daily.y2025.m06.d16.p42577_전화번호_목록;

import java.util.Arrays;

class Solution2 {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i].length() > phone_book[i + 1].length()) {
                continue;
            }

            if (phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}