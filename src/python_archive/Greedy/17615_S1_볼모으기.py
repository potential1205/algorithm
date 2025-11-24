if __name__ == "__main__":
    n = int(input())
    balls = input()

    info = []

    temp = 'R' if balls[0] == 'R' else 'B'
    info, cnt = [], 1

    for i in range(1,n):
        if balls[i] == temp:
            cnt+=1
        else:
            info.append(cnt)
            cnt = 1
            temp = balls[i]

    info.append(cnt)

    s1,s2,s3,s4 = 0,0,0,0

    for i in range(len(info)-1):
        if i%2==0:
            s1 += info[i]
        elif i%2!=0:
            s2 += info[i]

    for i in range(1,len(info)):
        if i%2==0:
            s3 += info[i]
        elif i%2!=0:
            s4 += info[i]

    print(min(s1,s2,s3,s4))