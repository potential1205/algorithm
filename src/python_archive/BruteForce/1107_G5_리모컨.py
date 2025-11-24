import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    m = int(input())
    lst = list(map(int,input().split()))
    min_cnt = abs(100-n)

    for num in range(1000001):
        num = str(num)
        leng = len(num)

        for idx,val in enumerate(num):
            if int(val) in lst:
                break
            
            elif idx == (leng-1):
                min_cnt = min(min_cnt, abs(n-int(num))+leng)

    print(min_cnt)