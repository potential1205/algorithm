from collections import deque

def solution():
    queue = deque()
    for i in range(4):
        queue.append([sy,sx,i,0])
    
    while queue:
        y, x, direct, cnt = queue.popleft()

        for i in range(4):
            ky, kx = y+dy[i], x+dx[i]

            if 0<=ky<h and 0<=kx<w and board[ky][kx] != "*":
                new_cnt = cnt + 1 if direct != i else cnt

                if visit[i][ky][kx] > new_cnt:
                    visit[i][ky][kx] = new_cnt
                    queue.append([ky,kx,i,new_cnt])


    return min(visit[0][ey][ex],visit[1][ey][ex],visit[2][ey][ex],visit[3][ey][ex])


if __name__ == "__main__":
    w,h = map(int,input().split())
    board = [input() for _ in range(h)]

    dx = (-1,1,0,0)
    dy = (0,0,-1,1)

    C = []
    for i in range(h):
        for j in range(w):
            if board[i][j]=='C':
                C.append([i,j])

    (sy,sx),(ey,ex) = C

    visit = [[[float("inf") for k in range(w)] for j in range(h)] for i in range(4)]
    print(solution())