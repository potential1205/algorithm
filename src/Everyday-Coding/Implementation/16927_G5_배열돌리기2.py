import sys
input = sys.stdin.readline

def rotate(board,n,m,r):
    t = min(n,m)//2

    for i in range(t):
        R = r % (((n-2*i)*2) + ((m-2*i)*2) -4)

        for k in range(R):
            sy,sx = i,i
            
            xt = m - (2*i) - 1
            yt = n - (2*i) - 1

            temp = board[sy][sx]

            for j in range(xt):
                board[sy][sx+j] = board[sy][sx+j+1]

            for j in range(yt):
                board[sy+j][sx+xt] = board[sy+j+1][sx+xt]

            for j in range(xt):
                board[sy+yt][sx+xt-j] = board[sy+yt][sx+xt-j-1]

            for j in range(yt):
                board[sy+yt-j][sx] = board[sy+yt-j-1][sx]

            board[sy+1][sx] = temp

    return board


if __name__ == "__main__":
    n,m,r = map(int,input().split())

    board = []
    for i in range(n):
        board.append(list(map(int,input().split())))

    board = rotate(board,n,m,r)
    
    for i in range(n):
        print(*board[i])