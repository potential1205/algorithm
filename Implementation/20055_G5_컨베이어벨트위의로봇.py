from collections import deque

# def rotate():
#     # 벨트 회전
#     temp = belt[2*n-1]
#     for i in range(2*n-1,0,-1):
#         belt[i] = belt[i-1]
#     belt[0] = temp

#     # 벨트 회전에 의한 로봇 회전
#     for i in range(len(robot)):
#         robot[i] += 1
    
#     if len(robot)!=0 and robot[0] == 2*n-1:
#         robot.popleft()

#     return


# def move():
#     flag = False
#     for i in range(len(robot)):
#         if robot[i] + 1 == 2*n-1: # 만약 로봇의 다음 칸이 내리는 위치라면
#             flag = True
#         else:
#             if (robot[i]+1) not in robot and belt[robot[i]+1] > 0:
#                 robot[i] += 1
    
#     if flag:
#         robot.popleft()

#     return


if __name__ == "__main__":
    n,k = map(int,input().split())
    belt, robot = deque(map(int,input().split())), deque([0]*n)
    t = 0

    while True:
        if belt.count(0) >= k:
            break
        
        belt.rotate(1)
        robot.rotate(1)
        robot[-1] = 0
        if len(robot) != 0:
            for i in range(n-2,-1,-1):
                if robot[i] == 1 and robot[i+1] == 0 and belt[i+1] > 0:
                    robot[i] = 0
                    robot[i+1] = 1
                    belt[i+1] -= 1
            robot[-1] = 0
        
        if robot[0] == 0 and belt[0] > 0:
            robot[0] = 1
            belt[0] -= 1
        
        t += 1
    
    print(t)
 
        
