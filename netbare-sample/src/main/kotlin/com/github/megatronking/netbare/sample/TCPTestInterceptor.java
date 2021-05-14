package com.github.megatronking.netbare.sample;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.megatronking.netbare.tcp.TCPDataInterceptor;
import com.github.megatronking.netbare.tcp.TCPInterceptor;
import com.github.megatronking.netbare.tcp.TCPInterceptorFactory;
import com.github.megatronking.netbare.tcp.TCPRequestChain;
import com.github.megatronking.netbare.tcp.TCPResponseChain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TCPTestInterceptor extends TCPDataInterceptor {

    final String TAG = "TCPTest";

    @Override
    protected void intercept(@NonNull TCPRequestChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        String msg = byteBufferToString(buffer);
        Log.i(TAG, msg);
    }

    @Override
    protected void intercept(@NonNull TCPResponseChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        Log.i(TAG, chain.response().tcpData().toString());
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

    public static String byteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer = null;
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer);
            buffer.flip();
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
