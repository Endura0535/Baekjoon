import sys
from collections import Counter

v = int(sys.stdin.readline())
vote = sys.stdin.readline().strip()
counter = Counter(vote)

if counter['A'] > counter['B']:
    print('A')
elif counter['A'] < counter['B']:
    print('B')
else:
    print('Tie')