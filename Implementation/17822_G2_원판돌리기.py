import sys
input = sys.stdin.readline

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def rotate(board,i,d):
    line = board[i][:]
    if d==0: # 시계 방향으로 회전
        for l in range(m):
            board[i][(l+k)%m] = line[l]
    elif d==1: # 반시계 방향으로 회전
        for l in range(m):
            board[i][(l-k)%m] = line[l]

    return board

def bfs(board):
    copy_board = [row[:] for row in board]
    flag = False

    for i in range(n):
        for j in range(m):
            for d in range(4):
                ky,kx = i+dy[d], (j+dx[d])%m

                if ky<0 or ky>=n:
                    continue

                if copy_board[i][j]!=0 and copy_board[i][j] == copy_board[ky][kx]:
                    board[i][j] = 0
                    board[ky][kx] = 0
                    flag=True

    return board,flag


if __name__ == "__main__":
    n,m,t = map(int,input().split())
    board = [list(map(int,input().split())) for _ in range(n)]

    for _ in range(t):
        x,d,k = map(int,input().split())
    
        for i in range(n):
            if (i+1)%x == 0: # i번째 원판이 x의 배수일 때
                board = rotate(board,i,d)

        board,flag = bfs(board)

        if not flag: # 인접한 수가 같은 경우가 없었다면
            cum,cnt = 0,0 
            for i in range(n):
                for j in range(m):
                    if board[i][j] != 0:
                        cnt+=1
                        cum += board[i][j]

            if cnt !=0:
                avg = cum/cnt

                for i in range(n):
                    for j in range(m):
                        if board[i][j] != 0:
                            if board[i][j] > avg:
                                board[i][j]-=1
                            elif board[i][j] < avg:
                                board[i][j]+=1
            else:
                print(0)
                exit()

    answer = 0
    for i in range(n):
        for j in range(m):
            answer += board[i][j]
    print(answer)

    
