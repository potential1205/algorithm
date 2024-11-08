
def rotate(board):
    return list(map(list,zip(*board[::-1])))

def flip(board):
    return list(map(list,zip(*board)))

def solution(board,std):
    for i in range(6):
        for j in range(6):
            for k in range(len(std)):
                cnt = 0
                flag = True

                for n in range(len(std[k])):
                    for m in range(len(std[k][0])):
                        if i+n >= 6 or j+m >= 6:
                            flag=False
                            break

                        if str(board[i+n][j+m]) == std[k][n][m]:
                            cnt+=1
                        else:
                            flag = False
                            break

                    if flag==False:
                        break

                if cnt == len(std[k]) * len(std[k][0]):
                    return "yes"

    return "no"
                            

if __name__ == "__main__":
    
    std = [
        ['1000','1111','1000'],
        ['1000','1111','0100'],
        ['1000','1111','0010'],
        ['1000','1111','0001'],
        ['0100','1111','0100'],
        ['0100','1111','0010'],
        ['1000','1110','0011'],
        ['0100','1110','0011'],
        ['0010','1110','0011'],
        ['1100','0110','0011'],
        ['11100','00111']
    ]

    for i in range(len(std)):
        shape = std[i]
        shape1 = rotate(shape)
        std.append(shape1)
        
        shape2 = rotate(shape1)
        std.append(shape2)

        shape3 = rotate(shape2)
        std.append(shape3)

        shape4 = flip(shape)
        std.append(shape4)

        shape5 = rotate(shape4)
        std.append(shape5)

        shape6 = rotate(shape5)
        std.append(shape6)

        shape7 = rotate(shape6)
        std.append(shape7)


    for i in range(3):
        board = []
        for j in range(6):
            board.append(list(map(int,input().split())))

        print(solution(board,std))
    