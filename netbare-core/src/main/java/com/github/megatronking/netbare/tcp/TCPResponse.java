package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.Response;

import java.nio.ByteBuffer;

public class TCPResponse extends Response {
    private final TCPSession mSession;

    TCPResponse(TCPSession session){
        this.mSession = session;
    }

    TCPSession session(){
        return mSession;
    }

    public ByteBuffer tcpData(){
        return mSession.TCPData;
    }

    @Override
    public String ip() {
        return super.ip();
    }

    @Override
    public int port() {
        return super.port();
    }
}
