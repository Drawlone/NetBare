package com.github.megatronking.netbare.tcp;

import com.github.megatronking.netbare.gateway.IndexedInterceptor;


public abstract class TCPDataInterceptor extends IndexedInterceptor<TCPRequest, TCPRequestChain,
        TCPResponse, TCPResponseChain> implements TCPInterceptor{
}
