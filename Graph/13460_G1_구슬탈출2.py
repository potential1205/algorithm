from collections import deque

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def solution(ry,rx,by,bx):
    queue = deque([[ry,rx,by,bx]])
    visit = [[ry,rx,by,bx]]
    cnt = 0

    while queue:
        for t in range(len(queue)):
            ry,rx,by,bx = queue.popleft()

            if cnt > 10:
                print(-1)
                return

            if board[ry][rx] == 'O':
                print(cnt)
                return
            
            for i in range(4):
                nry,nrx = ry,rx

                while True:
                    nry,nrx = nry+dy[i], nrx+dx[i]

                    if board[nry][nrx] == '#':
                        nry, nrx = nry-dy[i], nrx-dx[i]
                        break

                    if board[nry][nrx] == 'O':
                        break
                    
                
                nby,nbx = by,bx
                while True:
                    nby,nbx = nby+dy[i], nbx+dx[i]

                    if board[nby][nbx] == '#':
                        nby, nbx = nby-dy[i], nbx-dx[i]
                        break

                    if board[nby][nbx] == 'O':
                        break
                
                if board[nby][nbx] == 'O':
                    continue

                if nry == nby and nrx == nbx:
                    if abs(ry-nry) + abs(rx-nrx) > abs(by-nby) + abs(bx-nbx):
                        nry, nrx = nry-dy[i], nrx-dx[i]
                    else:
                        nby, nbx = nby-dy[i], nbx-dx[i]

                if [nry,nrx,nby,nbx] not in visit:
                    queue.append([nry,nrx,nby,nbx])
                    visit.append([nry,nrx,nby,nbx])
        cnt+=1

    print(-1)


if __name__ == "__main__":
    n,m = map(int,input().split())

    board = []
    ry,rx,by,bx,hy,hx = 0,0,0,0,0,0
    for i in range(n):
        line = list(input())
        for j in range(m):
            if line[j] == 'R':
                ry,rx = i,j
            if line[j] == 'B':
                by,bx = i,j
            if line[j] == 'O':
                hy,hx = i,j
        board.append(line)
    
    solution(ry,rx,by,bx)
