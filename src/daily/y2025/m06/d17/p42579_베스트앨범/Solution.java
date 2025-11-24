package daily.y2025.m06.d17.p42579_베스트앨범;

import java.util.*;
import java.util.stream.*;

class Solution {

    static class Music implements Comparable<Music> {
        int id;
        int playCount;

        Music(){}

        @Override
        public int compareTo(Music o) {
            int countGap = o.playCount - this.playCount;

            if (countGap == 0) {
                return this.id - o.id;
            } else {
                return countGap;
            }
        }
    }

    static class GenreInfo implements Comparable<GenreInfo> {
        int totalCount;
        List<Music> musicList = new ArrayList<>();

        @Override
        public int compareTo(GenreInfo o) {
            return this.totalCount - o.totalCount;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answerList = new ArrayList<>();

        Map<String, GenreInfo> info = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (info.containsKey(genres[i])) {
                GenreInfo genreInfo = info.get(genres[i]);
                genreInfo.totalCount += plays[i];

                Music music = new Music();
                music.id = i;
                music.playCount = plays[i];
                genreInfo.musicList.add(music);
            } else {
                GenreInfo genreInfo = new GenreInfo();
                genreInfo.totalCount += plays[i];

                info.put(genres[i], genreInfo);

                Music music = new Music();
                music.id = i;
                music.playCount = plays[i];
                genreInfo.musicList.add(music);
            }
        }

        List<Map.Entry<String, GenreInfo>> sortedList = info.entrySet()
                .stream()
                .sorted(Map.Entry.<String, GenreInfo>comparingByValue().reversed())
                .collect(Collectors.toList());

        for (Map.Entry<String, GenreInfo> entry : sortedList) {
            List<Music> musicList = entry.getValue().musicList;
            Collections.sort(musicList);

            int cnt = 0;

            for (Music music : musicList) {
                if (cnt == 2) break;
                answerList.add(music.id);
                cnt++;
            }
        }

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}