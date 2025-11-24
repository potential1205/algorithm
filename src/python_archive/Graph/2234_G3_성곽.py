
from collections import deque

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def binary(val):
    return bin(val)[2:].zfill(4)

def bfs(sy,sx,number):
    queue = deque([[sy,sx]])
    visit[sy][sx] = number
    size, points = 1, []

    while queue:
        y,x = queue.popleft()
        points.append([y,x])

        for i in range(4):
            ky,kx = y+dy[i], x+dx[i]
            if ky<0 or kx<0 or ky>=n or kx>=m or visit[ky][kx] != 0:
                continue
            
            if i == 0:
                if binary(board[y][x])[2] == '0' and binary(board[ky][kx])[0] == '0':
                    queue.append([ky,kx])
                    visit[ky][kx] = number
                    size+=1

            elif i == 1:
                if binary(board[y][x])[0] == '0' and binary(board[ky][kx])[2] == '0':
                    queue.append([ky,kx])
                    visit[ky][kx] = number
                    size+=1

            elif i == 2:
                if binary(board[y][x])[3] == '0' and binary(board[ky][kx])[1] == '0':
                    queue.append([ky,kx])
                    visit[ky][kx] = number
                    size+=1
            
            elif i == 3: 
                if binary(board[y][x])[1] == '0' and binary(board[ky][kx])[3] == '0':
                    queue.append([ky,kx])
                    visit[ky][kx] = number
                    size+=1

    for y,x in points:
        room[y][x] = size

    return size


if __name__ == "__main__":

    m,n = map(int,input().split())
    board = [list(map(int,input().split())) for i in range(n)]
    visit = [[0 for j in range(m)] for i in range(n)]
    room = [[0 for j in range(m)] for i in range(n)]

    area_num, max_size, break_max_size = 1, 0, 0

    for i in range(n):
        for j in range(m):
            if visit[i][j] == False:
                size = bfs(i,j,area_num)
                if max_size < size:
                    max_size = size
                area_num +=1

    for i in range(n):
        for j in range(m):
            for k in range(4):
                ky,kx = i+dy[k], j+dx[k]
                if 0<=ky<n and 0<=kx<m and visit[i][j] != visit[ky][kx]:
                        break_max_size = max(break_max_size, room[i][j] + room[ky][kx])
    
    print(area_num-1)
    print(max_size)
    print(break_max_size)
