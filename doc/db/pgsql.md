

### 表文件位置
SHOW DATA_DIRECTORY;
SELECT pg_relation_filepath('test');


### 先创建扩展功能
CREATE EXTENSION pageinspect; 

### 查看表文件页头信息
-- 0表的页数
select * from page_header(get_raw_page('test', 0));


### pg_filedump

### LSN与段文件
SELECT pg_current_wal_lsn(),pg_walfile_name(pg_current_wal_lsn());

### LSN，Log sequence number
wal日志命名 前8位 timeline，中间8位logid, 最后8位logseg





