package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.Request;

import java.nio.ByteBuffer;

public class TCPRequest extends Request {
    private final TCPSession mSession;

    TCPRequest(TCPSession session){
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
