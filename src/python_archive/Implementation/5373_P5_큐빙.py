
if __name__ == "__main__":
    t = int(input())

    for _ in range(t):
        n = int(input())
        opes = list(input().split())
        
        F = [['r','r','r'],['r','r','r'],['r','r','r']]
        B = [['o','o','o'],['o','o','o'],['o','o','o']]
        L = [['g','g','g'],['g','g','g'],['g','g','g']]
        R = [['b','b','b'],['b','b','b'],['b','b','b']]
        U = [['w','w','w'],['w','w','w'],['w','w','w']]
        D = [['y','y','y'],['y','y','y'],['y','y','y']]

        for ope in opes:
            a,b = ope[0],ope[1]

            if a == 'U':
                if b == '+':
                    U = list(map(list,zip(*U[::-1])))
                    temp1,temp2,temp3 = F[0][0],F[0][1],F[0][2]
                    F[0][0],F[0][1],F[0][2] = R[2][0],R[1][0],R[0][0]
                    R[2][0],R[1][0],R[0][0] = B[2][2],B[2][1],B[2][0]
                    B[2][2],B[2][1],B[2][0] = L[0][2],L[1][2],L[2][2]
                    L[0][2],L[1][2],L[2][2] = temp1,temp2,temp3

                elif b == '-':
                    U = list(map(list,zip(*U)))[::-1]
                    for i in range(3):
                        temp1,temp2,temp3 = F[0][0],F[0][1],F[0][2]
                        F[0][0],F[0][1],F[0][2] = R[2][0],R[1][0],R[0][0]
                        R[2][0],R[1][0],R[0][0] = B[2][2],B[2][1],B[2][0]
                        B[2][2],B[2][1],B[2][0] = L[0][2],L[1][2],L[2][2]
                        L[0][2],L[1][2],L[2][2] = temp1,temp2,temp3

            if a == 'D':
                if b == '+':
                    D = list(map(list,zip(*D[::-1])))
                    temp1,temp2,temp3 = B[0][0],B[0][1],B[0][2]
                    B[0][0],B[0][1],B[0][2] = R[0][2],R[1][2],R[2][2]
                    R[0][2],R[1][2],R[2][2] = F[2][2],F[2][1],F[2][0]
                    F[2][2],F[2][1],F[2][0] = L[2][0],L[1][0],L[0][0]
                    L[2][0],L[1][0],L[0][0] = temp1,temp2,temp3

                elif b == '-':
                    D = list(map(list,zip(*D)))[::-1]

                    for i in range(3):
                        temp1,temp2,temp3 = B[0][0],B[0][1],B[0][2]
                        B[0][0],B[0][1],B[0][2] = R[0][2],R[1][2],R[2][2]
                        R[0][2],R[1][2],R[2][2] = F[2][2],F[2][1],F[2][0]
                        F[2][2],F[2][1],F[2][0] = L[2][0],L[1][0],L[0][0]
                        L[2][0],L[1][0],L[0][0] = temp1,temp2,temp3

            if a == 'F':
                if b == '+':
                    F = list(map(list,zip(*F[::-1])))
                    temp1,temp2,temp3 = D[0][0],D[0][1],D[0][2]
                    D[0][0],D[0][1],D[0][2] = R[2][2],R[2][1],R[2][0]
                    R[2][2],R[2][1],R[2][0] = U[2][2],U[2][1],U[2][0]
                    U[2][2],U[2][1],U[2][0] = L[2][2],L[2][1],L[2][0]
                    L[2][2],L[2][1],L[2][0] = temp1,temp2,temp3

                elif b == '-':
                    F = list(map(list,zip(*F)))[::-1]
                    for i in range(3):
                        temp1,temp2,temp3 = D[0][0],D[0][1],D[0][2]
                        D[0][0],D[0][1],D[0][2] = R[2][2],R[2][1],R[2][0]
                        R[2][2],R[2][1],R[2][0] = U[2][2],U[2][1],U[2][0]
                        U[2][2],U[2][1],U[2][0] = L[2][2],L[2][1],L[2][0]
                        L[2][2],L[2][1],L[2][0] = temp1,temp2,temp3

            if a == 'B':
                if b == '+':
                    B = list(map(list,zip(*B[::-1])))
                    temp1,temp2,temp3 = U[0][0],U[0][1],U[0][2]
                    U[0][0],U[0][1],U[0][2] = R[0][0],R[0][1],R[0][2]
                    R[0][0],R[0][1],R[0][2] = D[2][2],D[2][1],D[2][0]
                    D[2][2],D[2][1],D[2][0] = L[0][0],L[0][1],L[0][2]
                    L[0][0],L[0][1],L[0][2] = temp1,temp2,temp3


                elif b == '-':
                    B = list(map(list,zip(*B)))[::-1]
                    for i in range(3):
                        temp1,temp2,temp3 = U[0][0],U[0][1],U[0][2]
                        U[0][0],U[0][1],U[0][2] = R[0][0],R[0][1],R[0][2]
                        R[0][0],R[0][1],R[0][2] = D[2][2],D[2][1],D[2][0]
                        D[2][2],D[2][1],D[2][0] = L[0][0],L[0][1],L[0][2]
                        L[0][0],L[0][1],L[0][2] = temp1,temp2,temp3

            if a == 'L':
                if b == '+':
                    L = list(map(list,zip(*L[::-1])))
                    temp1,temp2,temp3 = F[2][0],F[1][0],F[0][0]
                    F[2][0],F[1][0],F[0][0] = U[2][0],U[1][0],U[0][0]
                    U[2][0],U[1][0],U[0][0] = B[2][0],B[1][0],B[0][0]
                    B[2][0],B[1][0],B[0][0] = D[2][0],D[1][0],D[0][0]
                    D[2][0],D[1][0],D[0][0] = temp1,temp2,temp3

                elif b == '-':
                    L = list(map(list,zip(*L)))[::-1]
                    for i in range(3):
                        temp1,temp2,temp3 = F[2][0],F[1][0],F[0][0]
                        F[2][0],F[1][0],F[0][0] = U[2][0],U[1][0],U[0][0]
                        U[2][0],U[1][0],U[0][0] = B[2][0],B[1][0],B[0][0]
                        B[2][0],B[1][0],B[0][0] = D[2][0],D[1][0],D[0][0]
                        D[2][0],D[1][0],D[0][0] = temp1,temp2,temp3

            if a == 'R':
                if b == '+':
                    R = list(map(list,zip(*R[::-1])))
                    temp1,temp2,temp3 = F[0][2],F[1][2],F[2][2]
                    F[0][2],F[1][2],F[2][2] = D[0][2],D[1][2],D[2][2]
                    D[0][2],D[1][2],D[2][2] = F[0][2],F[1][2],F[2][2]
                    F[0][2],F[1][2],F[2][2] = U[0][2],U[1][2],U[2][2]
                    U[0][2],U[1][2],U[2][2] = temp1,temp2,temp3
                
                elif b == '-':
                    R = list(map(list,zip(*R)))[::-1]
                    for i in range(3):
                        temp1,temp2,temp3 = F[0][2],F[1][2],F[2][2]
                        F[0][2],F[1][2],F[2][2] = D[0][2],D[1][2],D[2][2]
                        D[0][2],D[1][2],D[2][2] = F[0][2],F[1][2],F[2][2]
                        F[0][2],F[1][2],F[2][2] = U[0][2],U[1][2],U[2][2]
                        U[0][2],U[1][2],U[2][2] = temp1,temp2,temp3

            
            # print("_----")
            # print("Up")
            # for i in range(3):
            #     print(*U[i])

            # print("Down")
            # for i in range(3):
            #     print(*D[i])
            
            # print("Back")
            # for i in range(3):
            #     print(*B[i])
            
            # print("Front")
            # for i in range(3):
            #     print(*F[i])
            
            # print("Left")
            # for i in range(3):
            #     print(*L[i])
            
            # print("Right")
            # for i in range(3):
            #     print(*R[i])

        for i in range(3):
            for j in range(3):
                print(U[i][j],end='')
            print()
