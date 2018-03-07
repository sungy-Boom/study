import xlwt
import xlrd
from xlrd.timemachine import xrange

excelFile = "C:\\Users\\Soul\\Desktop\\排班统计\\郑州排班统计-牛冬 2018-03.xlsx"
res_list = [0] * 12


def read_excel():
    data = xlrd.open_workbook(excelFile)
    table = data.sheets()[0]
    nrows = table.nrows  # 行数
    ncols = table.ncols  # 列数
    for i in xrange(1, nrows):
        rowValues = table.row_values(i)  # 某一行数据
        print(rowValues[2])
        index = (int(rowValues[2]) - 59).__mod__(100)
        res_list[index] = int(res_list[index] + rowValues[3])


print(res_list)


def write_excel():
    f = xlwt.Workbook()  # 创建工作簿
    sheet1 = f.add_sheet(u'sheet2', cell_overwrite_ok=True)  # 创建sheet
    for i in range(len(res_list)):
        sheet1.write(i, 0, res_list[i])  # 向表格中添加数据
    f.save('C:\\Users\\Soul\\Desktop\\统计.xls')  # 保存文件
    print(res_list)


read_excel()
write_excel()
