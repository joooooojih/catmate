package com.catmate.reserve;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
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

        ReservationDto reservationDto = new ReservationDto();

        Map<String, Object> map = reserveService.getPet_sitter_houseList(reservationDto, pet_sitter_houseDto);

        request.setAttribute("pet_sitter_houseList", map.get("pet_sitter_houseList"));
        request.setAttribute("room_photoList", map.get("room_photoList"));
        request.setAttribute("user_profileList", map.get("user_profileList"));

        request.setAttribute("tags_name", tags_name);
        request.setAttribute("tags", tags);
        return "reserve/search";
    }

    @RequestMapping(value="/reserve/search_ajax", method=RequestMethod.GET)
    public void search(HttpServletRequest request, HttpServletResponse response, Pet_sitter_houseDto pet_sitter_houseDto) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        ReservationDto reservationDto = new ReservationDto();
        try {
            Date start_date = Date.valueOf(request.getParameter("start_date"));  // 예약날짜 넣기
            Date end_date = Date.valueOf(request.getParameter("end_date"));
            reservationDto.setStart_day(start_date);
            reservationDto.setEnd_day(end_date);
        } catch(Exception e) {}

        /*System.out.println("start "+reservationDto.getStart_day());
        System.out.println("end "+reservationDto.getEnd_day());
        System.out.println("age "+pet_sitter_houseDto.getCare_age());
        System.out.println("size "+pet_sitter_houseDto.getCare_size());
        System.out.println("space "+pet_sitter_houseDto.getCare_space());
        System.out.println("수제 "+pet_sitter_houseDto.getHomemade_snacks());
        System.out.println("getLicense "+pet_sitter_houseDto.getLicense());
        System.out.println("getMedication_possible "+pet_sitter_houseDto.getMedication_possible());
        System.out.println("getFirst_aid "+pet_sitter_houseDto.getFirst_aid());
        System.out.println("getPick_up "+pet_sitter_houseDto.getPick_up());
        System.out.println("getYard "+pet_sitter_houseDto.getYard());
        System.out.println("getOther_animal "+pet_sitter_houseDto.getOther_animal());
        System.out.println("getOutdoor_bowels "+pet_sitter_houseDto.getOutdoor_bowels());
        System.out.println("dog " + pet_sitter_houseDto.getPatient_dog());*/
        
        Map<String, Object> map = reserveService.getPet_sitter_houseList(reservationDto, pet_sitter_houseDto);
        ObjectMapper mapper = new ObjectMapper();
        
        String jsonText = "";
        
        List<Pet_sitter_houseDto> list = (List<Pet_sitter_houseDto>) map.get("pet_sitter_houseList");
        jsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map); 
        
        response.getWriter().print(jsonText);
    }

}
