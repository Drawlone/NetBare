package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.Response;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPResponse extends Response {
    private final TCPSession mSession;
    private final Response mResponse;

    TCPResponse(Response response, TCPSession session){
        this.mResponse = response;
        this.mSession = session;
    }

    TCPSession session(){
        return mSession;
    }

    @Override
    public void process(ByteBuffer buffer) throws IOException {
        mResponse.process(buffer);
    }

    public ByteBuffer tcpData(){
        return mSession.TCPData;
    }

    @Override
    public String ip() {
        return  mResponse.ip();
    }

    @Override
    public int port() {
        return mResponse.port();
    }
}
