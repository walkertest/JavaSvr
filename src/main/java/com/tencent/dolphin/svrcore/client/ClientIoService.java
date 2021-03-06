package com.tencent.dolphin.svrcore.client;

import com.tencent.dolphin.svrcore.CodecService;
import com.tencent.dolphin.svrcore.packet.IoPacket;
import com.tencent.dolphin.svrcore.ws.WorkerService;

import java.util.concurrent.Future;

/**
 * 网络客户端服务
 * @see KilimClientIoService
 * @see TcpClientIoService
 */
public interface ClientIoService<T_REQ extends IoPacket, T_RSP extends IoPacket> {

	void start();
	
	/**
	 * 注意传server CodecService会导致协议异常
	 * @return
	 */
	ClientIoService<T_REQ, T_RSP> setCodecService(CodecService codec);
	
	ClientIoService<T_REQ, T_RSP> setRouterService(RouterService routerService);
	
	ClientIoService<T_REQ, T_RSP> setWorkerService(WorkerService workerService);
	
	ClientIoService<T_REQ, T_RSP> setTimeoutManager(TimeoutManager timeoutManager);
	
	/**
	 * 异步调用
	 * @param req
	 * @param timeout
	 * @param callback
	 */
	void async(T_REQ req, long timeout, IoCallBack<T_REQ, T_RSP> callback);
	
	/**
	 * 同步调用
	 * @param req
	 * @param timeout
	 * @return
	 */
	T_RSP sync(T_REQ req, long timeout) throws  Exception;
	
	Future<T_RSP> send(T_REQ req);
}
