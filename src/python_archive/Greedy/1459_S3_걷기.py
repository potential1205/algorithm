

if __name__ == "__main__":
    m,n,c1,c2 = map(int,input().split())

    answer = 0
    big, small = max(m,n), min(m,n)

    if (big + small)%2!=0:
        big -= 1
        answer += c1

    answer += (small * min(c1*2, c2))
    max_val = big - small
    answer += (max_val * min(c1, c2))
    
    print(answer)
