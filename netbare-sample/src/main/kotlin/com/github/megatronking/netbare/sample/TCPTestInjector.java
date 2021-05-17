package com.github.megatronking.netbare.sample;

import androidx.annotation.NonNull;

import com.github.megatronking.netbare.injector.InjectorCallback;
import com.github.megatronking.netbare.injector.SimpleTCPInjector;
import com.github.megatronking.netbare.tcp.TCPRequest;
import com.github.megatronking.netbare.tcp.TCPResponse;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPTestInjector extends SimpleTCPInjector {

    private int injectIndex = 0;

    @Override
    public boolean sniffRequest(@NonNull TCPRequest request) {
        ByteBuffer buffer = request.tcpData();
        byte[] src = buffer.array();
        byte[] target = {0x31, 0x32, 0x33};
        injectIndex = KMP(src, target);
        return injectIndex != -1;
    }



    @Override
    public boolean sniffResponse(@NonNull TCPResponse response) {
        return false;
    }

    @Override
    public void onRequestInjector(ByteBuffer buffer, @NonNull InjectorCallback callback) throws IOException {
        byte[] replace = {0x34, 0x35, 0x36};
        buffer.put(replace, injectIndex, replace.length);
        buffer.rewind();
        callback.onFinished(buffer);
    }

    public static int KMP(byte[] s1, byte[] s2){
        if(s1.length < s2.length || s2.length == 0) return -1;
        //求模式串的next数组
        int[] next=new int[s2.length];
        next[0]=-1;
        for(int i=1,k=-1;i<s2.length;i++){
            if(k==-1||s2[i-1]==s2[k]){
                k++;
                next[i]=k;
            }
            else {
                next[i]=0;
                k=0;
            }
        }
        //进行匹配
        for(int i=0,j=0;i<s1.length;){
            if(s1[i]!=s2[j]){
                if(next[j]==-1) i++;
                else j=next[j];
            }
            else{
                if(j==s2.length-1) return i-s2.length+1;
                i++;j++;
            }
        }
        return -1;
    }
}
