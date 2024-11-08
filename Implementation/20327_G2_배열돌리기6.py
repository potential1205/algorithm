import sys
input = sys.stdin.readline

def f1(board,m,iter):
    for sy in range(iter):
        for sx in range(iter):
            for i in range(m//2):
                for j in range(m):
                    board[(sy*m)+i][(sx*m)+j], board[(sy*m)+(m-i-1)][(sx*m)+j] = board[(sy*m)+(m-i-1)][(sx*m)+j], board[(sy*m)+i][(sx*m)+j]
    return board

def f2(board,m,iter):
    for sy in range(iter):
        for sx in range(iter):
            for i in range(m):
                for j in range(m//2):
                    board[(sy*m)+i][(sx*m)+j], board[(sy*m)+i][(sx*m)+(m-1-j)] = board[(sy*m)+i][(sx*m)+(m-1-j)], board[(sy*m)+i][(sx*m)+j]
    return board

def f3(board,m,iter):
    for sy in range(iter):
        for sx in range(iter):
            temp = []
            for i in range(m):
                temp.append([])
                for j in range(m):
                    temp[i].append(board[(sy*m)+i][(sx*m)+j])

            temp = list(map(list,zip(*temp[::-1])))
            
            for i in range(m):
                for j in range(m):
                    board[(sy*m)+i][(sx*m)+j] = temp[i][j]

    return board

def f4(board,m,iter):
    for sy in range(iter):
        for sx in range(iter):
            temp = []
            for i in range(m):
                temp.append([])
                for j in range(m):
                    temp[i].append(board[(sy*m)+i][(sx*m)+j])
                    
            temp = list(map(list,zip(*temp)))[::-1]
            
            for i in range(m):
                for j in range(m):
                    board[(sy*m)+i][(sx*m)+j] = temp[i][j]
    
    return board

def f5(board,m,iter):
    for sx in range(iter):
        for sy in range(iter//2):
            for i in range(m):
                for j in range(m):
                    board[sy*m+i][sx*m+j], board[(2**n)-(m*(sy+1))+i][sx*m+j] = board[(2**n)-(m*(sy+1))+i][sx*m+j], board[sy*m+i][sx*m+j]
    
    return board

def f6(board,m,iter):
    for sy in range(iter):
        for sx in range(iter//2):
            for i in range(m):
                for j in range(m):
                    board[sy*m+i][sx*m+j], board[sy*m+i][(2**n)-(m*(sx+1))+j] = board[sy*m+i][(2**n)-(m*(sx+1))+j], board[sy*m+i][sx*m+j]
    
    return board

def f7(board,m,iter):
    new_board = [line[:] for line in board]
    before_rotate = [[[sy*m,sx*m] for sx in range(iter)] for sy in range(iter)]
    after_rotate = list(map(list,zip(*before_rotate[::-1])))

    for sy in range(iter):
        for sx in range(iter):
            ky,kx = before_rotate[sy][sx]
            ny,nx = after_rotate[sy][sx]

            for i in range(m):
                for j in range(m):
                    new_board[ky+i][kx+j] = board[ny+i][nx+j]

    return new_board

def f8(board,m,iter):
    new_board = [line[:] for line in board]
    before_rotate = [[[sy*m,sx*m] for sx in range(iter)] for sy in range(iter)]
    after_rotate = list(map(list,zip(*before_rotate)))[::-1]

    for sy in range(iter):
        for sx in range(iter):
            ky,kx = before_rotate[sy][sx]
            ny,nx = after_rotate[sy][sx]

            for i in range(m):
                for j in range(m):
                    new_board[ky+i][kx+j] = board[ny+i][nx+j]

    return new_board

if __name__ == "__main__":
    n,r = map(int,input().split())
    board = [list(map(int,input().split())) for i in range(2**n)]

    operations = []
    for i in range(r):
        k,l  = map(int,input().split())
        operations.append([k,l])

    for op in operations:
        k,l = op

        m = 2**l
        iter = 2**n // m

        if k == 1:
            board = f1(board,m,iter)
        elif k == 2:
            board = f2(board,m,iter)
        elif k == 3:
            board = f3(board,m,iter)
        elif k == 4:
            board = f4(board,m,iter)
        elif k == 5:
            board = f5(board,m,iter)
        elif k == 6:
            board = f6(board,m,iter)
        elif k == 7:
            board = f7(board,m,iter)
        elif k == 8:
            board = f8(board,m,iter)
    
    for line in board:
        print(*line)
