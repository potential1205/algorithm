
from itertools import product
from collections import deque
from itertools import permutations;

def rotate_90(board):
    board = list(zip(*board))
    rotated_board = [list(row)[::-1] for row in board]
    return rotated_board

def rotate_num(board,num):
    for i in range(num):
        board = rotate_90(board)
    return board  


dx = [-1,1,0,0,0,0]
dy = [0,0,-1,1,0,0]
dz = [0,0,0,0,-1,1]

def bfs(cube):
    queue = deque([[0,0,0,0]])
    visit = [[[False for k in range(5)] for j in range(5)] for i in range(5)]

    while queue:
        z,y,x,cnt = queue.popleft()

        if z==4 and y==4 and x==4:
            return cnt

        for i in range(6):
            kz,ky,kx = z+dz[i],y+dy[i],x+dx[i]

            if kz<0 or ky<0 or kx<0 or kz>=5 or ky>=5 or kx>=5 or visit[kz][ky][kx] == True or cube[kz][ky][kx] == 0:
                continue

            queue.append([kz,ky,kx,cnt+1])
            visit[kz][ky][kx] = True

    return 9999 


if __name__ == "__main__":
    cube = [[list(map(int, input().split())) for _ in range(5)] for _ in range(5)]

    rotate_face = []
    for i in range(5):
        rotate_face_line = []
        for j in range(4):
            rotate_face_line.append(rotate_num(cube[i],j))
        rotate_face.append(rotate_face_line)


    #rotate_face[i][j] : i번째 면을 j번 회전시킨 2차원 배열

    rotate_combinations = list(product(range(4), repeat=5))
    layer_combinations = list(permutations(range(5)))

    answer = 9999
    for idx, layer_combination in enumerate(layer_combinations):
        for jdx, rotate_combination  in enumerate(rotate_combinations):
            temp_cube = []
            for k in range(5):
                temp_cube.append(rotate_face[layer_combination[k]][rotate_combination[k]])
            
            if temp_cube[0][0][0] == 0 or temp_cube[4][4][4] == 0:
                continue

            val = bfs(temp_cube)
            if val == 12:
                print(12)
                exit()
                
            answer = min(answer, val)

    if answer == 9999:
        print(-1)
    else:
        print(answer)

