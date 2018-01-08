total = 0


def add_test(a, b):
    global total  # 使用全局变量
    total = a + b
    print(total)


def list_reverse():
    list.reverse()


add_test(12, 4)
print(total)

list = [1, 23, 4, 5, "sdf"]
list_reverse()
print(list)
