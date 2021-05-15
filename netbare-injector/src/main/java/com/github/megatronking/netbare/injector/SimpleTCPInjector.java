package com.github.megatronking.netbare.injector;

import android.support.annotation.NonNull;

import com.github.megatronking.netbare.tcp.TCPRequest;
import com.github.megatronking.netbare.tcp.TCPResponse;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleTCPInjector implements TCPInjector{
    @Override
    public boolean sniffRequest(@NonNull TCPRequest request) {
        return false;
    }

    @Override
    public boolean sniffResponse(@NonNull TCPResponse response) {
        return false;
    }

    @Override
    public void onRequestInjector(ByteBuffer buffer, @NonNull InjectorCallback callback) throws IOException {
        callback.onFinished(buffer);
    }

    @Override
    public void onResponseInjector(ByteBuffer buffer, @NonNull InjectorCallback callback) throws IOException {
        callback.onFinished(buffer);
    }

    @Override
    public void onRequestFinished(@NonNull TCPRequest request) {

    }

    @Override
    public void onResponseFinished(@NonNull TCPResponse response) {

    }
}
