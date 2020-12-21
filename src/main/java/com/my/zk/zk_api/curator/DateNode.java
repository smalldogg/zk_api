package com.my.zk.zk_api.curator;


import com.google.common.collect.Maps;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.sound.midi.Patch;
import java.util.Map;

/**
 * @author wangyh
 *
 * Curator的基本Api
 *
 *
 * @create 2020-12-21 11:31
 */
@Component
public class DateNode {

		/**
		 *
		 * 这里的api，都是同步方法
		 *
		 * @throws Exception
		 */
		@Test
		public void  testData() throws Exception {
				String path = "/test";
				CuratorFramework client = ZKUtil.getConnection( );
				if (client.checkExists().forPath( path ) == null)
					client.create().creatingParentsIfNeeded().withMode( CreateMode.PERSISTENT).forPath(path, "init".getBytes());
				if (client.checkExists().forPath( "/conf" ) != null) {
						byte[] bytes = client.getData( ).forPath( "/conf" );
						System.out.println( new String( bytes ) );
				}
		}

		@Test
		public void  testData2() throws Exception {
				CuratorFramework client = ZKUtil.getConnection( );
				if (client.checkExists().forPath("/path") != null) client.delete().forPath("/path");
				client.create().forPath("/path","init".getBytes("utf-8"));
		}



		public void  testData3() throws Exception {
				CuratorFramework client = ZKUtil.getConnection( );
				String nodeB = ZKUtil.getNodePath( "/nodeB" );
				System.out.println(nodeB );
		}

		// 删除节点


		private String path;
		private String content;

		//缓存
		@Test
		public void testData4() throws Exception {
				CuratorFramework client = ZKUtil.getConnection( );
				PathChildrenCache cache = new PathChildrenCache( client, "/nodeA", true);
				cache.start();
				cache.getListenable().addListener(new ZKListener());
				while (true) {
						System.out.println(path  + "--->" + content);
						Thread.sleep( 1000 );
				}
		}

		private class ZKListener implements PathChildrenCacheListener {

				@Override public void childEvent( CuratorFramework client, PathChildrenCacheEvent event ) throws Exception {
						switch(event.getType()) {
								case CHILD_ADDED: {
										path = ZKPaths.getNodeFromPath( event.getData( ).getPath( ) );
										byte[] data = event.getData( ).getData( );
										content = new String( data );
										break;
								}
								case CHILD_UPDATED: {
										path = ZKPaths.getNodeFromPath( event.getData( ).getPath( ) );
										byte[] data = event.getData( ).getData( );
										content = new String( data );
										break;
								}
								case CHILD_REMOVED: {
										path = ZKPaths.getNodeFromPath( event.getData( ).getPath( ) );
										break;
								}
								case CONNECTION_SUSPENDED:
										break;
								case CONNECTION_RECONNECTED:
										break;
								case CONNECTION_LOST:
										break;
								case INITIALIZED:
										break;
						}
				}
		}


		@Test
		public void testData5() {
				CuratorFramework client = ZKUtil.getConnection( );
				
		}



}
