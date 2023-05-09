package com.ppx.psql;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.postgresql.PGConnection;
import org.postgresql.replication.LogSequenceNumber;
import org.postgresql.replication.PGReplicationStream;

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
		// Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/iot?replication=database", "postgres", "postgres");
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
                .start();
		
		String event = null;
		while (true) {
			ByteBuffer record = stream.read();
			if (record != null) {
				final int offset = record.arrayOffset();
				final byte[] source = record.array();
				final int length = source.length - offset;
				event = new String(source, offset, length);
				System.out.println("event:" + event);
			}		
		}
		
//		PGReplicationStream stream = streamBuilder.start();
//		
//		
//		System.out.println("xxxxxxxxxxxx--pgConn:" + pgConn);
// 		System.out.println("xxxxxxxxxxxx--end");
	}
	
	

}
