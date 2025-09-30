import sys
from collections import deque

N, K = map(int, sys.stdin.readline().split())


coin_list = deque()
for n in range(N):
    coin_list.append(int(sys.stdin.readline().strip()))

def find_min_coin_cnt(amount: int, used: int):
    if amount == 0:
        print(used)
        return
    coin = coin_list.pop()
    find_min_coin_cnt(amount % coin, used + amount // coin)


find_min_coin_cnt(K, 0)