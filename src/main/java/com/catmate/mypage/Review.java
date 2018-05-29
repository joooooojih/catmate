package com.catmate.mypage;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.ReservationDto;
import com.catmate.dto.ReviewDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;
import com.catmate.reserve.service.ReserveService;

@Controller
public class Review {

    @Autowired
    MypageService mypageService;
    @Autowired
    ReserveService reserveService;
    
    @RequestMapping(value="/mypage/review", method=RequestMethod.GET)
    public String review(HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes, ReservationDto reservationDto) {
        
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        reservationDto.setUser_email(user_profileDto.getUser_email());
        reservationDto.setStart_day(Date.valueOf(request.getParameter("start_date")));
        reservationDto.setEnd_day(Date.valueOf(request.getParameter("end_date")));
        
        redirectAttributes.addFlashAttribute("review_check", "yes");
        redirectAttributes.addFlashAttribute("reservation", reservationDto);
        return "redirect:/reserve/sitter_detail?idx="+reservationDto.getIdx();
    }
    
    @RequestMapping(value="/mypage/review", method=RequestMethod.POST)
    public String review(HttpSession session, HttpServletRequest request, ReviewDto reviewDto, ReservationDto reservationDto) {

        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        reviewDto.setUser_email(user_profileDto.getUser_email());
        
        reservationDto.setUser_email(user_profileDto.getUser_email());
        
        reservationDto.setStart_day(Date.valueOf(request.getParameter("start_date")));
        reservationDto.setEnd_day(Date.valueOf(request.getParameter("end_date")));
        
        Pet_sitter_houseDto pet_sitter_houseDto = reserveService.getPet_sitter_house(reviewDto.getIdx());
        User_profileDto house_user_profileDto = mypageService.getUser_profile(pet_sitter_houseDto.getUser_email());
        
        List<Pet_sitter_houseDto> pet_sitter_houseList = mypageService.getMy_pet_sitter_houseList(house_user_profileDto);
        List<ReviewDto> reviewList = mypageService.getMyReviewList(pet_sitter_houseList);
        
        int review_count = reviewList.size() + 1;
        int grade = 0;
        for(ReviewDto tmpReviewDto : reviewList) {
            grade += tmpReviewDto.getReview_scope();
        }
        grade += reviewDto.getReview_scope();
        house_user_profileDto.setUser_grade(grade / review_count);
        
        mypageService.insertReview(reservationDto, house_user_profileDto, reviewDto);
        return "redirect:/mypage/reservation_history/to_sitter";
    }
}
