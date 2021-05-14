/*  NetBare - An android network capture and injection library.
 *  Copyright (C) 2018-2019 Megatron King
 *  Copyright (C) 2018-2019 GuoShi
 *
 *  NetBare is free software: you can redistribute it and/or modify it under the terms
 *  of the GNU General Public License as published by the Free Software Found-
 *  ation, either version 3 of the License, or (at your option) any later version.
 *
 *  NetBare is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *  PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with NetBare.
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.megatronking.netbare.http;

import com.github.megatronking.netbare.stream.Stream;
import com.github.megatronking.netbare.injector.InjectorCallback;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;

/**
 * A HTTP request packets injector callback.
 *
 * @author Megatron King
 * @since 2018-12-15 21:55
 */
public class HttpRequestInjectorCallback implements InjectorCallback {

    private HttpRequestChain mChain;

    /**
     * Constructs a {@link InjectorCallback} for HTTP request.
     *
     * @param chain A {@link HttpInterceptor} chain.
     */
    public HttpRequestInjectorCallback(HttpRequestChain chain) {
        this.mChain = chain;
    }

    @Override
    public void onFinished(Stream stream) throws IOException {
        ByteBuffer byteBuffer = stream.toBuffer();
        if (stream instanceof HttpRequestHeaderPart) {
            HttpRequestHeaderPart header = (HttpRequestHeaderPart) stream;
            HttpSession session = mChain.request().session();
            session.method = header.method();
            session.protocol = header.protocol();
            session.path = header.path();
            session.requestHeaders = new LinkedHashMap<>(header.headers());
            session.reqBodyOffset = byteBuffer.remaining();
        }
        mChain.process(byteBuffer);
    }

    @Override
    public void onFinished(ByteBuffer buffer) {

    }

}