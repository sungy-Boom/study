a = b = c = 0
print(a, b, c)

# 复数
comNum = complex(3, 6)
print(comNum)

print()

# 字符串
strTest = "string test.....123"
print(strTest[:19])  # 默认从0开始，输出到1
print(strTest[4::3])  # 字符串切割，第一个是从哪里开始切，第二个是隔几个字符切一下
print(strTest[1:])  # 输出字符串，第一个开始位置，第二个是结束位置

print()

# list
list = ["test", "list", "hello"]
print(list[0:])
print(list[::4])  # 这个切割是按照 endPoint%len 来进行输出的
print(list[2])  # 输出第几个元素
# print(list[5])  # endPoint>len 报错

print()

# tuple
tupleTet = ("three", 1, "four", 4, 5)
print(tupleTet)
# tupleTet[2] = 5  # tuple类型不支持修改，是只读类型的

print()

# dictionary
dic = {"first": "fds", "second": 234}
print(dic)
print(dic.get("first"))
print(dic.keys())
print(dic.values())

print()

a = 2.0
b = 2.0
print(a, b)
print(a is b)
print(a == b)

a = "str"
b = "str"
print(a, b)
print(a is b)
print(a == b)

a = {"f", 1}
b = {"f", 1}
print(a, b)
print(a is b)
print(a == b)

print(repr(10))
print(ord('f'))
print(hex(12))
print(oct(12))

#  时间戳
import time

print(time.time())
