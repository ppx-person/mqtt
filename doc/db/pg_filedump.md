

### yum install pg_filedump_15.x86_64


### 查看表对应的文件
SHOW DATA_DIRECTORY;
select pg_relation_filepath('t_dump');
--- "base/16389/16390"'


/usr/pgsql-15/bin/pg_filedump /var/lib/pgsql/15/data/base/16387/16427

/usr/pgsql-15/bin/pg_filedump -D int,charn /var/lib/pgsql/15/data/base/16387/16519 | grep COPY


### BgWriter来刷新数据
### vacuum_defer_cleanup_age (integer) 来调整 Dead 元组在数据库中的数量


pg_dirtyread 






