import sys
input = sys.stdin.readline

def solution(preorder, inorder):
    if len(preorder) == 0:
        return

    root = preorder[0]
    mid_index = inorder.index(root)

    preorder_left = preorder[1:mid_index+1]
    preorder_right = preorder[mid_index+1:]
    
    inorder_left = inorder[:mid_index]
    inorder_right = inorder[mid_index+1:]


    solution(preorder_left, inorder_left)
    solution(preorder_right, inorder_right)
    print(root,end=' ')


if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        n = int(input())
        preorder = list(map(int,input().split()))
        inorder = list(map(int,input().split()))
        solution(preorder, inorder)
        print()
