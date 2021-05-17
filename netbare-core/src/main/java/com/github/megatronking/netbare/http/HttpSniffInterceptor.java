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

import androidx.annotation.NonNull;

import com.github.megatronking.netbare.NetBareLog;
import com.github.megatronking.netbare.ssl.SSLWhiteList;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A fronted interceptor verifies the first net packet in order to determine whether it is a HTTP
 * protocol packet. If the packet is not a valid HTTP packet, it will be sent to tunnel directly,
 * otherwise sent to the next interceptor.
 *
 * @author Megatron King
 * @since 2018-12-04 11:58
 */
/* package */ public final class HttpSniffInterceptor extends HttpIndexedInterceptor {



    private final HttpSession mSession;

    private int mType;

    /* package */ HttpSniffInterceptor(HttpSession session) {
        this.mSession = session;
    }

    @Override
    protected void intercept(@NonNull HttpRequestChain chain, @NonNull ByteBuffer buffer,
                             int index) throws IOException {
        if (index == 0) {
            if (SSLWhiteList.contains(chain.request().ip())) {
                mType = HttpUtils.TYPE_WHITELIST;
                NetBareLog.i("detect whitelist ip " + chain.request().ip());
            } else {
                mType = chain.request().host() == null ? HttpUtils.TYPE_INVALID : HttpUtils.verifyHttpType(buffer);
            }
        }
        if (mType == HttpUtils.TYPE_HTTPS) {
            mSession.isHttps = true;
        }
        if ((mType == HttpUtils.TYPE_INVALID) || (mType == HttpUtils.TYPE_WHITELIST)) {
            chain.processFinal(buffer);
            return;
        }
        chain.process(buffer);
    }

    @Override
    protected void intercept(@NonNull HttpResponseChain chain, @NonNull ByteBuffer buffer,
                             int index) throws IOException {
        if ((mType == HttpUtils.TYPE_INVALID) || (mType == HttpUtils.TYPE_WHITELIST)) {
            chain.processFinal(buffer);
            return;
        }
        chain.process(buffer);
    }




}
