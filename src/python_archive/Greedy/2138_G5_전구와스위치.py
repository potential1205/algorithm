
def switching(num):
    if num == '0':
        return '1'
    elif num == '1':
        return '0'

def solution(x):
    cnt = 0

    for i in range(1,n):
        if x[i-1] == y[i-1]:
            continue
        
        x[i-1] = switching(x[i-1])
        x[i] = switching(x[i])
        if i+1 < n:
            x[i+1] = switching(x[i+1])
        cnt+=1

    if y==x:
        return cnt
    else:
        return -1


if __name__ == "__main__":
    n = int(input())
    x = list(input())
    y = list(input())

    x1,x2 = x[:], x[:]
    x2[0] = switching(x2[0])
    x2[1] = switching(x2[1])

    val1 = solution(x1)
    val2 = solution(x2)

    if val1 == -1 and val2 == -1:
        print(-1)
    elif val1 == -1:
        print(val2+1)
    elif val2 == -1:
        print(val1)
    else:
        print(min(val1,val2+1))
