import sys

N, M = map(int, sys.stdin.readline().split())

name_to_num = {}
num_to_name = {}

for n in range(1, N + 1):
    name = sys.stdin.readline().strip()
    name_to_num[name] = n
    num_to_name[n] = name

for m in range(M):
    s = sys.stdin.readline().strip()
    if s.isdigit():
        print(num_to_name[int(s)])
    else:
        print(name_to_num[s])
