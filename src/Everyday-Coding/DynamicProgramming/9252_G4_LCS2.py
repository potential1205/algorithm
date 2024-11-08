
if __name__ == "__main__":
    str1 = input()
    str2 = input()
    n = len(str1)
    m = len(str2)
    board = [[0 for j in range(m+1)] for i in range(n+1)]

    for i in range(1,n+1):
        for j in range(1,m+1):
            if str1[i-1] == str2[j-1]:
                board[i][j] = board[i-1][j-1] + 1
            else:
                board[i][j] = max(board[i-1][j],board[i][j-1])

    result = ""
    sy,sx = n,m

    while True:
        if sy == 0 or sx == 0:
            break

        if board[sy][sx] == board[sy-1][sx]:
            sy -= 1
        elif board[sy][sx] == board[sy][sx-1]:
            sx -= 1
        else:
            sy-=1
            sx-=1
            result = str1[sy] + result
            
    print(board[-1][-1])
    if len(result)!=0:
        print(result)