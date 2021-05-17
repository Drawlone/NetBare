package com.github.megatronking.netbare.tcp;

import androidx.annotation.NonNull;

import com.github.megatronking.netbare.gateway.InterceptorFactory;

public interface TCPInterceptorFactory extends InterceptorFactory {

    @NonNull
    @Override
    TCPInterceptor create();
}
