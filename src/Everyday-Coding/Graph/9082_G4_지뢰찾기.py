



if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        n = int(input())
        lst1 = list(map(int,input()))
        lst2 = list(input())

        for i in range(n):
            if lst2[i] == '*':
                lst2[i] = 'x'
                if i-1>=0:
                    lst1[i-1] -=1
                lst1[i] -=1
                if i+1 < n:
                    lst1[i+1] -=1
        
        print(lst1)
        print(lst2)