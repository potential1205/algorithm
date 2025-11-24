
## 정리
### value 기준으로 Map 정렬하기 Comparable vs Comparator
음악의 장르에 따라 전체 재생 횟수 기준으로 정렬하고 음악의 경우는 재생 횟수와 고유 번호를 기준으로 정렬하는 경우
직관적으로 생각해보면 장르가 key 값이고 value가 totalCount, List<Music>인 구조가 가장 먼저 떠올랐다.

그런데 문제는 key 값인 장르를 기준으로 정렬하는 것이 아니라 value 중 totalCount 값을 기준으로 내림차순으로 정렬을
해야한다는 것인데 TreeMap의 경우 key 값을 기준으로 정렬하기 때문에 value를 기준으로 Map을 정렬하기 위해선 
sort() 메서드와 함께 정렬 기준을 Comparator로 정의해주어야 한다.

물론 정렬 기준을 다음과 같이 class에 Comparable을 상속받아서 재정의할 수도 있지만
로직마다 정렬 조건이 달라질 경우 유연성이 떨어지는 단점이 있다.
그래서 오늘은 Comparator를 이용해 필요할 때마다 정렬 기준을 명시하는 방법으로 풀어보았다.

**Comparable**
```java
static class GenreInfo implements Comparable<GenreInfo> {
        int totalCount;
        List<Music> musicList = new ArrayList<>();

        @Override
        public int compareTo(GenreInfo o) {
            return this.totalCount - o.totalCount;
        }
    }
```

먼저 entrySet()과 stream()을 이용해 Map의 Entry 요소를 순회하며 sorted()에 명시된 정렬 조건을 기준으로 정렬된
List를 반환받을 수 있다.
Comparator의 comparing() 메서드에 기준으로 삼을 값의 타입과 값을 명시해주면 된다.

**Map to sorted List**
```java
List<Map.Entry<String, GenreInfo>> sortedList = info.entrySet().stream()
        .sorted(Comparator.comparing((Map.Entry<String, GenreInfo> entry) -> entry.getValue().totalCount)
                .reversed())
        .collect(Collectors.toList());
```

마찬가지인데 정렬된 결과를 취합할 때 Collectors의 toMap() 메서드를 이용하여 Linked Hash Map을 반환할 수 있다. 
Linked Hash Map의 경우 입력 순서가 유지되는 Map이다.

**Map to sorted Map(Linked Hash Map)**
```java
Map<String, GenreInfo> sortedMap = info.entrySet().stream()
        .sorted(Comparator.comparing((Map.Entry<String, GenreInfo> entry) -> entry.getValue().totalCount).reversed())
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,      // merge function : 충돌이 발생한 경우 e1=무시, e2=덮어쓰기
                LinkedHashMap::new
        ));
```


## 마치며
알고리즘을 해결할 때 사실 Java에선 Stream을 그렇게 선호하진 않지만 필요에 따라서는 또 자주쓰이는 문법은 기억해두는 것이
좋을 것 같다. key를 기반으로 정렬하거나 데이터를 관리하는 문제가 코딩테스트에서 꽤 보인다.