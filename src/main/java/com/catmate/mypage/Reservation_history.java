package com.catmate.mypage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;
import com.catmate.reserve.service.ReserveService;

@Controller
public class Reservation_history {

    @Autowired
    MypageService mypageService;
    @Autowired
    ReserveService reserveService;

    @RequestMapping(value="/mypage/reservation_history/to_sitter", method=RequestMethod.GET)
    public String to_sitter(HttpSession session, HttpServletRequest request) {

        List<ReservationDto> reservationList = mypageService.getReservation_mypageList((User_profileDto) session.getAttribute("user_profile"));
        List<Pet_sitter_houseDto> pet_sitter_houseList = new ArrayList<Pet_sitter_houseDto>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> start_day = new ArrayList<String>();
        List<String> end_day = new ArrayList<String>();
        for(ReservationDto reservationDto : reservationList) {
            start_day.add(sdf.format(reservationDto.getStart_day()));
            end_day.add(sdf.format(reservationDto.getEnd_day()));

            pet_sitter_houseList.add(reserveService.getPet_sitter_house(reservationDto.getIdx()));
        }
        request.setAttribute("pet_sitter_houseList", pet_sitter_houseList);
        request.setAttribute("reservationList", reservationList);
        request.setAttribute("start_day", start_day);
        request.setAttribute("end_day", end_day);
        return "mypage/reservation_history/to_sitter";
    }

    @RequestMapping(value="/mypage/reservation_history/from_sitter", method=RequestMethod.GET)
    public String from_sitter(HttpSession session, HttpServletRequest request) {

        List<Pet_sitter_houseDto> pet_sitter_houseList = mypageService.getMy_pet_sitter_houseList((User_profileDto) session.getAttribute("user_profile"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        if(!pet_sitter_houseList.isEmpty()) {
            List<ReservationDto> reservationList = mypageService.getMy_reservationList(pet_sitter_houseList);
            List<User_profileDto> user_profileList = new ArrayList<User_profileDto>();
            List<Pet_sitter_houseDto> asc_pet_sitter_houseList = new ArrayList<Pet_sitter_houseDto>();
            List<String> start_dayList = new ArrayList<String>();
            List<String> end_dayList = new ArrayList<String>();
            if(!reservationList.isEmpty()) {
                for(ReservationDto reservationDto : reservationList) {
                    user_profileList.add(mypageService.getUser_profile(reservationDto.getUser_email()));
                    start_dayList.add(sdf.format(reservationDto.getStart_day()));
                    end_dayList.add(sdf.format(reservationDto.getEnd_day()));
                    
                    for(Pet_sitter_houseDto pet_sitter_houseDto : pet_sitter_houseList) {
                        if(reservationDto.getIdx() == pet_sitter_houseDto.getIdx()) {
                            asc_pet_sitter_houseList.add(pet_sitter_houseDto);
                        }
                    }
                }
            }
            request.setAttribute("reservationList", reservationList);
            request.setAttribute("user_profileList", user_profileList);
            request.setAttribute("pet_sitter_houseList", asc_pet_sitter_houseList);
            request.setAttribute("start_dayList", start_dayList);
            request.setAttribute("end_dayList", end_dayList);
            
        }  // if
        

        return "mypage/reservation_history/from_sitter";
    }

}
