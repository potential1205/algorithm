import heapq
import sys
INF = sys.maxsize

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    dist[start] = 0

    while q:
        d, now = heapq.heappop(q)
        if dist[now] < d:
            continue
        
        for v, w in graph[now]:
            cost = d + w
            if cost < dist[v]:
                dist[v] = cost
                heapq.heappush(q, (cost, v))


if __name__ == "__main__":
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    dist = [INF]*(n+1)

    for _ in range(m):
        a, b, c = map(int, input().split())
        graph[a].append((b, c))
        graph[b].append((a, c))

    dijkstra(1)
    print(dist[n])