
## 정리

### 요구사항
- 최악의 경우 n = 1,000,000
- 삽입
- 최댓값 / 최솟값 삭제
- 최댓값 / 최솟값 조회

=> O(logN)의 시간복잡도를 가지도록 알고리즘 설계가 필요함.

### 이중우선순위 큐
이런 경우 이중우선순위 큐를 이용하여 최댓값과 최솟값을 모두 O(logN) 조회,삭제,삽입 할 수 있다.
이중우선순위 큐의 경우 TreeMap 또는 최대, 최소 힙의 중첩으로 구현할 수 있다.

TreeMap의 경우 Red-Black 균형 트리로 구현되어 매 삽입, 삭제시 key값을 기준으로 정렬 상태가 유지된다.
그래서 삽입, 삭제는 물론이고 firstKey(), lastKey()와 같은 메서드로 최소, 최대 값 접근이 가능하다.

**TreeMap**
```java
TreeMap<Integer, Integer> map = new TreeMap<>();
map.firstKey();
map.lastKey();
```

다른 방법으로는 minHeap과 maxHeap 각각에 같은 데이터 상태를 유지하는 방법도 있다.
다만 삽입, 삭제 연산에 대해서 maxHeap과 minHeap 각각 한번씩 연산해줘야 하므로 최소 데이터가 2배, 연산 횟수가 2배 늘어나게 된다.

**PriorityQueue**
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```
