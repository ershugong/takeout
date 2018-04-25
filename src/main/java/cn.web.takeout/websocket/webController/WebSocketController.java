package cn.web.takeout.websocket.webController;


import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@ServerEndpoint("/websocket")//该注解表示该类被声明为一个webSocket终端
public class WebSocketController {

    //线程安全的socket集合
    public static Map<String,WebSocketController> webSocketSet = new HashMap<>();
    public static Map<String,Session> sessionSet = new HashMap<>();
    Map<String,String> map = new HashMap<>();
    //会话
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.put(session.getId(),this);
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        String shopId = map.get(this.session.getId());
        sessionSet.remove(shopId);
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        System.out.println("来自客户端的消息:"+message);
        if(!message.equals("")){
            sessionSet.put(message,session);
            map.put(session.getId(),message);
        }
    }
}
