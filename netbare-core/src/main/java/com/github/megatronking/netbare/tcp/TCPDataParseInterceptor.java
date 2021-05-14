package com.github.megatronking.netbare.tcp;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPDataParseInterceptor extends TCPDataInterceptor{
    @Override
    protected void intercept(@NonNull TCPRequestChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        chain.request().session().TCPData = buffer;
    }

    @Override
    protected void intercept(@NonNull TCPResponseChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {

    }
}
