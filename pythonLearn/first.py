"""
money =200
----------------
判断 money >500
    打印 你是富人
判断 maoney > 300
    打印 你是中产阶级
判断 maoney < 100
    打印 你是屌丝
"""

money = 10
if money > 500:
    print("你是富人")
if money > 300:
    print("你是中产阶级")
if money < 100:
    print("你是屌丝")

if money > 500:
    print("你是富人")
elif money > 300:
    print("你是中产")
else:
    print("你是屌丝")

# list ,set ,map,tuple
# list = [1,2,3,4,5,6,7,8,9]
# 通过range可以生成一个从0到100的数组
list1 = range(100)
for item in list1:
    print(item)

# map
# keyandvalue,keys,values
map = {"key1": "values1", "key2": "values2"}
for item in map.items():
    print(item[0], item[1])
for key in map.keys():
    print(key)
for value in map.values():
    print(value)

# set
set = (["1", "2", "3", 4])
for item in set:
    print(item)

# tuple
t = ("a", "b", "d", [1, 2, 3, 4])
for item in t:
    print(item)

# while
n = 0
while n <= 10:
    print(n)
    n += 1

# for循环100个数据，只想打印偶数
# 打印到 48的终端for循环
# break
# continue跳过当前循环
for i in range(100):
    if i % 2 == 0:
        # 如果下面用 continue，则不打印48
        # 如果下面用 break，者48以后的都不再带引了
        if i == 48:
            break
            # zcontinue
        print(i)
