def solution(s,t,lens,lent):
    global flag
    if lens > lent:
        return
    
    if s == t:
        flag = 1
        return
    
    t1 = t[:]
    t2 = t[::-1]

    if t1[-1] == 'A':
        solution(s,t1[:lent-1],lens,lent-1)

    if t2[-1] == 'B':
        solution(s,t2[:lent-1],lens,lent-1)

    return
    

if __name__ == "__main__":
    s = input()
    t = input()
    flag = 0
    solution(s,t,len(s),len(t))
    
    if flag == 1:
        print(1)
    else:
        print(0)