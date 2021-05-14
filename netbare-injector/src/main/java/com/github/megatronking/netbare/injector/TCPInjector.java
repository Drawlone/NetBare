package com.github.megatronking.netbare.injector;

import android.support.annotation.NonNull;

import com.github.megatronking.netbare.stream.Stream;
import com.github.megatronking.netbare.tcp.TCPRequest;
import com.github.megatronking.netbare.tcp.TCPResponse;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface TCPInjector {


    /**
     * Determine should the injector apply to this request.
     *
     * @param request TCP request session.
     * @return True if do injection to this request.
     */
    boolean sniffRequest(@NonNull TCPRequest request);

    /**
     * Determine should the injector apply to this response.
     *
     * @param response TCP response session.
     * @return True if do injection to this response.
     */
    boolean sniffResponse(@NonNull TCPResponse response);


    /**
     * Inject the TCP request data, call {@link InjectorCallback#onFinished(Stream)} after
     * the injection.
     *
     * @param buffer TCP Data
     * @param callback A injection finish callback.
     * @throws IOException If an I/O error has occurred.
     */
    void onRequestInjector(ByteBuffer buffer, @NonNull InjectorCallback callback) throws IOException;

    /**
     * Inject the TCP response data, call {@link InjectorCallback#onFinished(Stream)} after
     * the injection.
     *
     * @param buffer TCP Data
     * @param callback A injection finish callback.
     * @throws IOException If an I/O error has occurred.
     */
    void onResponseInjector(ByteBuffer buffer, @NonNull InjectorCallback callback) throws IOException;

    void onRequestFinished(@NonNull TCPRequest request);

    void onResponseFinished(@NonNull TCPResponse response);
}
