package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.AbstractResponseChain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class TCPResponseChain extends AbstractResponseChain<TCPResponse, TCPInterceptor> {

    TCPResponse mTCPResponse;

    protected TCPResponseChain(TCPResponse response, List<TCPInterceptor> interceptors) {
        this(response, interceptors, 0, null);
    }

    protected TCPResponseChain(TCPResponse response, List<TCPInterceptor> interceptors, int index, Object tag) {
        super(response, interceptors, index, tag);
        this.mTCPResponse = response;
    }

    @Override
    public TCPResponse response() {
        return mTCPResponse;
    }

    @Override
    protected void processNext(ByteBuffer buffer, TCPResponse flow, List<TCPInterceptor> interceptors, int index, Object tag) throws IOException {
        TCPInterceptor interceptor = interceptors.get(index);
        if(interceptor != null){
            interceptor.intercept(new TCPResponseChain(mTCPResponse, interceptors, ++index, tag), buffer);
        }
    }
}
