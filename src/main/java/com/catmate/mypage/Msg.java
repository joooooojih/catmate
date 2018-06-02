package com.catmate.mypage;

import java.io.IOException;
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

    @RequestMapping(value="mypage/msg_user_list", method=RequestMethod.GET)
    public void msg_list(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<User_profileDto> user_profileList = new ArrayList<User_profileDto>();
        user_profileList.add(mypageService.getUser_profile("joooooojih@gmail.com"));
        user_profileList.add(mypageService.getUser_profile("jooojih@naver.com"));
        user_profileList.add(mypageService.getUser_profile("whtjwls1234@naver.com"));

        // 각 email 마다 msg 최근 1개 + 안읽은 갯수

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_profileList", user_profileList);

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
        int end_row = page_num * page_size;
        Map<String, Object> search_map = new HashMap<String, Object>();
        search_map.put("msgDto", msgDto);
        search_map.put("start_row", start_row);
        search_map.put("end_row", end_row);
        
        Map<String, Object> result_map = new HashMap<String, Object>();
        result_map.put("msgList", mypageService.getMsgList(search_map));
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
        
        MsgDto msg_new = mypageService.getMsg_new(msgDto);
        if((msg_new.getTo_user_email().equals(to_user_profile.getUser_email()) && msg_new.getFrom_user_email().equals(msgDto.getFrom_user_email())) || 
                (msg_new.getTo_user_email().equals(msgDto.getFrom_user_email()) && msg_new.getFrom_user_email().equals(to_user_profile.getUser_email()))
                ) {
            result_map.put("msg_new", mypageService.getMsg_new(msgDto));
            result_map.put("to_user_profile", to_user_profile);
            result_map.put("from_user_profile", mypageService.getUser_profile(msgDto.getFrom_user_email()));
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonText = "";
            jsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result_map); 
            response.getWriter().print(jsonText);
        }
        
    }
  

}
