package com.my.zk.zk_api.conf;

import lombok.Data;

/**
 * @author wangyh
 * @create 2020-12-20 9:37
 */
@Data
public class MyConf {
	private String ip;
	private Integer port;

		@Override public String toString( ) {
				return "MyConf{" + "ip='" + ip + '\'' + ", port=" + port + '}';
		}
}
