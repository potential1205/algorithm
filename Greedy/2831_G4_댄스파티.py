
def solution(lst1,lst2):
    global cnt
    idx,jdx = 0,0
    leng1,leng2 = len(lst1), len(lst2)

    lst1.sort(reverse=True)
    lst2.sort()

    while True:
        if idx == leng1 or jdx == leng2:
            break
        
        if abs(lst1[idx]) > abs(lst2[jdx]):
            cnt+=1
            idx+=1
            jdx+=1
        else:
            idx+=1
    return

if __name__ == "__main__":
    n = int(input())


    men = list(map(int,input().split()))
    women = list(map(int,input().split()))

    left_men,right_men = [],[]
    left_women,right_women = [],[]

    for man in men:
        if man < 0: left_men.append(man)
        else: right_men.append(man)
    
    for woman in women:
        if woman > 0: left_women.append(woman)
        else: right_women.append(woman)
    
    cnt = 0
    solution(left_men,left_women)
    solution(right_women,right_men)    
    print(cnt)