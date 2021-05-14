package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.AbstractRequestChain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class TCPRequestChain extends AbstractRequestChain<TCPRequest, TCPInterceptor> {

    TCPRequest mTCPRequest;

    protected TCPRequestChain(TCPRequest request, List<TCPInterceptor> interceptors) {
        this(request, interceptors, 0, null);
    }

    protected TCPRequestChain(TCPRequest request, List<TCPInterceptor> interceptors, int index, Object tag) {
        super(request, interceptors, index, tag);
        this.mTCPRequest = request;
    }

    @Override
    public TCPRequest request() {
        return mTCPRequest;
    }

    @Override
    protected void processNext(ByteBuffer buffer, TCPRequest flow, List<TCPInterceptor> interceptors, int index, Object tag) throws IOException {
        TCPInterceptor interceptor = interceptors.get(index);
        if(interceptor != null){
            interceptor.intercept(new TCPRequestChain(mTCPRequest, interceptors, ++index, tag), buffer);
        }
    }
}
