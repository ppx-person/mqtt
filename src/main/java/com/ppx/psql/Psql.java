package com.ppx.psql;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.postgresql.PGConnection;
import org.postgresql.replication.LogSequenceNumber;
import org.postgresql.replication.PGReplicationStream;

/**
 * 注: 参数
 * pro.setProperty("replication", "database");
 * pro.setProperty("assumeMinServerVersion", "15.0");
 * 主从架构下，如果从库还没有收到wal日志，主库就把wal删除了，这样就不能自动恢复.复制slot就是为了确保主库不会把那些
 * 还没有被传到从库的wal日志给删除。如从库已宕机，磁盘撑满，设置max_slot_wal_keep_size来设置wal文件的保留的上限
 *
 * 
 * 
 * 
 * pg_create_logical_replication_slot(slot_name, plugin_name)
 * 如：pg_create_logical_replication_slot
 * SELECT * FROM pg_create_logical_replication_slot('regression_slot', 'test_decoding');
 * pg_create_logical_replication_slot('test_logical_slot', 'wal2json');
 * test_decoding自带的，直接在psql中安装插件：create extension test_decoding;
 * --- waltojson15 ---
 * wget https://yum.postgresql.org/15/redhat/rhel-7-x86_64/wal2json_15-2.5-1.rhel7.x86_64.rpm
 * sudo yum install wal2json_15-2.5-1.rhel7.x86_64.rpm
 * 
 */
public class Psql {
	public static void main(String[] args) throws Exception {
		
		
		// https://pgjdbc.github.io/pgjdbc/documentation/server-prepare/
		// 复制slot https://blog.csdn.net/weixin_34550241/article/details/115783298
		// https://qa.1r1g.com/sf/ask/3650349111/
		System.out.println("xxxxxxxxxxxx--begin");
		
		Class.forName("org.postgresql.Driver");
		
		Properties pro = new Properties();
		pro.setProperty("user", "postgres");
		pro.setProperty("password", "postgres");
		pro.setProperty("dbname", "iot");
		pro.setProperty("replication", "database");
		pro.setProperty("assumeMinServerVersion", "15.0");
		 
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/iot", pro);
		System.out.println("xxxxxxxconn:" + conn);
		// SELECT pg_current_wal_lsn();
		// 9003D20
		String startLSNStr = "0/90034C8";
		LogSequenceNumber startLSN = LogSequenceNumber.valueOf(startLSNStr);
		PGConnection pgConn = conn.unwrap(PGConnection.class);
		
//		pgConn.getReplicationAPI()
//        .createReplicationSlot()
//        .logical()
//        .withSlotName("regression_slot004")
//        .withOutputPlugin("test_decodeding")
//        .make();
		
		PGReplicationStream stream = pgConn.
                getReplicationAPI()
                .replicationStream()
                .logical()
                .withSlotName("regression_slot_001")
                .withStartPosition(startLSN)
                .withSlotOption("include-xids", true)
                .withSlotOption("include-timestamp", true)
                .withSlotOption("skip-empty-xacts", true)
                .start();
		
		String event = null;
		while (true) {
			ByteBuffer record = stream.read();
			if (record != null) {
				final int offset = record.arrayOffset();
				final byte[] source = record.array();
				final int length = source.length - offset;
				event = new String(source, offset, length);
				System.out.println("event:" + event + "|" + stream.getLastReceiveLSN());
			}		
		}
		
//		PGReplicationStream stream = streamBuilder.start();
//		
//		
//		System.out.println("xxxxxxxxxxxx--pgConn:" + pgConn);
// 		System.out.println("xxxxxxxxxxxx--end");
	}
	
	

}
