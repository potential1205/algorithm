package daily.y2025.m06.d17.p42579_베스트앨범;

import java.util.*;
import java.util.stream.*;

class Solution2 {

    static class Music {
        int id;
        int playCount;
    }

    static class GenreInfo {
        int totalCount;
        List<Music> musicList = new ArrayList<>();
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answerList = new ArrayList<>();

        Map<String, GenreInfo> info = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            GenreInfo genreInfo = info.computeIfAbsent(genres[i], key -> new GenreInfo());
            genreInfo.totalCount += plays[i];
            Music music = new Music();
            music.id = i;
            music.playCount = plays[i];
            genreInfo.musicList.add(music);
        }

        List<Map.Entry<String, GenreInfo>> sortedList = info.entrySet().stream()
                .sorted(Comparator.comparing((Map.Entry<String, GenreInfo> entry) -> entry.getValue().totalCount)
                        .reversed())
                .collect(Collectors.toList());

        for (Map.Entry<String, GenreInfo> entry : sortedList) {
            List<Music> musicList = entry.getValue().musicList;

            musicList.sort(
                    Comparator.comparing((Music m) -> m.playCount, Comparator.reverseOrder())
                            .thenComparing(m -> m.id));

            musicList.stream()
                    .limit(2)
                    .map(m -> m.id)
                    .forEach(answerList::add);
        }

        return answerList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}