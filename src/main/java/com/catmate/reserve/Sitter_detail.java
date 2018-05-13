package com.catmate.reserve;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.User_profileDto;
import com.catmate.reserve.service.ReserveService;

@Controller
public class Sitter_detail {

    @Autowired
    ReserveService reserveService;
    
    @RequestMapping(value="reserve/sitter_detail", method=RequestMethod.GET)
    public String sitter_detail(HttpServletRequest request) {
        
        String idx = (String)request.getParameter("idx");
        if(idx != null) {
            
            Map<String, Object> map = reserveService.getPet_sitter_house(Integer.parseInt(idx));
            List<ReservationDto> reservationList = reserveService.getReservation(Integer.parseInt(idx));
            List<String> disable_dayList = new ArrayList<String>();
            
            for(ReservationDto reservationDto : reservationList) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                
                c1.setTime(reservationDto.getStart_day());
                c2.setTime(reservationDto.getEnd_day());
                
                while(c1.compareTo(c2) != 1) {
                    disable_dayList.add(sdf.format(c1.getTime()));
                    c1.add(Calendar.DATE, 1);
                }
            }
            
            request.setAttribute("pet_sitter_house", map.get("pet_sitter_house"));
            request.setAttribute("room_photoList", map.get("room_photo"));
            request.setAttribute("house_user_profile", map.get("user_profile"));
            request.setAttribute("disable_dayList", disable_dayList);
            
            // 날짜 disable 걸어야됨
        }
        
        
        return "reserve/sitter_detail";
    }
    
    // insert reservation
    @RequestMapping(value="reserve/sitter_detail", method=RequestMethod.POST)
    public void sitter_detail(HttpServletRequest request, HttpSession session, ReservationDto reservationDto) {
        
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        reservationDto.setUser_email(user_profileDto.getUser_email());
        
        reservationDto.setStart_day(Date.valueOf(request.getParameter("start_date")));
        reservationDto.setEnd_day(Date.valueOf(request.getParameter("end_date")));
        
        reserveService.insertReservation(reservationDto);
        
    }
    
    // 유효성 검사
    @RequestMapping(value="reserve/sitter_detail_price_check", method=RequestMethod.GET)
    public void sitter_detail_price_check(HttpServletRequest request, HttpServletResponse response, ReservationDto reservationDto) throws IOException {
        
        int day = -1;
        int total_price = 0;
        int excess_amount = 0;
        Map<String, Object> map = reserveService.getPet_sitter_house(reservationDto.getIdx());
        Pet_sitter_houseDto pet_sitter_houseDto =  (Pet_sitter_houseDto) map.get("pet_sitter_house");
        
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        
        c1.setTime(Date.valueOf(request.getParameter("start_date")));
        c2.setTime(Date.valueOf(request.getParameter("end_date")));
        
        while(c1.compareTo(c2) != 1) {  // 며칠 인지 구함
            day++;
            c1.add(Calendar.DATE, 1);
        }
        
        if(day == 0) { // 데이케어
            excess_amount = pet_sitter_houseDto.getDay_care() + (pet_sitter_houseDto.getSurcharge() * reservationDto.getHow_many());
        } else {
            excess_amount = (pet_sitter_houseDto.getNightly_rate() * day) + (pet_sitter_houseDto.getSurcharge() * reservationDto.getHow_many() * day);
        }
        total_price = excess_amount + excess_amount / 10;
        response.getWriter().print(total_price);
        
    }
}
