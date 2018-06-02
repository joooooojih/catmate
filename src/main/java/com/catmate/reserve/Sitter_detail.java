package com.catmate.reserve;

import java.io.IOException;
import java.sql.Date;
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
import com.catmate.dto.ReviewDto;
import com.catmate.dto.Room_photoDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;
import com.catmate.reserve.service.ReserveService;

@Controller
public class Sitter_detail {

    @Autowired
    ReserveService reserveService;
    @Autowired
    MypageService mypageService;

    @RequestMapping(value="reserve/sitter_detail", method=RequestMethod.GET)
    public String sitter_detail(HttpServletRequest request) {
        String tmp_idx = (String)request.getParameter("idx");
        List<String> area_textList = new ArrayList<String>();
        area_textList.add("전체");
        area_textList.add("서울");
        area_textList.add("인천");
        area_textList.add("경기");
        area_textList.add("부산");
        
        List<Integer> area_countList = new ArrayList<Integer>();
        for(String area_text : area_textList) {
            area_countList.add(reserveService.getAreaCount(area_text));
        }
        
        String return_text = "redirect:search";
        if(tmp_idx != null) {
            int idx = Integer.parseInt(tmp_idx);
            Pet_sitter_houseDto pet_sitter_houseDto = reserveService.getPet_sitter_house(idx);
            List<Room_photoDto> room_photoList = reserveService.getRoom_photo(idx);
            User_profileDto house_user_profile = mypageService.getUser_profile(pet_sitter_houseDto.getUser_email());

            if(pet_sitter_houseDto.getSregister().equals("yes")) {
                List<ReservationDto> reservationList = reserveService.getReservation(idx);
                List<ReviewDto> reviewList = reserveService.getReviewList(idx);
                int review_count = reserveService.getReviewCount(idx);
                List<String> disable_dayList = new ArrayList<String>();
                List<User_profileDto> review_user_profileList = new ArrayList<User_profileDto>();
                
                for(ReviewDto reviewDto : reviewList) {
                    review_user_profileList.add(mypageService.getUser_profile(reviewDto.getUser_email()));
                }

                for(ReservationDto reservationDto : reservationList) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    Calendar start_cal = Calendar.getInstance();
                    Calendar end_cal = Calendar.getInstance();

                    start_cal.setTime(reservationDto.getStart_day());
                    end_cal.setTime(reservationDto.getEnd_day());

                    while(start_cal.compareTo(end_cal) != 1) {
                        disable_dayList.add(sdf.format(start_cal.getTime()));
                        start_cal.add(Calendar.DATE, 1);
                    }
                }
                request.setAttribute("area_text", area_textList);
                request.setAttribute("area_count", area_countList);
                
                request.setAttribute("pet_sitter_house", pet_sitter_houseDto);
                request.setAttribute("room_photoList", room_photoList);
                request.setAttribute("house_user_profile", house_user_profile);
                request.setAttribute("disable_dayList", disable_dayList);
                
                request.setAttribute("reviewList", reviewList);
                request.setAttribute("review_count", review_count);
                request.setAttribute("review_user_profileList", review_user_profileList);
                
                
                return_text = "reserve/sitter_detail";
            }
        }
        return return_text;
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
        Pet_sitter_houseDto pet_sitter_houseDto =  reserveService.getPet_sitter_house(reservationDto.getIdx());

        Calendar start_cal = Calendar.getInstance();
        Calendar end_cal = Calendar.getInstance();

        start_cal.setTime(Date.valueOf(request.getParameter("start_date")));
        end_cal.setTime(Date.valueOf(request.getParameter("end_date")));

        while(start_cal.compareTo(end_cal) != 1) {  // 며칠 인지 구함
            ++day;
            start_cal.add(Calendar.DATE, 1);
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
