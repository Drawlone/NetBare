package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.Request;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPRequest extends Request {
    private final Request mRequest;
    private final TCPSession mSession;

    TCPRequest(Request request, TCPSession session){
        this.mRequest = request;
        this.mSession = session;
    }

    @Override
    public void process(ByteBuffer buffer) throws IOException {
        mRequest.process(buffer);
    }

    TCPSession session(){
        return mSession;
    }

    public ByteBuffer tcpData(){
        return mSession.TCPData;
    }

    @Override
    public String ip() {
        return mRequest.ip();
    }

    @Override
    public int port() {
        return mRequest.port();
    }
}
