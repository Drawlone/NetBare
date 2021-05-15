package com.github.megatronking.netbare.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.github.megatronking.netbare.NetBare
import com.github.megatronking.netbare.NetBareConfig
import com.github.megatronking.netbare.NetBareListener
import com.github.megatronking.netbare.gateway.InterceptorFactory
import com.github.megatronking.netbare.http.HttpInjectInterceptor
import com.github.megatronking.netbare.http.HttpInterceptorFactory
import com.github.megatronking.netbare.ssl.JKS
import com.github.megatronking.netbare.tcp.TCPInjectInterceptor
import com.github.megatronking.netbare.tcp.TCPInterceptorFactory
import java.io.IOException

class MainActivity : AppCompatActivity(), NetBareListener {

    companion object {
        private const val REQUEST_CODE_PREPARE = 1
    }

    private lateinit var mNetBare : NetBare

    private lateinit var mActionButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNetBare = NetBare.get()

        mActionButton = findViewById(R.id.action)
        mActionButton.setOnClickListener {
            if (mNetBare.isActive) {
                mNetBare.stop()
            } else{
                prepareNetBare()
            }
        }

        // 监听NetBare服务的启动和停止
        mNetBare.registerNetBareListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mNetBare.unregisterNetBareListener(this)
        mNetBare.stop()
    }

    override fun onServiceStarted() {
        runOnUiThread {
            mActionButton.setText(R.string.stop_netbare)
        }
    }

    override fun onServiceStopped() {
        runOnUiThread {
            mActionButton.setText(R.string.start_netbare)
        }
    }

    private fun prepareNetBare() {
        // 安装自签证书
        if (!JKS.isInstalled(this, App.JSK_ALIAS)) {
            try {
                JKS.install(this, App.JSK_ALIAS, App.JSK_ALIAS)
            } catch(e : IOException) {
                // 安装失败
            }
            return
        }
        // 配置VPN
        val intent = NetBare.get().prepare()
        if (intent != null) {
            startActivityForResult(intent, REQUEST_CODE_PREPARE)
            return
        }
        // 启动NetBare服务
        mNetBare.start(NetBareConfig.defaultTCPConfig(interceptorFactories()))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PREPARE) {
            prepareNetBare()
        }
    }

    private fun interceptorFactories() : List<TCPInterceptorFactory> {

        // 可以添加其它的拦截器，注入器
        // ...
        val interceptor = TCPTestInterceptor().createFactory()
        val injector = TCPInjectInterceptor.createFactory(TCPTestInjector())
        return listOf(interceptor, injector)
    }


}
