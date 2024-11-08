
if __name__ == "__main__":
    n = int(input())
    lstp,lstm,lstz = [],[],[]
    for i in range(n):
        num = int(input())
        if num > 0:
            lstp.append(num)
        elif num<0:
            lstm.append(num)
        else:
            lstz.append(num)

    lstp.sort(reverse=True)
    lstm.sort()

    answer = 0

    for i in range(len(lstp)//2):
        num1, num2 = lstp[0],lstp[1]
        if num1 == 1 or num2 == 1:
            break
        answer += (lstp.pop(0)*lstp.pop(0))
    
    while lstp:
        answer += lstp.pop(0)


    for i in range(len(lstm)//2):
        answer += (lstm.pop(0)*lstm.pop(0))

    for i in range(len(lstz)):
        if lstm:
            lstm.pop(0)
        else:
            break
    
    while lstm:
        answer += lstm.pop(0)

    print(answer)





