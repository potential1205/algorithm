
if __name__ == "__main__":
    n = int(input())
    k = int(input())
    lst = list(map(int,input().split()))
    lst.sort()
    
    if k>=n:
        print(0)
        exit()

    result = []

    for i in range(n-1):
        result.append(lst[i+1]-lst[i])
    
    result.sort(reverse=True)

    for i in range(k-1):
        result.pop(0)


    print(sum(result))