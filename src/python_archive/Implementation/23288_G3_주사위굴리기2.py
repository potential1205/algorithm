from collections import deque
dy = [0,1,0,-1]
dx = [1,0,-1,0]

def get_direction(dice):
    a = dice["state"][5]
    b = board[dice["y"]][dice["x"]]

    if a>b:
        dice["d"] = (dice["d"] + 1)%4
    elif b>a:
        dice["d"] = (dice["d"] - 1)%4

    return dice

def rotate_d(d):
    if d == 0:
        d = 2
    elif d == 1:
        d = 3
    elif d == 2:
        d = 0
    elif d == 3:
        d = 1
    return d

def bfs(dice):
    queue = deque([[dice["y"],dice["x"]]])
    val = board[dice["y"]][dice["x"]]
    visit = [[False for j in range(m)] for i in range(n)]
    visit[dice["y"]][dice["x"]] = True
    cnt = 0
    while queue:
        y,x = queue.popleft()

        if board[y][x] == val:
            cnt+=1

        for i in range(4):
            ky,kx = y+dy[i],x+dx[i]
            if ky<0 or kx<0 or ky>=n or kx>=m or visit[ky][kx] == True or board[ky][kx]!=val:
                continue

            queue.append([ky,kx])
            visit[ky][kx] = True

    return cnt

def get_score(dice):
    c = bfs(dice)
    return c * board[dice["y"]][dice["x"]]

def roll(dice,d):
    lst = dice["state"]
    if d == 0:
        lst[0],lst[1],lst[2],lst[3],lst[4],lst[5] = lst[3],lst[1],lst[0],lst[5],lst[4],lst[2]
    elif d == 1:
        lst[0],lst[1],lst[2],lst[3],lst[4],lst[5] = lst[1],lst[5],lst[2],lst[3],lst[0],lst[4]
    elif d == 2:
        lst[0],lst[1],lst[2],lst[3],lst[4],lst[5] = lst[2],lst[1],lst[5],lst[0],lst[4],lst[3]
    elif d == 3:
        lst[0],lst[1],lst[2],lst[3],lst[4],lst[5] = lst[4],lst[0],lst[2],lst[3],lst[5],lst[1]
    return dice

def move(dice):
    ky,kx = dice["y"]+dy[dice["d"]], dice["x"]+dx[dice["d"]]
    if ky<0 or ky>=n or kx<0 or kx>=m:
        dice["d"] = rotate_d(dice["d"])
        ky,kx = dice["y"]+dy[dice["d"]], dice["x"]+dx[dice["d"]]

    dice = roll(dice,dice["d"])
    dice["y"] = ky
    dice["x"] = kx

    return dice

if __name__ == "__main__":
    n,m,k = map(int,input().split())
    board = []
    for i in range(n):
        line = list(map(int,input().split()))
        board.append(line)
    
    dice = dict()
    dice["state"] = [1,2,3,4,5,6] # 천장, 상, 우, 좌, 하, 바닥
    dice["d"], dice["y"], dice["x"] = 0, 0, 0

    answer = 0
    for turn in range(k):
        dice = move(dice)
        score = get_score(dice)
        dice = get_direction(dice)
        answer += score
    
    print(answer)