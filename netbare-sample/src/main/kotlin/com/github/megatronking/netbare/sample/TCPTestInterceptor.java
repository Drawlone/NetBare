package com.github.megatronking.netbare.sample;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.megatronking.netbare.gateway.Interceptor;
import com.github.megatronking.netbare.tcp.TCPDataInterceptor;
import com.github.megatronking.netbare.tcp.TCPInterceptor;
import com.github.megatronking.netbare.tcp.TCPInterceptorFactory;
import com.github.megatronking.netbare.tcp.TCPRequestChain;
import com.github.megatronking.netbare.tcp.TCPResponseChain;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPTestInterceptor extends TCPDataInterceptor {

    final String TAG = "TCPTest";

    @Override
    protected void intercept(@NonNull TCPRequestChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        Log.i(TAG, chain.request().tcpData().toString());
    }

    @Override
    protected void intercept(@NonNull TCPResponseChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {

    }

    public TCPInterceptorFactory createFactory(){
        return new TCPInterceptorFactory() {
            @NonNull
            @Override
            public TCPInterceptor create() {
                return new TCPTestInterceptor();
            }
        };
    }
}
