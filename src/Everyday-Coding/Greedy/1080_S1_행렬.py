def change(c):
    return '1' if c == '0' else '0'

if __name__ == "__main__":
    n,m = map(int,input().split())
    board1 = [list(input()) for _ in range(n)]
    board2 = [list(input()) for _ in range(n)]

    if n <= 2 or m <= 2:
        print(0) if board1 == board2 else print(-1)
    else:
        cnt = 0
        for i in range(n-2):
            for j in range(m-2):
                if board1[i][j] != board2[i][j]:
                    cnt+=1
                    for k in range(9):
                        board1[i+k//3][j+k%3] = change(board1[i+k//3][j+k%3])
                    
                if board1==board2:
                    print(cnt)
                    exit()
            
        print(-1)
