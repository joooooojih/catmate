package com.catmate.mypage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.catmate.dto.MsgDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;

@Component("msg_handler")
public class Msg_handler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions  = new HashMap<String, WebSocketSession>();

    @Autowired
    MypageService mypageService;
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String[] text = message.getPayload().split(":");
        
        Map<String, Object> map = session.getAttributes();
        User_profileDto to_user_profile = (User_profileDto) map.get("user_profile");  // 내 email
        MsgDto msgDto = new MsgDto();
        msgDto.setTo_user_email(to_user_profile.getUser_email());
        msgDto.setFrom_user_email(text[0]);
        
        if(text[2].equals("message")) {  // insertMsg
            msgDto.setMsg_content(text[1]);
            mypageService.insertMsg(msgDto);
            text[1] = "message";
        } else {  // 읽음 처리
            mypageService.updateMsg_read(msgDto);
        }
       
        WebSocketSession webSocketSession = sessions.get(msgDto.getTo_user_email());
        webSocketSession.sendMessage(new TextMessage(text[1]));  // 나
        webSocketSession = sessions.get(text[0]);
        if(webSocketSession != null) {
            webSocketSession.sendMessage(new TextMessage(text[1]));  // 상대
        }
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> map = session.getAttributes();
        User_profileDto user_profileDto = (User_profileDto) map.get("user_profile");
        sessions.put(user_profileDto.getUser_email(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Map<String, Object> map = session.getAttributes();
        User_profileDto user_profileDto = (User_profileDto) map.get("user_profile");
        sessions.remove(user_profileDto.getUser_email());
    }
}
