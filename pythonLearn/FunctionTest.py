import pymysql

db = pymysql.connect("localhost", "root", "root", "test")
cursor = db.cursor()

#
# sql = "select * from name_test"
# cursor.execute(sql)
# list = cursor.fetchall()
# for item in list:
#     str_utf8 = item.encode("gbk")
#     print(str_utf8.decode("utf-8", "strict"))
# print(list)

c = ""
print(type(c))
print("\"" + c + "\"")


def update_test():
    sql = "replace into name_test(`id`, `name`, `sub`, `add_time`) values(34, \"\", 2123, \"2018-01-22 10:54:12\")"
    try:
        cursor.execute(sql)
        print(sql)
        db.commit()
        pass
    except Exception as e:
        print(str(e))
        pass


def list_test():
    list = []
    list.append(12)
    list.append("23123")
    for item in list:
        print(item)


# update_test()
list_test()