import sys
input = sys.stdin.readline


if __name__ == "__main__":
    m,n,l,k = map(int,input().split())

    stars=[]
    for i in range(k):
        stars.append(list(map(int,input().split())))
        
    result = 0
    for i in range(len(stars)):
        for j in range(len(stars)):
            cnt = 0
            for cur in range(len(stars)):
                y,x = stars[cur]

                if stars[i][0] <= y <= stars[i][0] + l and stars[j][1] <= x <= stars[j][1] + l:
                    cnt+=1
            result = max(result, cnt)

    print(k-result)

