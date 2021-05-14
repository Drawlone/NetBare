/*
 *  NetBare - An android network capture and injection library.
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
package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.Request;
import com.github.megatronking.netbare.gateway.Response;
import com.github.megatronking.netbare.gateway.SpecVirtualGateway;
import com.github.megatronking.netbare.gateway.VirtualGateway;
import com.github.megatronking.netbare.http.HttpInterceptorFactory;
import com.github.megatronking.netbare.ip.Protocol;
import com.github.megatronking.netbare.net.Session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link VirtualGateway} that is responsible for TCP protocol packets interception.
 *
 * @author Megatron King
 * @since 2019-04-06 17:03
 */
public class TcpVirtualGateway extends SpecVirtualGateway {

    private final List<TCPInterceptor> mInterceptors;
    private final TCPRequest mRequest;
    private final TCPResponse mResponse;

    public TcpVirtualGateway(Session session, Request request, Response response,
                             final List<TCPInterceptorFactory> factories) {
        super(Protocol.TCP, session, request, response);
        TCPSession tcpSession = new TCPSession();
        mRequest = new TCPRequest(tcpSession);
        mResponse = new TCPResponse(tcpSession);
        this.mInterceptors = new ArrayList<>(4);
        mInterceptors.add(new TCPDataParseInterceptor());
        for(TCPInterceptorFactory factory: factories){
            mInterceptors.add(factory.create());
        }
    }

    @Override
    protected void onSpecRequest(ByteBuffer buffer) throws IOException {
        new TCPRequestChain(mRequest, mInterceptors).process(buffer);
    }

    @Override
    protected void onSpecResponse(ByteBuffer buffer) throws IOException {
        new TCPResponseChain(mResponse, mInterceptors).process(buffer);
    }

    @Override
    protected void onSpecRequestFinished() {
        for(TCPInterceptor interceptor: mInterceptors){
            interceptor.onRequestFinished(mRequest);
        }
    }

    @Override
    protected void onSpecResponseFinished() {
        for(TCPInterceptor interceptor: mInterceptors){
            interceptor.onResponseFinished(mResponse);
        }
    }
}
