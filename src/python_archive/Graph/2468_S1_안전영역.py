from collections import deque

dx = [-1,1,0,0]
dy = [0,0,-1,1]

def bfs(visit,y,x,level):
    queue = deque([[y,x]])
    
    while queue:
        y,x = queue.popleft()
        visit[y][x] = True
        for i in range(4):
            ky,kx = y+dy[i], x+dx[i]
            if ky<0 or kx<0 or ky>=n or kx>=n or visit[ky][kx] == True:
                continue
            
            if board[ky][kx] > level:
                queue.append([ky,kx])
                visit[ky][kx] = True

    return visit


def check(board,level):
    visit,cnt = [[False for j in range(n)] for i in range(n)], 0
    for i in range(n):
        for j in range(n):
            if visit[i][j] == False and board[i][j] > level:
                visit = bfs(visit,i,j,level)
                cnt+=1
                
    return cnt

if __name__ == "__main__":
    n = int(input())
    board, max_level,max_safe_level = [],0,0
    for i in range(n):
        line = list(map(int,input().split()))
        board.append(line)
        if max_level < max(line):
            max_level = max(line)

    for i in range(max_level+1):
        cnt = check(board,i)
        if max_safe_level < cnt:
            max_safe_level = cnt
    print(max_safe_level)