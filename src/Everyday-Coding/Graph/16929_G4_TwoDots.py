dx = [1,0,-1,0]
dy = [0,1,0,-1]

def check(sy,sx,ny,nx,color,cnt):
    
    for i in range(4):
        ky,kx = ny+dy[i],nx+dx[i]
        if ky<0 or ky>=n or kx<0 or kx>=m:
            continue
        
        if cnt>=4 and ky==sy and kx==sx:
            print("Yes")
            exit(0)
        
        if board[ky][kx] == color and visit[ky][kx] == False:
            visit[ky][kx] = True
            check(sy,sx,ky,kx,color,cnt+1)
            visit[ky][kx] = False


if __name__ == "__main__":
    n,m = map(int,input().split())
    board = [[line for line in input().rstrip()] for _ in range(n)]
    visit = [[False for j in range(m)] for i in range(n)]

    for i in range(n):
        for j in range(m):
            sy,sx = i,j
            ny,nx = i,j
            visit[i][j] = True
            check(sy,sx,ny,nx,board[i][j],1)

    print("No")
