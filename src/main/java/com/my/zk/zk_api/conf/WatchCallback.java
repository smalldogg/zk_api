package com.my.zk.zk_api.conf;

import lombok.Data;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangyh
 * @create 2020-12-20 14:25
 */


/**
 * 在这里得到配置文件的动态刷新,说明一下，对数据的读取才有事件的概念
 * watch的类型。1.getDate() exists() 设置的是data watches
 * 2.getChildren()设置的是child watches.
 * 区分一下不同的情况
 * 1.节点不存在
 * 1.1 调用exists() 当有事件时。设置conf
 * 2.节点存在
 * 2.1节点内容改变时，对conf重新赋值
 */
@Data public class WatchCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
		private MyConf myConf;
		private ZooKeeper zk;
		private CountDownLatch countDownLatch = new CountDownLatch( 1 );


		public void aWait( ) throws InterruptedException {
				zk.exists( "/conf", this, this, "ctx" );
				countDownLatch.await( );
		}



		/**
		 * exists的回调方法
		 * 这里如果节点存在了，那么就去拿节点的内容
		 * @param i
		 * @param s
		 * @param o
		 * @param stat
		 */
		@Override public void processResult( int i, String s, Object o, Stat stat ) {
				if (stat != null) zk.getData( "/conf",this,this,"ctx" );
		}

		/**
		 * getData时的回调方法
		 *
		 * @param i
		 * @param s
		 * @param o
		 * @param bytes
		 * @param stat
		 */
		@Override public void processResult( int i, String s, Object o, byte[] bytes, Stat stat ) {
				if (bytes != null) {
						String content = new String( bytes );
						String[] split = content.split( ":" );
						myConf.setIp( split[0] );
						myConf.setPort( Integer.valueOf( split[1] ) );
						countDownLatch.countDown();
				}
		}

		/**
		 *
		 * 等待节点的创建
		 *
		 * @param watchedEvent
		 */
		@Override public void process( WatchedEvent watchedEvent ) {
				switch( watchedEvent.getType( ) ) {
						case None:
								break;
						case NodeCreated: {
								zk.getData("/conf", this, this,"ctx");
								break;
						}
						case NodeDeleted: {
								myConf.setIp( "" );
								myConf.setPort( -1 );
								break;
						}
						case NodeDataChanged: {
								zk.getData("/conf", this, this,"ctx");
								break;
						}
						case NodeChildrenChanged: {
								//zk.exists( "/conf",this,this,"ctx");
								break;
						}
				}
		}
}
