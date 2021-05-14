package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.AbstractResponseChain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class TCPResponseChain extends AbstractResponseChain<TCPResponse, TCPInterceptor> {
    protected TCPResponseChain(TCPResponse response, List<TCPInterceptor> interceptors) {
        super(response, interceptors);
    }

    @Override
    public TCPResponse response() {
        return null;
    }

    @Override
    protected void processNext(ByteBuffer buffer, TCPResponse flow, List<TCPInterceptor> interceptors, int index, Object tag) {

    }
}
