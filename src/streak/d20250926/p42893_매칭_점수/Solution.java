package streak.d20250926.p42893_매칭_점수;

import java.util.*;
import java.util.regex.*;

class Solution {

    public int solution(String word, String[] pages) {
        word = word.toLowerCase();

        Map<String, Integer> basicMap = new HashMap<>();
        Map<String, Integer> outMap = new HashMap<>();
        Map<String, List<String>> sourceMap = new HashMap<>();
        Map<String, Double> totalMap = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();

        Pattern pageUrl  = Pattern.compile("<meta[^>]*property=\"og:url\"[^>]*content=\"([^\"]*)\"[^>]*>");
        Pattern pageLink = Pattern.compile("<a[^>]*href=\"([^\"]*)\"[^>]*>");
        Pattern pageBody = Pattern.compile("<body>([\\s\\S]*?)</body>");

        for (int i = 0; i < pages.length; i++) {
            String html = pages[i].toLowerCase();

            // 내 링크
            Matcher m = pageUrl.matcher(html);
            String myLink = "";
            if (m.find()) myLink = m.group(1);

            // 본문 추출
            String body = "";
            Matcher bm = pageBody.matcher(html);
            if (bm.find()) body = bm.group(1);

            // 기본 점수
            Pattern wordPat = Pattern.compile("(?<![a-z])" + Pattern.quote(word) + "(?![a-z])");
            int basicScore = 0;
            if (!body.isEmpty()) {
                Matcher wm = wordPat.matcher(body);
                while (wm.find()) basicScore++;
            }

            // 외부 링크 수집
            List<String> list = new ArrayList<>();
            Matcher lm = pageLink.matcher(html);
            int outLinkCount = 0;
            while (lm.find()) {
                list.add(lm.group(1));
                outLinkCount++;
            }

            basicMap.put(myLink, basicScore);
            outMap.put(myLink, outLinkCount);
            sourceMap.put(myLink, list);
            totalMap.put(myLink, (double) basicScore);
            indexMap.put(myLink, i);
        }

        // 링크 점수 분배
        for (String from : sourceMap.keySet()) {
            int basicScore = basicMap.get(from);
            int outCount   = outMap.get(from);
            if (outCount == 0 || basicScore == 0) continue;

            double share = basicScore / (double) outCount;
            for (String to : sourceMap.get(from)) {
                if (totalMap.containsKey(to)) {
                    totalMap.put(to, totalMap.get(to) + share);
                }
            }
        }

        // 최댓값 선택(동점 시 더 작은 인덱스)
        double maxScore = -1;
        int answer = Integer.MAX_VALUE;

        for (String key : totalMap.keySet()) {
            double score = totalMap.get(key);
            int idx = indexMap.get(key);

            if (score > maxScore || (score == maxScore && answer > idx)) {
                maxScore = score;
                answer = idx;
            }
        }

        return answer;
    }
}