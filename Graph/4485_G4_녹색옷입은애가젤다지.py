import heapq
import sys

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def bfs(t):
    distance = [[sys.maxsize for j in range(n)] for i in range(n)]
    distance[0][0] = board[0][0]

    heap = []
    heapq.heappush(heap,(0,0,0))

    while heap:
        w,y,x = heapq.heappop(heap)

        if w > distance[y][x]:
            continue

        for i in range(4):
            ky,kx = y+dy[i], x+dx[i]
            if ky<0 or kx<0 or kx>=n or ky>=n:
                continue
            
            next_w = w + board[ky][kx]
            if next_w < distance[ky][kx]:
                distance[ky][kx] = next_w
                heapq.heappush(heap,(next_w,ky,kx))

    print("Problem {}: {}".format(t, distance[n-1][n-1]+board[0][0]))

if __name__ == "__main__":
    t = 1
    while True:
        n = int(input())
        if n == 0: break
        board = [list(map(int,input().split())) for i in range(n)]
        
        bfs(t)
        t += 1
