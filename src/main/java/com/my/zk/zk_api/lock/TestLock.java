package com.my.zk.zk_api.lock;

import com.my.zk.zk_api.conf.ZKUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * @author wangyh
 * @create 2020-12-20 15:18
 */

public class TestLock {

		ZooKeeper zk ;

		@Before
		public void conn () throws IOException, InterruptedException {
				zk  = ZKUtils.getConnection();
		}

		@After
		public void close (){
				try {
						zk.close();
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
		}




}
