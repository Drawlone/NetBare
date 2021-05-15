package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.injector.InjectorCallback;
import com.github.megatronking.netbare.stream.Stream;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPResponseInjectorCallback implements InjectorCallback {

    private TCPResponseChain mChain;

    @Override
    public void onFinished(Stream stream) throws IOException {

    }

    @Override
    public void onFinished(ByteBuffer buffer) throws IOException {
        TCPSession tcpSession = mChain.response().session();
        tcpSession.TCPData = buffer;
        mChain.process(buffer);
    }
}
