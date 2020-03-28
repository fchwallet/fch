package com.xyz.fch_sp.app.modular.wsHandler;


import com.xyz.fch_sp.app.core.websocket.InMessage;
import org.springframework.web.socket.WebSocketSession;

public interface WsHandler {
    public void handle(WebSocketSession webSocketSession, InMessage im);
}
