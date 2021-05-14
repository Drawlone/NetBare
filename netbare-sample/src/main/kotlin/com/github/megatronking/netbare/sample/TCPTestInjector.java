package com.github.megatronking.netbare.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.netbare.injector.SimpleTCPInjector;
import com.github.megatronking.netbare.tcp.TCPRequest;
import com.github.megatronking.netbare.tcp.TCPResponse;

public class TCPTestInjector extends SimpleTCPInjector {
    @Override
    public boolean sniffRequest(@NonNull TCPRequest request) {
        return false;
    }

    @Override
    public boolean sniffResponse(@NonNull TCPResponse response) {
        return false;
    }
}
