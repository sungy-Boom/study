import os
from snownlp import SnowNLP

mention_list = []


def read_dir():
    path = "E:\\project\\java\\CommentAnalysis\\data\\train\\positive"
    pathDir = os.listdir(path)
    print('文档总数', len(pathDir))
    for file in pathDir:
        str = path + "\\" + file
        read_file_content(str)
    total = calculate()
    # print(pathDir)
    print("准确率：", total / len(pathDir))


def read_file_content(file):
    fo = open(file, "rb")
    content = fo.readlines()
    file_content = ''
    for item in content:
        file_content = file_content + item.decode('utf-8')

    # print(file_content)
    analysis(file_content)


def analysis(content):
    s = SnowNLP(content)
    mention = s.sentiments
    mention_list.append(mention)
    # print(mention)


def calculate():
    total = 0
    for item in mention_list:
        if item < 0.5:
            total += 1
    print(total)
    return total


read_dir()
