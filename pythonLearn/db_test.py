#!usr/bin/python
# -*- coding -*-
# python3 use pymysql

import pymysql

db = pymysql.connect("localhost","root","root","test")

cursor = db.cursor()

print(cursor.execute("select * from name_test"))

data = cursor.fetchone()

print(data)