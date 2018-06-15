package com.catmate.reserve;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.Room_photoDto;
import com.catmate.dto.User_profileDto;
import com.catmate.dto.Wish_listDto;
import com.catmate.mypage.service.MypageService;
import com.catmate.reserve.service.ReserveService;

@Controller
public class Search {

    @Autowired
    ReserveService reserveService;
    @Autowired
    MypageService mypageService;

    @RequestMapping(value="/reserve/search", method=RequestMethod.GET)
    public String search(HttpServletRequest request, Pet_sitter_houseDto pet_sitter_houseDto) {

        List<String> tagsList = new ArrayList<String>();
        tagsList.add("아파트");
        tagsList.add("마당");
        tagsList.add("환자견케어");
        tagsList.add("자격증보유");
        tagsList.add("실외배변");
        tagsList.add("반려견 없는 곳");
        tagsList.add("픽업");
        tagsList.add("수제간식");
        tagsList.add("응급처치");
        tagsList.add("투약가능");
        
        List<String> tags_nameList = new ArrayList<String>();
        tags_nameList.add("care_space");
        tags_nameList.add("yard");
        tags_nameList.add("patient_dog");
        tags_nameList.add("license");
        tags_nameList.add("outdoor_bowels");
        tags_nameList.add("other_animal");
        tags_nameList.add("pick_up");
        tags_nameList.add("homemade_snacks");
        tags_nameList.add("first_aid");
        tags_nameList.add("medication_possible");
        
        request.setAttribute("tags_name", tags_nameList);
        request.setAttribute("tags", tagsList);
        return "reserve/search";
    }

    @RequestMapping(value="/reserve/search_ajax", method=RequestMethod.GET)
    public void search(HttpServletRequest request, HttpServletResponse response, HttpSession session, Pet_sitter_houseDto pet_sitter_houseDto, int page_num) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<ReservationDto> tmpReservationList = reserveService.getReservationList();
        List<ReservationDto> reservationList = new ArrayList<ReservationDto>();
        List<Pet_sitter_houseDto> pet_sitter_house_notList = null;
        List<Wish_listDto> wish_listList = mypageService.getWish_listList((User_profileDto) session.getAttribute("user_profile"));
        List<Integer> pet_countList = new ArrayList<Integer>();
        List<List<Room_photoDto>> room_photoList = new ArrayList<List<Room_photoDto>>();
        List<User_profileDto> user_profileList = new ArrayList<User_profileDto>();
        List<Integer> review_countList = new ArrayList<Integer>();
        
        int page_size = 2;
        int start_row = (page_num * page_size) - page_size;
        
        Map<String, Object> search_map = new HashMap<String, Object>();
        Map<String, Object> result_map = new HashMap<String, Object>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date start_date = Date.valueOf(request.getParameter("start_date"));  // 예약날짜 넣기
            Date end_date = Date.valueOf(request.getParameter("end_date"));
            
            int start_int = Integer.parseInt(sdf.format(start_date));
            int end_int = Integer.parseInt(sdf.format(end_date));
            
            for(ReservationDto tmpReservationDto : tmpReservationList) {  // 예약 안되는 날 게시글 가져오기
                
                int db_start_int = Integer.parseInt(sdf.format(tmpReservationDto.getStart_day()));
                int db_end_int = Integer.parseInt(sdf.format(tmpReservationDto.getEnd_day()));
                
                if(!(end_int < db_start_int || start_int > db_end_int)) {
                    reservationList.add(tmpReservationDto);
                }
                    
            }  // for
            
        } catch(Exception e) {}

        if(reservationList.isEmpty()) {  // 예약 가능 한 게시판
            pet_sitter_house_notList = reserveService.getPet_sitter_houseList();
        } else {
            pet_sitter_house_notList = reserveService.getPet_sitter_house_not(reservationList); 
        }
        
        search_map.put("idxList", pet_sitter_house_notList);
        search_map.put("pet_sitter_houseDto", pet_sitter_houseDto);
        search_map.put("start_row", start_row);
        search_map.put("page_size", page_size);
        
        List<Pet_sitter_houseDto> pet_sitter_houseList = reserveService.getPet_sitter_house_searchList(search_map);  // 필터 끝난 게시판

        for(Pet_sitter_houseDto pet_sitter_house : pet_sitter_houseList) {  // user_profile, pet_sitter_house photo 가져오기
            room_photoList.add(reserveService.getRoom_photo(pet_sitter_house.getIdx()));
            user_profileList.add(mypageService.getUser_profile(pet_sitter_house.getUser_email()));
            pet_countList.add(reserveService.getPetCount(pet_sitter_house.getUser_email()));
            review_countList.add(reserveService.getReviewCount(pet_sitter_house.getIdx()));
        }

        result_map.put("pet_sitter_houseList", pet_sitter_houseList);
        result_map.put("room_photoList", room_photoList);
        result_map.put("user_profileList", user_profileList);
        result_map.put("wish_listList", wish_listList);
        result_map.put("pet_countList", pet_countList);
        result_map.put("review_countList", review_countList);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonText = "";
        jsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result_map); 
        
        response.getWriter().print(jsonText);
    }

}
