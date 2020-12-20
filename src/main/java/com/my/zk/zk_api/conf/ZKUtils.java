package com.my.zk.zk_api.conf;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangyh
 * @create 2020-12-20 14:15
 */

public class ZKUtils {

		private static final String ip_port = "192.168.200.128:2181";
		private static ZooKeeper zk;
		private static CountDownLatch countDownLatch = new CountDownLatch( 1 );


		public static ZooKeeper getConnection() throws IOException, InterruptedException {
				DefaultWatch watcher = new DefaultWatch();
			zk = new ZooKeeper(ip_port, 1000, watcher);
			watcher.setCountDownLatch(countDownLatch);
			countDownLatch.await();
			return zk;
		}
}
