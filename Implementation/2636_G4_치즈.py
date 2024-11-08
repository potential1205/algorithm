from collections import deque

dx = [-1,1,0,0]
dy = [0,0,-1,1]

def solution():
    queue = deque([[0,0]])
    visit = [[False for j in range(m)] for i in range(n)]
    positions = []

    while queue:
        y,x = queue.popleft()

        for i in range(4):
            ky,kx = y+dy[i], x+dx[i]

            if ky<0 or kx<0 or ky>=n or kx>=m or visit[ky][kx] == True:
                continue

            if board[ky][kx] == '0':
                queue.append([ky,kx])
                visit[ky][kx] = True
 
            elif board[ky][kx] == '1':
                visit[ky][kx] = True
                positions.append([ky,kx])
    

    for i in range(len(positions)):
        board[positions[i][0]][positions[i][1]] = '0'

    return len(positions)
        

if __name__ == "__main__":
    n,m = map(int,input().split())
    before_cheese = 0
    board = []
    for i in range(n):
        line = list(input().split(' '))
        for j in range(len(line)):
            if line[j] == '1':
                before_cheese += 1
        board.append(line)

    after_cheese = 0
    cnt,time = 0, 0

    while True:
        after_cheese = cnt
        cnt = solution()
        if cnt == 0:
            break
        time += 1

    print(time)
    print(after_cheese)