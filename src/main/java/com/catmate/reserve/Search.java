package com.catmate.reserve;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.reserve.service.ReserveService;

@Controller
public class Search {
    
    @Autowired
    ReserveService reserveService;
    
	@RequestMapping(value="/reserve/search", method=RequestMethod.GET)
	public String search(HttpServletRequest request, Pet_sitter_houseDto pet_sitter_houseDto) {
	  
	    String[] tags = {"아파트", "마당", "환자견케어", "자격증보유", "실외배변", "반려견 없는 곳", "픽업가능", "수제간식", "응급처치", "투약가능"};
	    String[] tags_name = {"care_space", "yard", "patient_dog", "license", "outdoor_bowels", "other_animal", "pick_up", "homemade_snacks", "first_aid", "medication_possible"};
	    String[] tags_value = {"아파트", "있습니다.", "yes", "yes", "yes", "다른 동물은 없습니다.", "yes", "yes", "yes", "yes"};
	    
	    
	    ReservationDto reservationDto = new ReservationDto();
	    try {
	    Date start_date = Date.valueOf("2018-05-05");  // 예약날짜 넣기
	    Date end_date = Date.valueOf("2018-05-20");
	    reservationDto.setStart_day(start_date);
        reservationDto.setEnd_day(end_date);
	    } catch(Exception e) {}
	    
	    // ajax 로 할지 어쩔지 고민
	    
	    Map<String, Object> map = reserveService.getPet_sitter_houseList(reservationDto, pet_sitter_houseDto);
	    
	    request.setAttribute("pet_sitter_houseList", map.get("pet_sitter_houseList"));
	    request.setAttribute("room_photoList", map.get("room_photoList"));
	    request.setAttribute("user_profileList", map.get("user_profileList"));
	    
	    request.setAttribute("tags_name", tags_name);
	    request.setAttribute("tags_value", tags_value);
	    request.setAttribute("tags", tags);
		return "reserve/search";
	}
	
}
