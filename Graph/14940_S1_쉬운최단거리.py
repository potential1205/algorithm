from collections import deque
import sys
input = sys.stdin.readline

dy=[-1,1,0,0]
dx=[0,0,-1,1]

def solve(sy,sx):
    queue = deque([[sy,sx,0]])
    visit = [[False for j in range(m)] for i in range(n)]

    while queue:
        y,x,cnt = queue.popleft()
        info[y][x] = cnt

        for i in range(4):
            ky,kx = y+dy[i], x+dx[i]
            if ky<0 or kx<0 or kx>=m or ky>=n or visit[ky][kx]==True or board[ky][kx]==0:
                continue
            queue.append([ky,kx,cnt+1])
            visit[ky][kx] = True
    



if __name__ == "__main__":
    n,m = map(int,input().split())
    board=[]
    spoint = []
    info = [[0 for j in range(m)] for i in range(n)]
    for i in range(n):
        line = list(map(int,input().split()))
        board.append(line)
        for j in range(len(line)):
            if line[j]==2:
                spoint.append([i,j])
                info[i][j] = 0
            elif line[j] == 0:
                info[i][j] = -1
            else:
                info[i][j] = 0

    solve(spoint[0][0],spoint[0][1])

    for i in range(n):
        for j in range(m):
            if info[i][j] == -1:
                info[i][j] = 0
            elif info[i][j] == 0:
                info[i][j] = -1

    info[spoint[0][0]][spoint[0][1]] =0
    
    for i in range(n):
        for j in range(m):
            print(info[i][j],end=" ")
        print("")
