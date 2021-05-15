package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.Request;
import com.github.megatronking.netbare.gateway.Response;
import com.github.megatronking.netbare.gateway.VirtualGateway;
import com.github.megatronking.netbare.gateway.VirtualGatewayFactory;
import com.github.megatronking.netbare.net.Session;

import java.util.ArrayList;
import java.util.List;

public class TCPVirtualGatewayFactory implements VirtualGatewayFactory {

    private final List<TCPInterceptorFactory> mFactories;

    public TCPVirtualGatewayFactory(List<TCPInterceptorFactory> factories){
        this.mFactories = factories;
    }

    @Override
    public VirtualGateway create(Session session, Request request, Response response) {
        return new TcpVirtualGateway(session, request, response, new ArrayList<>(mFactories));
    }
}
