
from collections import deque

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def solution():
    queue = deque([[0,0]])
    visit = [[-1 for j in range(m)] for i in range(n)]
    visit[0][0] = 0

    while queue:
        y,x = queue.popleft()

        for i in range(4):
            ky, kx = y+dy[i], x+dx[i]

            if ky<0 or kx<0 or ky>=n or kx>=m:
                continue
            
            if visit[ky][kx] == -1:
                if board[ky][kx] == 1:
                    queue.append([ky,kx])
                    visit[ky][kx] = visit[y][x] + 1
                else:
                    queue.appendleft([ky,kx])
                    visit[ky][kx] = visit[y][x]
    
    print(visit[n-1][m-1])

if __name__ == "__main__":
    m,n = map(int,input().split())
    board = [list(map(int, input())) for _ in range(n)]
    solution()

