# -*- coding=utf-8 -*-
# from snownlp import SnowNLP
#
# s = SnowNLP(u'这个东西真心很赞')
#
# s.words  # [u'这个', u'东西', u'真心',
# # u'很', u'赞']
#
# s.tags  # [(u'这个', u'r'), (u'东西', u'n'),
# # (u'真心', u'd'), (u'很', u'd'),
# # (u'赞', u'Vg')]
#
# s.sentiments  # 0.9769663402895832 positive的概率
#
# s.pinyin  # [u'zhe', u'ge', u'dong', u'xi',
# # u'zhen', u'xin', u'hen', u'zan']
#
# s = SnowNLP(u'「繁體字」「繁體中文」的叫法在臺灣亦很常見。')
#
# print(s.han)  # u'「繁体字」「繁体中文」的叫法
# # 在台湾亦很常见。'
#
# text = u'''
# 自然语言处理是计算机科学领域与人工智能领域中的一个重要方向。
# 它研究能实现人与计算机之间用自然语言进行有效通信的各种理论和方法。
# 自然语言处理是一门融语言学、计算机科学、数学于一体的科学。
# 因此，这一领域的研究将涉及自然语言，即人们日常使用的语言，
# 所以它与语言学的研究有着密切的联系，但又有重要的区别。
# 自然语言处理并不是一般地研究自然语言，
# 而在于研制能有效地实现自然语言通信的计算机系统，
# 特别是其中的软件系统。因而它是计算机科学的一部分。
# '''
#
# s = SnowNLP(text)
# keyWords = s.keywords(3)
# print(keyWords)  # [u'语言', u'自然', u'计算机']
#
# s.summary(3)  # [u'因而它是计算机科学的一部分',
# # u'自然语言处理是一门融语言学、计算机科学、
# # 数学于一体的科学',
# # u'自然语言处理是计算机科学领域与人工智能
# # 领域中的一个重要方向']
#
# print(s.sentences)
#
# s = SnowNLP([[u'这篇', u'文章'],
#              [u'那篇', u'论文'],
#              [u'这个']])
# print(s.tf)
# print(s.idf)
# # [0.3756070762985226, 0, 0]
# print(s.sim(['文章']))
import sys
from snownlp import SnowNLP


#
# text = u'''
#     不好。自然语言处理是计算机科学领域与人工智能领域中的一个重要方向。
#     研究能实现人与计算机之间用自然语言进行有效通信的各种理论和方法。
#     自然语言处理是一门融语言学、计算机科学、数学于一体的科学。
# 因此，这一领域的研究将涉及自然语言，即人们日常使用的语言，
# 所以它与语言学的研究有着密切的联系，但又有重要的区别。
# 自然语言处理并不是一般地研究自然语言，
# 而在于研制能有效地实现自然语言通信的计算机系统，
# 特别是其中的软件系统。因而它是计算机科学的一部分。
# '''
def analysis(text):
    s = SnowNLP(text)
    mention = s.sentiments
    if mention > 0.6:
        return 1
    elif mention < 0.4:
        return -1
    else:
        return 0


if __name__ == "__main__":
    print("test")
    text = sys.argv[1]
    mention = analysis(text)
    print(mention)
