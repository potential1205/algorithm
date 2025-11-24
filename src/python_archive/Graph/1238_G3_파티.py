import heapq
import sys

def solution(start):
    distance = [sys.maxsize for i in range(n+1)]
    distance[start] = 0

    heap = []
    heapq.heappush(heap,(0,start))

    while heap:
        cost, node = heapq.heappop(heap)

        if distance[node] < cost:
            continue

        for next_node in graph[node]:
            next_cost = cost + next_node[1]

            if next_cost < distance[next_node[0]]:
                distance[next_node[0]] = next_cost
                heapq.heappush(heap,(next_cost,next_node[0]))

    result.append(distance)

if __name__ == "__main__":
    n, m, x = map(int,input().split())
    graph = [[] for _ in range(n+1)]
    result = []
    
    for i in range(m):
        a,b,c = map(int,input().split())
        graph[a].append((b,c))

    for i in range(1,n+1):
        solution(i)

    answer = 0
    for i in range(1,n+1):
        val = result[i-1][x] + result[x-1][i]
        answer = max(answer,val)
    
    print(answer)
    
    

    


