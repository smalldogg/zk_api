package com.my.zk.zk_api.conf;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wangyh
 * @create 2020-12-20 14:28
 */

public class Configration {
		ZooKeeper zk;


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


		/**
		 * 在这里测试
		 */
		@Test
		public void getConf() throws InterruptedException {
				WatchCallback watchCallBack = new WatchCallback();
				watchCallBack.setZk(zk);
				MyConf myConf = new MyConf();
				watchCallBack.setMyConf(myConf);
				watchCallBack.aWait();
				while (true ) {
						System.err.println(myConf);
						Thread.sleep(400);
				}
		}


}
