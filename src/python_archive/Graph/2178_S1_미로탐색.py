from collections import deque

def solution():
    queue = deque([[0,0,1]])
    visit = [[False for j in range(m)] for i in range(n)]
    while queue:
        y,x,cnt = queue.popleft()
        if y==n-1 and x == m-1:
            print(cnt)
            return
        
        for dy,dx in [[-1,0],[1,0],[0,-1],[0,1]]:
            ky,kx = y+dy, x+dx
            if ky<0 or ky>=n or kx<0 or kx>=m or visit[ky][kx] == True or board[ky][kx]=='0':
                continue
            
            queue.append([ky,kx,cnt+1])
            visit[ky][kx] = True
            
    return


if __name__ == "__main__":
    n,m = map(int,input().split())
    board = []
    for i in range(n):
        board.append(list(input()))
    
    solution()