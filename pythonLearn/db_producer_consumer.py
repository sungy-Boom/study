#!/usr/bin/python3
# -*- coding:utf-8 -*-

import threading
from queue import Queue
import pymysql
import time

db = pymysql.connect("localhost", "root", "root", "comment_analysis")
cursor = db.cursor()
start_position = 0


class Producer(threading.Thread):
    """
    构造函数
    """

    def __init__(self, block_queue):
        threading.Thread.__init__(self)
        self.queue = block_queue
        pass

    def run(self):
        global start_position
        total_num = self.get_total_rows()
        while start_position <= total_num:
            sql = "select * from relation where id >= %d limit 200" % start_position
            cursor.execute(sql)
            records = cursor.fetchall()
            for item in records:
                self.queue.put(item)
                pass
            start_position += 200
            pass
        pass

    # 获取数据总数
    def get_total_rows(self):
        sql = "select count(*) from relation"
        cursor.execute(sql)
        record = cursor.fetchone()
        return record[0]


class Consumer(threading.Thread):
    def __init__(self, block_queue):
        threading.Thread.__init__(self)
        self.queue = block_queue
        pass

    def run(self):
        # print(Queue.qsize(self.queue))
        while True:
            while not Queue.empty(self.queue):
                res = self.queue.get()
                for i in range(len(res)):
                    print(res[i])
                    pass
                self.queue.task_done()
                pass
            pass
        pass


if __name__ == "__main__":
    queue = Queue(200)
    producer = Producer(queue)
    # time.sleep(5)
    consumer = Consumer(queue)
    producer.start()
    consumer.start()
    # producer = Producer(queue)
    # print(producer.get_total_rows())


# def queue_test(queue):
# for i in range(0, 5):
#         queue.put(i)
#     queue.get()
#     print(Queue.qsize(queue))
#
#
# queue = Queue(5)
# queue_test(queue)
