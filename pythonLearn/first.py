# K——means聚类用户分群(K值取3,4,5,6,7进行迭代优化)

# pip install numpy
# pip install matplotlib

from numpy import *
import time
import matplotlib.pyplot as plt


# 计算距离函数
def eucldistance(vector1, vector2):
    return sqrt(sum(power(vector2 - vector1, 2)))


# 在样本集中随机选取k个样本点作为初始质心
def initCentroids(dataSet, k):
    numSamples, dim = dataSet.shape()
    centroids = zeros((k, dim))
    for i in range(k):
        index = int(random.uniform(0, numSamples))
        centroids[i, :] = dataSet[index, :]
    return centroids


# k-means cluster
def kmeans(dataSet, k):
    numSamples = dataSet.shape[0]
    clusterAssment = mat(zeros((numSamples, 2)))
    clusterChanged = True

    # 在样本集中随机选取k个样本点作为初始质心
    centroids = initCentroids(dataSet, k)

    while clusterChanged:
        clusterChanged = False
        ## for each sample  
        for i in range(numSamples):
            minDist = 100000.0
            minIndex = 0

            # 计算每个样本点与质点之间的距离，将其归内到距离最小的那一簇
            for j in range(k):
                distance = eucldistance(centroids[j, :], dataSet[i, :])
                if distance < minDist:
                    minDist = distance
                    minIndex = j

                    # k个簇里面与第i个样本距离最小的的标号和距离保存在clusterAssment中
            # 若所有的样本不在变化，则退出while循环
            if clusterAssment[i, 0] != minIndex:
                clusterChanged = True
                clusterAssment[i, :] = minIndex, minDist ** 2

                ## update centroids
        for j in range(k):
            # clusterAssment[:,0].A==j是找出矩阵clusterAssment中第一列元素中等于j的行的下标，返回的是一个以array的列表，第一个array为等于j的下标
            pointsInCluster = dataSet[nonzero(clusterAssment[:, 0].A == j)[0]]  # 将dataSet矩阵中相对应的样本提取出来
            centroids[j, :] = mean(pointsInCluster, axis=0)  # 计算标注为j的所有样本的平均值

    print('Congratulations, cluster complete!')
    return centroids, clusterAssment


# show your cluster only available with 2-D data
# centroids为k个类别，其中保存着每个类别的质心
# clusterAssment为样本的标记，第一列为此样本的类别号，第二列为到此类别质心的距离
def showCluster(dataSet, k, centroids, clusterAssment):
    numSamples, dim = dataSet.shape()
    if dim != 2:
        print("Sorry! I can not draw because the dimension of your data is not 2!")
        return 1

    mark = ['or', 'ob', 'og', 'ok', '^r', '+r', 'sr', 'dr', '<r', 'pr']
    if k > len(mark):
        print("Sorry! Your k is too large! ")
        return 1

        # draw all samples
    for i in range(numSamples):
        markIndex = int(clusterAssment[i, 0])  # 为样本指定颜色
        plt.plot(dataSet[i, 0], dataSet[i, 1], mark[markIndex])

    mark = ['Dr', 'Db', 'Dg', 'Dk', '^b', '+b', 'sb', 'db', '<b', 'pb']
    # draw the centroids  
    for i in range(k):
        plt.plot(centroids[i, 0], centroids[i, 1], mark[i], markersize=12)
    plt.show()


##测试数据文件 testSet.txt
from numpy import *
import time
import matplotlib.pyplot as plt
# import KMeans

##load data  
dataSet = []
fileIn = open("D:/testSet.txt")
for line in fileIn.readlines():
    temp = []
    lineArr = line.strip().split('\t')
    temp.append(float(lineArr[0]))
    temp.append(float(lineArr[1]))
    dataSet.append(temp)

fileIn.close()

##clustering
print("step 2: clustering...")
dataSet = mat(dataSet)  # mat()函数数组转化为矩阵
k = 3
centroids, clusterAssment = kmeans(dataSet, k)  # 调用KMeans文件中定义的kmeans方法。

##show the result  
print("step 3: show the result...")
showCluster(dataSet, k, centroids, clusterAssment)
