import sys
input = sys.stdin.readline
from collections import defaultdict

def solution(string):
    leng = len(string)
    info = defaultdict(list)
   
    for i in range(leng):
        if string.count(string[i]) >= k:
            info[string[i]].append(i)

    if not info:
        return (-1,)
    
    min_leng, max_leng = 10000, 0

    for idx_lst in info.values():
        for j in range(len(idx_lst)-k+1):
            temp = idx_lst[j+k-1] - idx_lst[j] + 1

            if temp < min_leng:
                min_leng = temp

            if temp > max_leng:
                max_leng  = temp
    
    return min_leng, max_leng


if __name__ == "__main__":
    T = int(sys.stdin.readline())

    for t in range(T):
        string, k = input(), int(input())
        print(*solution(string))
