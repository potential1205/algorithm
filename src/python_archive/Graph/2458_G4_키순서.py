
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n,m = map(int,input().split())
    graph = [[0 for _ in range(n+1)] for _ in range(n+1)]

    for i in range(m):
        a,b = map(int,input().split())
        graph[a][b] = 1
  
    for k in range(1,n+1):
        for i in range(1,n+1):
            for j in range(1,n+1):
                if graph[i][j] == 1 or (graph[i][k] ==1 and graph[k][j] == 1):
                    graph[i][j] = 1

    answer = 0

    for i in range(1,n+1):
        cnt = 0
        for j in range(1,n+1):
            cnt = cnt + graph[i][j] + graph[j][i]
        if cnt == n-1:
            answer += 1
    print(answer)

