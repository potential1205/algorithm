



if __name__ == "__main__":
    str1 = input()
    str2 = input()

    n,m = len(str1),len(str2)

    board = [[0 for j in range(m+1)] for i in range(n+1)]
    answer = 0
    idx,jdx = 0,0
    for i in range(1,n+1):
        for j in range(1,m+1):
            if str1[i-1] == str2[j-1]:
                board[i][j] = board[i-1][j-1] + 1
                if answer < board[i][j]:
                    answer = board[i][j]
                    idx,jdx = i,j

    print(board[idx][jdx])

    # result = ""
    # while True:
    #     if board[idx][jdx] > 0:
    #         result = str1[idx-1] + result
    #     elif board[idx][jdx] == 0:
    #         break

    #     idx-=1
    #     jdx-=1
 
    # print(result)
