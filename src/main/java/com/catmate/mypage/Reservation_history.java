package com.catmate.mypage;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
        List<User_profileDto> user_profileList = new ArrayList<User_profileDto>();
        for(ReservationDto reservationDto : reservationList) {
            pet_sitter_houseList.add(reserveService.getPet_sitter_house(reservationDto.getIdx()));
        }
        for(Pet_sitter_houseDto pet_sitter_houseDto : pet_sitter_houseList) {
            user_profileList.add(mypageService.getUser_profile(pet_sitter_houseDto.getUser_email()));
        }
        request.setAttribute("user_profileList", user_profileList);
        request.setAttribute("pet_sitter_houseList", pet_sitter_houseList);
        request.setAttribute("reservationList", reservationList);
        return "mypage/reservation_history/to_sitter";
    }

    @RequestMapping(value="/mypage/reservation_history/delete_to_sitter", method=RequestMethod.POST)
    public void delete_to_sitter(HttpSession session, HttpServletRequest request, HttpServletResponse response, ReservationDto reservationDto) throws IOException {
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        reservationDto.setUser_email(user_profileDto.getUser_email());
        reservationDto.setStart_day(Date.valueOf(request.getParameter("start_date")));
        reservationDto.setEnd_day(Date.valueOf(request.getParameter("end_date")));

        mypageService.deleteReservation(reservationDto);

        response.getWriter().print(request.getParameter("num"));
    }

    @RequestMapping(value="/mypage/reservation_history/from_sitter", method=RequestMethod.GET)
    public String from_sitter(HttpSession session, HttpServletRequest request) {

        List<Pet_sitter_houseDto> pet_sitter_houseList = mypageService.getMy_pet_sitter_houseList((User_profileDto) session.getAttribute("user_profile"));

        if(!pet_sitter_houseList.isEmpty()) {
            List<ReservationDto> reservationList = mypageService.getMy_reservationList(pet_sitter_houseList);
            List<User_profileDto> user_profileList = new ArrayList<User_profileDto>();
            List<Pet_sitter_houseDto> asc_pet_sitter_houseList = new ArrayList<Pet_sitter_houseDto>();
            if(!reservationList.isEmpty()) {
                for(ReservationDto reservationDto : reservationList) {
                    user_profileList.add(mypageService.getUser_profile(reservationDto.getUser_email()));
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

        }  // if

        return "mypage/reservation_history/from_sitter";
    }

}
