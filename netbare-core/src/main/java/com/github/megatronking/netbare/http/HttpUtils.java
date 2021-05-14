package com.github.megatronking.netbare.http;

import com.github.megatronking.netbare.NetBareLog;
import com.github.megatronking.netbare.ssl.SSLCodec;

import java.nio.ByteBuffer;

public final class HttpUtils {
    public static final int TYPE_HTTP = 1;
    public static final int TYPE_HTTPS = 2;
    public static final int TYPE_INVALID = 3;
    public static final int TYPE_WHITELIST = 4;

    public static int verifyHttpType(ByteBuffer buffer) {
        if (!buffer.hasRemaining()) {
            return TYPE_INVALID;
        }
        byte first = buffer.get(buffer.position());
        switch (first) {
            // GET
            case 'G':
                // HEAD
            case 'H':
                // POST, PUT, PATCH
            case 'P':
                // DELETE
            case 'D':
                // OPTIONS
            case 'O':
                // TRACE
            case 'T':
                // CONNECT
            case 'C':
                return TYPE_HTTP;
            // HTTPS
            case SSLCodec.SSL_CONTENT_TYPE_ALERT:
            case SSLCodec.SSL_CONTENT_TYPE_APPLICATION_DATA:
            case SSLCodec.SSL_CONTENT_TYPE_CHANGE_CIPHER_SPEC:
            case SSLCodec.SSL_CONTENT_TYPE_EXTENSION_HEARTBEAT:
            case SSLCodec.SSL_CONTENT_TYPE_HANDSHAKE:
                return TYPE_HTTPS;
            default:
                // Unknown first byte data.
                NetBareLog.e("Unknown first request byte : " + first);
                break;
        }
        return TYPE_INVALID;
    }
}
