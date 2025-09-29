import sys

h, m = map(int, sys.stdin.readline().split())
c = int(sys.stdin.readline().strip())

m += c
if m >= 60:
    h += m // 60
    m = m % 60

if h >= 24:
    h -= 24

print(h, m)
