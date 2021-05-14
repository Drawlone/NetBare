package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.Request;
import com.github.megatronking.netbare.gateway.Response;
import com.github.megatronking.netbare.gateway.VirtualGateway;
import com.github.megatronking.netbare.gateway.VirtualGatewayFactory;
import com.github.megatronking.netbare.net.Session;

public class TCPVirtualGatewayFactory implements VirtualGatewayFactory {
    @Override
    public VirtualGateway create(Session session, Request request, Response response) {
        return new TcpVirtualGateway(session, request, response);
    }
}
