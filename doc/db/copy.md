

# copy
用于在posgreSql表和标准文件系统直接传输数据。
# copy to
copy表内容到文件，也可以copy select的查询结果


### 导出表
copy customers to stdout(delimiter '1')
copy customers to '/tmp/test.copy' (delimiter ' ')
### 导入表
copy customers from '/tmp/test_data.copy' (delimiter ' ')
customers可变成(select * from customers where name like 'A%')
### 如需要压缩
copy customers to program 'gzip > /tmp/test.copy.gz'
### 导出2列
copy customers(col, col2) to '/tmp/test.copy' delimiter ' ';
### 导出二进制文件
copy customers to '/tmp/data.dat' with binary;
## 导出csv文件
copy customers to '/tmp/data.csv' with csv;















