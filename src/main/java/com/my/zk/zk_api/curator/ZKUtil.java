package com.my.zk.zk_api.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.junit.Before;

/**
 * @author wangyh
 * @create 2020-12-21 11:22
 */

public class ZKUtil {

		private static String ip = "192.168.200.128:2181";
		private static String namespace = "curator";
		private static CuratorFramework client;
		private static int retryCount = 3;


		/**
		 * 得到zk客户端，创建成功之后，需要start方法
		 * @return
		 */
		public static void init() {
				RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, retryCount);
				client = CuratorFrameworkFactory.builder()
						.connectString(ip)
						.sessionTimeoutMs(3000 )
						.namespace(namespace)
						.retryPolicy( retryPolicy)
						.build();
				client.start();
		}


		public static CuratorFramework getConnection(){
				init();
				return client;
		}

		/**
		 *得到node路径
		 * @param name  needs with "/"
		 * @return
		 */
		public static String getNodePath(String path) {
				return ZKPaths.fixForNamespace( namespace,path );
		}



}
