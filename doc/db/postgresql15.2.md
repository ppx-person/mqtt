

# 初始化PostgreSQL15
initdb.exe -D "D:\Program Files\PostgreSQL\pgsql15.2\data" -E UTF-8 --locale=chs
# 启动PostgreSQL15
pg_ctl.exe -D "D:\Program Files\PostgreSQL\pgsql15.2\data" start
# 连接pgsql
psql postgres
创建postgres这个角色
# 命令行窗口(工具才能连接)
createuser -s -r postgres

create database testdatabase;
alter user postgres with password 'newpassword';
在data目录下，打开pg_hba.conf文件，修改如下位置：trust改成md5  重启

# postgre服务注册(管理员)
pg_ctl.exe register -N "pgsql" -D "D:\Program Files\PostgreSQL\pgsql15.2\data"


------------------- 工具 -------------------
# pg_resetwal.exe
select txid_current();
select pg_current_wal_lsn(), pg_walfile_name(pg_current_wal_lsn()),
pg_walfile_name_offset(pg_current_wal_lsn())
"D:\Program Files\PostgreSQL\pgsql15.2\bin\pg_resetwal.exe" -x 743 -l 000000010000000000000001 -D "D:\Program Files\PostgreSQL\pgsql15.2\data"

"D:\Program Files\PostgreSQL\pgsql15.2\bin\pg_receivewal.exe" -h localhost -D D:\temp\psql



------------------- 复制表功能 -------------------
CREATE SUBSCRIPTION s1
CONNECTION 'host=101.91.151.161 dbname=iot application_name=s1 user=postgres password=postgres'
PUBLICATION p1;


https://www.postgresql.org/docs/current/logical-replication-col-lists.html#LOGICAL-REPLICATION-COL-LIST-EXAMPLES



--------------- linux安装 -------------------
# Install the repository RPM:
sudo yum install -y https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm

# Install PostgreSQL:
sudo yum install -y postgresql15-server
更多工具
sudo yum install -y postgresql15-contrib



# Optionally initialize the database and enable automatic start:
sudo /usr/pgsql-15/bin/postgresql-15-setup initdb
sudo systemctl enable postgresql-15
sudo systemctl start postgresql-15




----------- root角色 --------------
create user root with password 'root'; 
GRANT ALL PRIVILEGES ON DATABASE iot to root;
ALTER ROLE root WITH SUPERUSER;



--- waltojson15 ---
wget https://yum.postgresql.org/15/redhat/rhel-7-x86_64/wal2json_15-2.5-1.rhel7.x86_64.rpm
sudo yum install wal2json_15-2.5-1.rhel7.x86_64.rpm

https://github.com/eulerto/wal2json

