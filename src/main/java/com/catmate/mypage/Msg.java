package com.catmate.mypage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.MsgDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;

@Controller
public class Msg {

    @Autowired
    MypageService mypageService;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");

    @RequestMapping(value="mypage/msg_user_list", method=RequestMethod.GET)
    public void msg_list(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User_profileDto myUser_profileDto = (User_profileDto) session.getAttribute("user_profile");
        MsgDto msgDto = new MsgDto();
        
        List<User_profileDto> allUser_profileList = mypageService.getUser_profileList();
        List<User_profileDto> user_profileList = new ArrayList<User_profileDto>();
        List<MsgDto> msgList = new ArrayList<MsgDto>();
        List<MsgDto> newMsgList = null;
        
        msgDto.setTo_user_email(myUser_profileDto.getUser_email());
        for(User_profileDto user_profileDto : allUser_profileList) {  // 목록 뽑기
            msgDto.setFrom_user_email(user_profileDto.getUser_email());
            int count = mypageService.getMsgCount(msgDto);
            if(count != 0) {
                msgList.add(mypageService.getMsgLately(msgDto));
            }
        }
        
        if(!msgList.isEmpty()) {  // 최신순 재 배열
            newMsgList = mypageService.getMsgLatelyList(msgList);
            for(MsgDto tmpMsgDto : newMsgList) {
                if(tmpMsgDto.getTo_user_email().equals(myUser_profileDto.getUser_email())) {
                    user_profileList.add(mypageService.getUser_profile(tmpMsgDto.getFrom_user_email()));
                } else {
                    user_profileList.add(mypageService.getUser_profile(tmpMsgDto.getTo_user_email()));
                }
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_profileList", user_profileList);
        map.put("msgList", newMsgList);

        ObjectMapper mapper = new ObjectMapper();
        String jsonText = "";
        jsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map); 
        response.getWriter().print(jsonText);
    }

    @RequestMapping(value="mypage/msg", method=RequestMethod.GET)
    public String msg(HttpSession session, HttpServletRequest request) {
        User_profileDto from_user_profile = mypageService.getUser_profile(request.getParameter("user_email"));
        request.setAttribute("from_user_profile", from_user_profile);
        return "mypage/msg";
    }

    @RequestMapping(value="mypage/msg_list", method=RequestMethod.GET)
    public void msg_list(HttpSession session, HttpServletResponse response, MsgDto msgDto, int page_num) throws JsonGenerationException, JsonMappingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User_profileDto to_user_profile = (User_profileDto) session.getAttribute("user_profile");
        msgDto.setTo_user_email(to_user_profile.getUser_email());
        int page_size = 20;
        int start_row = (page_num * page_size) - page_size;
        
        Map<String, Object> search_map = new HashMap<String, Object>();
        search_map.put("msgDto", msgDto);
        search_map.put("start_row", start_row);
        search_map.put("page_size", page_size);
        
        Map<String, Object> result_map = new HashMap<String, Object>();
        
        List<MsgDto> msgList = mypageService.getMsgList(search_map);
        List<String> msg_date = new ArrayList<String>();
        for(MsgDto tmpMsgDto : msgList) {
            msg_date.add(sdf.format(tmpMsgDto.getMsg_date()));
        }
        result_map.put("msgList", msgList);
        result_map.put("msg_date", msg_date);
        result_map.put("to_user_profile", to_user_profile);
        result_map.put("from_user_profile", mypageService.getUser_profile(msgDto.getFrom_user_email()));
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonText = "";
        jsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result_map); 
        response.getWriter().print(jsonText);
    }
  
    @RequestMapping(value="mypage/msg_new", method=RequestMethod.GET)
    public void msg_new(HttpSession session, HttpServletResponse response, MsgDto msgDto) throws JsonGenerationException, JsonMappingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User_profileDto to_user_profile = (User_profileDto) session.getAttribute("user_profile");
        msgDto.setTo_user_email(to_user_profile.getUser_email());
        Map<String, Object> result_map = new HashMap<String, Object>();
        
        MsgDto msg_search = mypageService.getMsg_new(msgDto);
        if((msg_search.getTo_user_email().equals(to_user_profile.getUser_email()) && msg_search.getFrom_user_email().equals(msgDto.getFrom_user_email())) || 
                (msg_search.getTo_user_email().equals(msgDto.getFrom_user_email()) && msg_search.getFrom_user_email().equals(to_user_profile.getUser_email()))
                ) {
            
            
            result_map.put("msg_search", msg_search);
            result_map.put("msg_date", sdf.format(msg_search.getMsg_date()));
            result_map.put("to_user_profile", to_user_profile);
            result_map.put("from_user_profile", mypageService.getUser_profile(msgDto.getFrom_user_email()));
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonText = "";
            jsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result_map); 
            response.getWriter().print(jsonText);
        }
        
    }
  

}
