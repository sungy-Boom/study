#!/usr/bin/python3
# -*- coding:utf-8 -*-

import threading
from queue import Queue
import pymysql
import snownlp
import datetime
import urllib.parse
import time

db = pymysql.connect("localhost", "root", "root", "comment_analysis")
cursor = db.cursor()
update_cursor = db.cursor()
db.ping(True)
start_position = 1
handled_num = 0
row_distance = 500


class Producer(threading.Thread):
    def __init__(self, block_queue):
        threading.Thread.__init__(self)
        self.queue = block_queue
        self.exists = False
        pass

    def run(self):
        global start_position
        total_num = self.get_total_rows()
        while True:
            sql = "select * from relation where id >= %d limit 500" % start_position
            try:
                cursor.execute(sql)
                pass
            except Exception as e:
                print("select exception ", str(e))
                db.connect()
                continue
                pass
            records = cursor.fetchall()

            # 队列已满，等待
            while True:
                if Queue.qsize(self.queue) < row_distance:
                    break
                    pass
                pass
            for item in records:
                self.queue.put(item)
                pass
            start_position += row_distance
            if start_position > total_num:
                break
            pass
        pass

    # 获取数据总数
    @staticmethod
    def get_total_rows():
        sql = "select count(*) from relation"
        cursor.execute(sql)
        record = cursor.fetchone()
        return record[0]


class Consumer(threading.Thread):
    def __init__(self, block_queue):
        threading.Thread.__init__(self)
        self.queue = block_queue
        self.exists = False
        pass

    def run(self):
        while True:
            self.exists = False
            sql = "replace into relation(`id`, `cid`, `user_name`, `comment`, `evaluation`, `add_time`) values"
            rows = 0
            # 队列不为空
            while not Queue.empty(self.queue):
                rows += 1
                sql = sql + self.get_sql_sequence(self.queue)
                if rows == row_distance:
                    self.exists = True
                    self.update_db(sql, "")
                    rows = 0
                    break
                sql = sql + ","
                global handled_num
                handled_num += 1
                pass
            if self.exists:
                self.queue.task_done()
        pass

    @staticmethod
    def update_db(sql, sequence):
        while True:
            try:
                global handled_num
                # update_cursor.executemany(sql, sequence)
                update_cursor.execute(sql)
                db.commit()
                handled_num += 1
                print("have analyzed rows :", handled_num)
                break
            except Exception as e:
                print("update db exception ", str(e))
                db.connect()
                pass
            pass
        pass

    # 进行情感计算
    @staticmethod
    def analysis_mention(text):
        s = snownlp.SnowNLP(text)
        mention = s.sentiments
        if mention > 0.6:
            return 1
        elif mention < 0.4:
            return -1
        else:
            return 0
        pass

    # 获取拼接好的sql
    def get_sql_sequence(self, bolck_queue):
        res = bolck_queue.get()
        sql_extend = ""
        for i in range(len(res) - 1):
            temp_str = res[i]
            if isinstance(res[i], str) or isinstance(res[i], datetime.datetime):
                temp_str = "\"" + str(res[i]) + "\""

            if i == 0:
                sql_extend = sql_extend + "(" + str(temp_str) + ","
                continue
            elif i == len(res) - 2:
                sql_extend = sql_extend + str(temp_str) + ")"
                continue
            if i == 4:
                text = urllib.parse.unquote(res[3])
                mention = 0
                if len(text) > 0:
                    mention = self.analysis_mention(text)
                    pass
                sql_extend = sql_extend + str(mention) + ","
                continue
            else:
                sql_extend = sql_extend + str(temp_str) + ","
                continue
        return sql_extend


if __name__ == "__main__":
    start = datetime.datetime.now()
    queue = Queue(row_distance)
    producer = Producer(queue)
    consumer = Consumer(queue)
    producer.start()
    consumer.start()

    end = datetime.datetime.now()

    print((end - start).seconds)

    # db.close()
