from collections import deque
from itertools import combinations
import sys
INF = sys.maxsize
<<<<<<< HEAD
dy = [-1,1,0,0]
dx = [0,0,-1,1]

def bfs(board,candidate,blank_cnt):

    visit = [[False for j in range(n)] for i in range(n)]

    queue = deque([])
    for y,x in candidate:
        queue.append((y,x))

    time = 0
    while True:
        leng = len(queue)

        if blank_cnt == 0 or leng == 0:
            if blank_cnt == 0:
                return time
            else:
                return INF
            
        time+=1
        for i in range(leng):
            y,x = queue.popleft()

            if visit[y][x] == False:
                visit[y][x] = True

            for j in range(4):
                ky,kx = y+dy[j],x+dx[j]

                if ky<0 or kx<0 or ky>=n or kx>=n or visit[ky][kx]==True:
                    continue
                
                if board[ky][kx] == 0:
                    visit[ky][kx] = True
                    blank_cnt -= 1
                    queue.append((ky,kx))
                elif board[ky][kx] == 2:
                    visit[ky][kx] = True
                    queue.append((ky,kx))


    return 0

if __name__ == "__main__":
    n,m = map(int,input().split())
    board = [list(map(int,input().split())) for i in range(n)]
    virus, blank_cnt = [], 0

    for i in range(n):
        for j in range(n):
            if board[i][j] == 0:
                blank_cnt += 1
            elif board[i][j] == 2:
                virus.append((i,j))

    answer = INF
    for combination in list(combinations(virus,m)):
        val = bfs(board,combination,blank_cnt)
        answer = min(answer,val)
=======

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def bfs(board,candidate,zero_cnt):
    visit = [[False for j in range(n)] for i in range(n)]
    queue = deque([])
    for i in range(m):
        y,x = candidate[i]
        queue.append((y,x))
        visit[y][x] = True
    
    time = 0
    while queue:
        if zero_cnt == 0:
            return time

        time += 1

        for i in range(len(queue)):
            y,x = queue.popleft()

            for j in range(4):
                ky,kx = y+dy[j], x+dx[j]

                if ky<0 or kx<0 or ky>=n or kx>=n or visit[ky][kx] == True:
                    continue

                if board[ky][kx] == 0:
                    visit[ky][kx] = True
                    queue.append((ky,kx))
                    zero_cnt-=1
                
                elif board[ky][kx] == 2:
                    visit[ky][kx] = True
                    queue.append((ky,kx))
    return INF

if __name__ == "__main__":
    n,m = map(int,input().split())
    board, virus = [list(map(int,input().split())) for i in range(n)], []

    zero_cnt = 0
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0:
                zero_cnt+=1
            if board[i][j] == 2:
                virus.append([i,j])

    answer = INF
    for comb in list(combinations(virus,m)):
        res = bfs(board,comb,zero_cnt)
        answer = min(answer,res)
>>>>>>> dd09236e0324c1298dfd08d7fd6156b58cbed83c
    
    if answer == INF:
        print(-1)
    else:
        print(answer)
<<<<<<< HEAD
            
=======
>>>>>>> dd09236e0324c1298dfd08d7fd6156b58cbed83c



