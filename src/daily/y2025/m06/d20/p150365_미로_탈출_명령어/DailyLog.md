
## 정리
N x M 보드에서 시작점부터 목적지까지 이동하는 경로를 찾는 문제인데
고려해야할 점은 
1. 반드시 k번 이동해야함
2. 경로가 둘 이상인 경우 방향의 우선순위가 높은 경로가 출력되어야 함
3. 같은 지점에 중복 방문이 가능함

보드를 네 가지 방향으로 탐색하는 문제이므로 BFS를 사용했다.
이 때 현재 위치 (y, x)와 방문한 경로를 저장할 path와 거쳐온 이동 횟수 cnt를 관리할 수 있도록 Node를 정의했고
거쳐온 이동 횟수의 내림차순, 경로의 오름차순을 정렬 기준을 설정했다.
왜냐하면 정확히 k번의 이동을 통해 목적지에 도달해야하고 이동 횟수가 같다면 방향의 우선순위를 고려해 이동해야되기 때문이다.

```java
static class Node implements Comparable<Node> {
    int y;
    int x;
    int cnt;
    String path;

    Node() {
    }

    Node(int y, int x, int cnt, String path) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
        this.path = path;
    }

    @Override
    public int compareTo(Node o) {
        if (o.cnt == this.cnt) {
            return o.cnt - this.cnt;
        } else {
            return this.path.compareTo(o.path);
        }
    }
}
```

그런데 실수했던 부분은 움직인 횟수가 k 미만인 경우만 queue에 다시 넣어주는 부분이었는데
이 조건으로만 필터링하면 동서남북 방향에 대해서 각 이동마다 4개의 경우의 수가 발생해서
최악의 경우 N과 M이 최대 50이고 시간복잡도는 O(4^K) 로 사실상 계산이 불가능해 시간초과가 발생한다.

**시간초과**
```java
while(!queue.isEmpty()) {
    Node node = queue.poll();

    if (node.cnt == k && node.y == r && node.x == c) {
        answer  = node.path;
        break;
    }

    for (int i = 0; i < 4; i++) {
        int ny = node.y + dy[i];
        int nx = node.x + dx[i];

        if (ny < 0 || nx < 0 || ny >= n || nx >= m || node.cnt >= k) {
            continue;
        }

        queue.offer(new Node(ny, nx, node.cnt + 1, node.path + arr[i]));
    }
}
```

시간 초과를 개선하기 위해 queue에 담기는 경우의 수를 획기적으로 줄여야한다.
핵심은 네 가지 방향을 for문에 의해 통해 남 -> 서 -> 동 -> 북 방향으로 순차적으로 탐색하게 될 때
하나의 방향이 조건에 부합한다면 그 방향의 이동만 queue에 추가하고 break를 걸어줘야 한다는 점이다.
왜냐하면 해당 조건을 만족하는 첫번째 경로가 cnt == k를 만족하면서도 사전순으로 가장 빠른 path이기 때문에 다른 경로를 탐색할 필요가 없다.
따라서 시간복잡도는 O(4K) 로 개선이 된다.

추가로 현재 위치에서 이동할 수 있는 횟수와 목적지까지의 거리의 차가 반드시 짝수여야만 목적지에 도달할 수 있다는 것도 고려해야 한다.
n <= k일 때 n번의 이동으로 목적지에 방문할 수 있다면 k-n번의 이동이 추가로 필요한데 목적지로부터 인접한 지점을 방문하고 돌아와야 하므로 반드시 짝수여야 한다.

**해결**
```java
static char[] arr = {'d', 'l', 'r', 'u'};
static int[] dy = {1, 0, 0, -1};
static int[] dx = {0, -1, 1, 0};
```

```java

while(!queue.isEmpty()) {
    Node node = queue.poll();

    if (node.cnt == k && node.y == r && node.x == c) {
        answer  = node.path;
        break;
    }
    
    for (int i = 0; i < 4; i++) {
        int ny = node.y + dy[i];
        int nx = node.x + dx[i];
        
        if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
            continue;
        }
        
        int remainMoveCnt = k - node.cnt - 1;
        int newDist = Math.abs(r - ny) + Math.abs(c - nx);
        
        if (newDist <= remainMoveCnt && (remainMoveCnt - newDist) % 2 == 0) {
            queue.offer(new Node(ny, nx, node.cnt + 1, node.path + arr[i]));
            break;
        }
    }
}
```


