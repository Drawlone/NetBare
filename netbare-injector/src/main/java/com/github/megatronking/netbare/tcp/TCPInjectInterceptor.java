package com.github.megatronking.netbare.tcp;

import android.os.Process;
import android.support.annotation.NonNull;

import com.github.megatronking.netbare.gateway.Interceptor;
import com.github.megatronking.netbare.injector.TCPInjector;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPInjectInterceptor extends TCPDataInterceptor{

    private boolean mShouldInjectRequest;
    private boolean mShouldInjectResponse;

    private TCPInjector mTCPInjector;

    private TCPInjectInterceptor(TCPInjector tcpInjector){
        this.mTCPInjector = tcpInjector;
    }

    @Override
    protected void intercept(@NonNull TCPRequestChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        mShouldInjectRequest = mTCPInjector.sniffRequest(chain.request());
        if (!mShouldInjectRequest) {
            chain.process(buffer);
            return;
        }
        mTCPInjector.onRequestInjector(buffer, new TCPRequestInjectorCallback(chain));
    }

    @Override
    protected void intercept(@NonNull TCPResponseChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        if (chain.response().uid() == Process.myUid()) {
            chain.process(buffer);
            return;
        }
        mShouldInjectResponse = mTCPInjector.sniffResponse(chain.response());
        if (!mShouldInjectResponse) {
            chain.process(buffer);
            return;
        }
        mTCPInjector.onResponseInjector(buffer, new TCPResponseInjectorCallback());
    }

    @Override
    public void onRequestFinished(@NonNull TCPRequest request) {
        super.onRequestFinished(request);
        if (mShouldInjectRequest) {
            mTCPInjector.onRequestFinished(request);
        }
        mShouldInjectRequest = false;
    }

    @Override
    public void onResponseFinished(@NonNull TCPResponse response) {
        super.onResponseFinished(response);
        if (mShouldInjectRequest) {
            mTCPInjector.onResponseFinished(response);
        }
        mShouldInjectResponse= false;
    }

    public static TCPInterceptorFactory createFactory(final TCPInjector tcpInjector){
        return new TCPInterceptorFactory() {
            @NonNull
            @Override
            public TCPInterceptor create() {
                return new TCPInjectInterceptor(tcpInjector);
            }
        };
    }
}
