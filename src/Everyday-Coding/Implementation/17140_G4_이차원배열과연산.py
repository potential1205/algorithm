import heapq
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    r,c,k = map(int,input().split())

    board,n,m = [],3,3
    for i in range(100):
        board.append([0]*100)

    for i in range(3):
        val1,val2,val3 = map(int,input().split())
        board[i][0] = val1
        board[i][1] = val2
        board[i][2] = val3

    for t in range(101):
        if board[r-1][c-1] == k:
            print(t)
            exit()
        
        result = []

        if n>=m:
            for i in range(n):
                cnt = 0
                for val in set(board[i]):
                    if val!=0:
                        heapq.heappush(result,[board[i].count(val),val])
                        cnt+=1

                m = max(m,2*cnt)

                for j in range(0,100,2):
                    if result:
                        val1,val2 = heapq.heappop(result)
                        board[i][j], board[i][j+1] = val2,val1
                    else:
                        board[i][j], board[i][j+1] = 0,0

        else:
            temp = list(zip(*board))

            for i in range(m):
                cnt = 0
                for val in set(temp[i]):
                    if val!=0:
                        heapq.heappush(result,[temp[i].count(val),val])
                        cnt+=1
                
                n = max(n,2*cnt)

                for j in range(0,100,2):
                    if result:
                        val1,val2 = heapq.heappop(result)
                        board[j][i], board[j+1][i] = val2,val1
                    else:
                        board[j][i], board[j+1][i] = 0,0

    print(-1)