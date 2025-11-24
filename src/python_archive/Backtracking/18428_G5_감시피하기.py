def check():
    dy = [-1,1,0,0]
    dx = [0,0,-1,1]

    for ty,tx in teachers:
        for i in range(4):
            ky,kx = ty,tx
            while True:
                ky,kx = ky+dy[i], kx+dx[i]

                if kx<0 or kx>=n or ky<0 or ky>=n or board[ky][kx] == 'O':
                    break

                if board[ky][kx] == 'S':
                    return False
                
    return True

def solution(cnt):
    if cnt == 3:
        if check():
            print("YES")
            exit()
    
    else:
        for i,j in emptys:
            if board[i][j] == 'X':
                board[i][j] = 'O'
                solution(cnt+1)
                board[i][j] = 'X'


if __name__ == "__main__":
    n = int(input())
    board, teachers, emptys = [], [], []

    for idx in range(n):
        line = input().split(' ')
        for jdx, letter in enumerate(line):
            if letter == 'T':
                teachers.append([idx,jdx])
            elif letter == 'X':
                emptys.append([idx,jdx])
        board.append(line)

    solution(0)
    print("NO")