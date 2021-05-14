package com.github.megatronking.netbare.tcp;

import android.support.annotation.NonNull;

import com.github.megatronking.netbare.gateway.Interceptor;
import com.github.megatronking.netbare.gateway.InterceptorFactory;

public interface TCPInterceptorFactory extends InterceptorFactory {

    @NonNull
    @Override
    TCPInterceptor create();
}
