
if __name__ == "__main__":
    n, s = map(int,input().split())
    lst = list(map(int,input().split()))

    left,right = 0, 0
    cum = lst[0]
    answer = float("inf")

    while True:
        if cum < s:
            right += 1
            if right == n:
                break

            cum += lst[right]
            
        elif cum >= s:
            answer = min(answer, right-left+1)
            if answer == 1:
                break
            cum -= lst[left]
            left += 1
            
            
    if answer == float("inf"):
        print(0)
    else:
        print(answer)