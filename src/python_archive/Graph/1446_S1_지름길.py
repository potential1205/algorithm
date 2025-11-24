
import heapq

def solve(start):
    q= []
    heapq.heappush(q,(0,start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if dist > distance[now]:
            continue

        for i in graph[now]:
            cost = dist + i[1]
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q,(cost,i[0]))


if __name__ == "__main__":
    n, d = map(int,input().split())
    graph = [[] for i in range(d+1)]
    distance = [float("inf")] * (d+1)

    for i in range(d):
        graph[i].append((i+1,1))

    for i in range(n):
        a,b,c = map(int,input().split())
        if b>d:
            continue
        graph[a].append((b,c))

    solve(0)
    print(distance[-1])