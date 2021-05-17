package com.github.megatronking.netbare.tcp;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import com.github.megatronking.netbare.http.HttpUtils;

/**
 * 解析TCP数据到{@link TCPSession}, 同时过滤掉属于HTTP协议的TCP包
 */
public class TCPDataParseInterceptor extends TCPDataInterceptor{
    @Override
    protected void intercept(@NonNull TCPRequestChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        if(HttpUtils.verifyHttpType(buffer) == HttpUtils.TYPE_INVALID){
            chain.request().session().TCPData = buffer;
            chain.process(buffer);
        }else {
            chain.processFinal(buffer);
        }

    }

    @Override
    protected void intercept(@NonNull TCPResponseChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        if(HttpUtils.verifyHttpType(buffer) == HttpUtils.TYPE_INVALID) {
            chain.response().session().TCPData = buffer;
            chain.process(buffer);
        }else {
            chain.processFinal(buffer);
        }
    }

}
