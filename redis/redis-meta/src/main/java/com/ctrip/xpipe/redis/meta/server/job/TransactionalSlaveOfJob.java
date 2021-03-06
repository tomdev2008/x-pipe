package com.ctrip.xpipe.redis.meta.server.job;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import com.ctrip.xpipe.api.command.Command;
import com.ctrip.xpipe.api.pool.SimpleKeyedObjectPool;
import com.ctrip.xpipe.api.pool.SimpleObjectPool;
import com.ctrip.xpipe.netty.commands.NettyClient;
import com.ctrip.xpipe.redis.core.entity.RedisMeta;
import com.ctrip.xpipe.redis.core.protocal.cmd.transaction.TransactionalSlaveOfCommand;

/**
 * @author wenchao.meng
 *
 * Dec 12, 2016
 */
public class TransactionalSlaveOfJob extends AbstractRedisesSlaveofJob{

	public TransactionalSlaveOfJob(List<RedisMeta> slaves, String masterHost, int masterPort,
			SimpleKeyedObjectPool<InetSocketAddress, NettyClient> clientPool, ScheduledExecutorService scheduled) {
		super(slaves, masterHost, masterPort, clientPool, scheduled);
	}

	@Override
	protected Command<?> createSlaveOfCommand(SimpleObjectPool<NettyClient> clientPool, String masterHost,
			int masterPort) {
		return new TransactionalSlaveOfCommand(clientPool, masterHost, masterPort, scheduled);
	}

}
