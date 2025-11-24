import sys
input = sys.stdin.readline
from collections import deque
from itertools import permutations

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def bfs(sy,sx,ey,ex):
    queue = deque([[sy,sx,0]])
    visit = [[False for j in range(w)] for i in range(h)]
    cnt=0
    
    while queue:
        y,x,cnt = queue.popleft()

        for i in range(4):
            ky, kx = y+dy[i], x+dx[i]

            if ky<0 or kx<0 or ky>=h or kx>=w or visit[ky][kx] == True or board[ky][kx] == 'x':
                continue
            
            if ky == ey and kx == ex:
                return cnt+1

            queue.append([ky,kx,cnt+1])
            visit[ky][kx] = True

    return -1
    
if __name__ == "__main__":
    while True:
        w,h = map(int,input().split())

        if w+h == 0:
            break
        
        board,robot,dusts = [],[],[]
        for i in range(h):
            line = list(input())
            board.append(line)
            for j in range(w):
                if line[j] == '*':
                    dusts.append((i,j))
                elif line[j] == 'o':
                    robot = (i,j)

        len_dusts = len(dusts)
        candidates = list(permutations(range(len_dusts)))
        
        first_dists = []
        for dust in dusts:
            cnt = bfs(robot[0],robot[1],dust[0],dust[1])
            first_dists.append(cnt)
        
        if -1 in first_dists:
            print(-1)
            continue

        dists = [[0 for _ in range(len_dusts)] for _ in range(len_dusts)]
        for i, dust1 in enumerate(dusts):
            for j, dust2 in enumerate(dusts):
                if i!=j:
                    cnt = bfs(dust1[0],dust1[1],dust2[0],dust2[1])
                    dists[i][j] = cnt
        
        min_val = 99999

        for i in range(len(candidates)):
            temp = 0
            for j in range(len_dusts):
                if j==0:
                    temp += first_dists[candidates[i][0]]
                    start = candidates[i][0]
                else:
                    temp += dists[start][candidates[i][j]]
                    start = candidates[i][j]
                    
            if temp < min_val:
                min_val = temp

        print(min_val)