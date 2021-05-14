package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.AbstractRequestChain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class TCPRequestChain extends AbstractRequestChain<TCPRequest, TCPInterceptor> {
    protected TCPRequestChain(TCPRequest request, List<TCPInterceptor> interceptors) {
        super(request, interceptors);
    }

    @Override
    public TCPRequest request() {
        return null;
    }

    @Override
    protected void processNext(ByteBuffer buffer, TCPRequest flow, List<TCPInterceptor> interceptors, int index, Object tag) throws IOException {

    }
}
