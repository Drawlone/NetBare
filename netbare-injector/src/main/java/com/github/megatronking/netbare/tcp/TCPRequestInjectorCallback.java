package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.injector.InjectorCallback;
import com.github.megatronking.netbare.stream.Stream;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPRequestInjectorCallback implements InjectorCallback {

    private final TCPRequestChain mChain;

    public TCPRequestInjectorCallback(TCPRequestChain chain){
        this.mChain = chain;
    }

    @Override
    public void onFinished(Stream stream) throws IOException {

    }

    @Override
    public void onFinished(ByteBuffer buffer) throws IOException {
        TCPSession tcpSession = mChain.request().session();
        tcpSession.TCPData = buffer;
        mChain.process(buffer);
    }
}
