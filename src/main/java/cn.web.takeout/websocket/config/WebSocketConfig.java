package cn.web.takeout.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @EnableWebSocketMessageBroker 的作用是在WebSocket 上启用 STOMP，
 * registerStompEndpoints方法的作用是websocket建立连接用的（也就是所谓的注册到指定的url），
 * configureMessageBroker方法作用是配置一个简单的消息代理。如果补充在，默认情况下会自动配置一个简单的内存消息队列，用来处理“/topic”为前缀的消息，
 * 但经过重载后，消息队列将会处理前缀为“/topic”、“/user”的消息，并会将“/app”的消息转给controller处理。
 * */
@Configuration
public class WebSocketConfig{

    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}