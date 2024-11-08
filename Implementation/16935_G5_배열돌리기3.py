
def f1(boardn,n,m):
    for i in range(m):
        for j in range(n//2):
            board[j][i], board[n-j-1][i] = board[n-j-1][i], board[j][i]
    return board

def f2(board,n,m):
    for i in range(n):
        for j in range(m//2):
            board[i][j], board[i][m-j-1] = board[i][m-j-1], board[i][j]
    return board

def f3(board,n,m):
    n,m = m,n
    return list(map(list,zip(*board[::-1]))),n,m

def f4(board,n,m):
    n,m = m,n
    return list(map(list,zip(*board)))[::-1],n,m

def f5(board,n,m):
    for i in range(n//2):
        for j in range(m//2):
            temp = board[i][j]
            board[i][j] = board[i+n//2][j]
            board[i+n//2][j] = board[i+n//2][j+m//2]
            board[i+n//2][j+m//2] = board[i][j+m//2]
            board[i][j+m//2] = temp
    return board

def f6(board,n,m):
    for i in range(n//2):
        for j in range(m//2):
            temp = board[i][j]
            board[i][j] = board[i][j+m//2]
            board[i][j+m//2] = board[i+n//2][j+m//2]
            board[i+n//2][j+m//2] = board[i+n//2][j]
            board[i+n//2][j] = temp
    return board

if __name__ == "__main__":
    n,m,r = map(int,input().split())
    board = []
    for i in range(n):
        board.append(list(map(int,input().split())))
    
    ops = list(map(int,input().split()))
    
    for op in ops:
        if op == 1:
            board = f1(board,n,m)
        elif op == 2:
            board = f2(board,n,m)
        elif op == 3:
            board,n,m = f3(board,n,m)
        elif op == 4:
            board,n,m = f4(board,n,m)
        elif op == 5:
            board = f5(board,n,m)
        elif op == 6:
            board = f6(board,n,m)

    for i in range(n):
        print(*board[i])