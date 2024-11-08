import sys
input = sys.stdin.readline
from collections import defaultdict

def dfs(node,history):
    history.append(node)
    visit[node] = True

    for v in graph[node]:
        if v not in history:
            dfs(v,history[:])
        else:
            result.extend(history)
            return

if __name__ == "__main__":
    n = int(sys.stdin.readline().strip())
    graph = defaultdict(list)
    for i in range(1, n+1):
        v = int(int(input()))
        graph[v].append(i)

  
    visit = [False for i in range(n+1)]
    result = []
    for i in range(1,n+1):
        if not visit[i]:
            dfs(i,[])

    result.sort()
    print(len(result))
    for val in result:
        print(val)