
if __name__ == "__main__":
    str1 = input()
    str2 = input()
    str3 = input()

    n,m,l = len(str1),len(str2),len(str3)

    board = [[[0 for k in range(l+1)] for j in range(m+1)] for i in range(n+1)]

    for i in range(1,n+1):
        for j in range(1,m+1):
            for k in range(1,l+1):
                if str1[i-1]==str2[j-1]==str3[k-1]:
                    board[i][j][k] = board[i-1][j-1][k-1] + 1
                else:
                    board[i][j][k] = max(board[i-1][j][k],board[i][j-1][k],board[i][j][k-1])
    
    print(board[n][m][k])
