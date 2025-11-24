
def check(arr,sym):
    if arr[0]==arr[1]==arr[2]==sym:
        return True
    if arr[3]==arr[4]==arr[5]==sym:
        return True
    if arr[6]==arr[7]==arr[8]==sym:
        return True
    if arr[0]==arr[3]==arr[6]==sym:
        return True
    if arr[1]==arr[4]==arr[7]==sym:
        return True
    if arr[2]==arr[5]==arr[8]==sym:
        return True
    if arr[0]==arr[4]==arr[8]==sym:
        return True
    if arr[2]==arr[4]==arr[6]==sym:
        return True
    return False

if __name__ == "__main__":
    while True:
        board = input()
        if board == "end":
            break

        cntO,cntX = board.count('O'), board.count('X')
        
        if cntX > cntO+1:
            print("invalid")
            continue

        if cntO > cntX:
            print("invalid")
            continue

        if cntO == cntX:
            if not check(board,'X') and check(board,'O'):
                print("valid")
                continue
        
        if cntO +1 == cntX:
            if not check(board,'O') and check(board,'X'):
                print("valid")
                continue

        if cntO == 4 and cntX == 5:
            if check(board,'X'):
                print("valid")
                continue
        
        print("invalid")