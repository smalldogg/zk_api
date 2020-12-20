package com.my.zk.zk_api.conf;

import lombok.Data;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangyh
 * @create 2020-12-20 14:18
 */


/**
 * 连接时的watch
 */
@Data
public class DefaultWatch implements Watcher {

		private CountDownLatch countDownLatch;


		@Override
		public void process( WatchedEvent watchedEvent ) {
				switch( watchedEvent.getState() ) {
						case Unknown:
								break;
						case Disconnected:
								break;
						case NoSyncConnected:
								break;
						case SyncConnected: //当连接成功的时候，在这里释放
								countDownLatch.countDown();
								break;
						case AuthFailed:
								break;
						case ConnectedReadOnly:
								break;
						case SaslAuthenticated:
								break;
						case Expired:
								break;
				}
		}


}
