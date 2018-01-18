import pymysql

db = pymysql.connect("localhost", "root", "root", "test")
cursor = db.cursor()

sql = "select * from name_test"
cursor.execute(sql)
list = cursor.fetchall()
for item in list:
    str_utf8 = item.encode("gbk")
    print(str_utf8.decode("utf-8", "strict"))
print(list)
