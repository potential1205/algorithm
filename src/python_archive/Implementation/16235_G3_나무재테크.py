import sys
from collections import deque
input = sys.stdin.readline

dy = [-1,-1,-1,0,0,1,1,1]
dx = [-1,0,1,-1,1,-1,0,1]

def spring_summer():
    for i in range(n):
        for j in range(n):
            temp = deque([])
            rest_food = 0

            for k in range(len(board[i][j])):
                age = board[i][j][k]

                if food[i][j] >= age:
                    food[i][j] -= age
                    temp.append(age+1)
                else:
                    rest_food += (age//2)

            board[i][j] = temp
            food[i][j] += rest_food

    return board


def fall_winter():
    global answer
    for i in range(n):
        for j in range(n):
            for k in range(len(board[i][j])):
                age = board[i][j][k]
                if age%5 == 0:
                    for l in range(8):
                        ky,kx = i+dy[l], j+dx[l]
                        if ky<0 or kx<0 or ky>=n or kx>=n:
                            continue
                        board[ky][kx].appendleft(1)

            food[i][j] += robot[i][j]

    return board

if __name__ == "__main__":
    n,m,k = map(int,input().split())
    robot = [list(map(int,input().split())) for i in range(n)]
    food = [[5 for j in range(n)] for i in range(n)]
    board = [[deque() for j in range(n)] for i in range(n)]
    
    board_leng = [[0 for j in range(n)] for i in range(n)]

    for _ in range(m):
        y,x,age = list(map(int,input().split()))
        board[y-1][x-1].append(age)

    for _ in range(k):
        spring_summer()
        fall_winter()

    answer = 0
    for i in range(n):
        for j in range(n):
            answer += len(board[i][j])

    print(answer)