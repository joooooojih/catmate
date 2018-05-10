package com.catmate.sregister;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.catmate.dto.Pet_sitter_houseDto;
import com.catmate.dto.Room_photoDto;
import com.catmate.dto.User_profileDto;
import com.catmate.member.service.MemberService;
import com.catmate.reserve.service.ReserveService;
import com.catmate.sregister.service.SregisterService;

@Controller
public class Pet_sitter {

    @Autowired
    SregisterService sregisterService;
    @Autowired
    ReserveService reserveService;
    @Autowired
    MemberService memberService;

    public void idx_check(HttpSession session, HttpServletRequest request) {
        int idx;
        try {
            idx = (int) session.getAttribute("idx");
        } catch(Exception e) {
            idx = 0;
            session.setAttribute("idx", 0);
        }
        if(idx != 0) {
            Map<String, Object> map = reserveService.getPet_sitter_house(idx);

            request.setAttribute("pet_sitter_house", map.get("pet_sitter_house"));
            request.setAttribute("room_photo", map.get("room_photo"));

        }
    }

    /*제목, 집 주소*/
    @RequestMapping(value="/sregister/pet_sitter01", method=RequestMethod.GET)
    public String pet_sitter01(HttpSession session, HttpServletRequest request) {

        idx_check(session, request);

        return "sregister/pet_sitter01";
    }

    @RequestMapping(value="/sregister/pet_sitter01", method=RequestMethod.POST)
    public String pet_sitter01(HttpServletRequest request, HttpSession session, Pet_sitter_houseDto pet_sitter_houseDto) {

        int idx = (int) session.getAttribute("idx");
        if(idx == 0) {
            User_profileDto user_profile = (User_profileDto) session.getAttribute("user_profile");
            pet_sitter_houseDto.setUser_email(user_profile.getUser_email());

            idx = sregisterService.insertPet_sitter01(pet_sitter_houseDto); // 등록

            session.setAttribute("idx", idx); // 방금 등록한 테이블의 idx값
        } else { // 수정
            pet_sitter_houseDto.setIdx(idx);
            sregisterService.updatePet_sitter01(pet_sitter_houseDto);
        }

        return "redirect:pet_sitter02";
    }

    /*체크인 체크아웃, 데이케어 1박 마리당 추가 금액*/
    @RequestMapping(value="/sregister/pet_sitter02", method=RequestMethod.GET)
    public String pet_sitter02(HttpSession session, HttpServletRequest request) {
        idx_check(session, request);

        Pet_sitter_houseDto pet_sitter_houseDto = (Pet_sitter_houseDto) request.getAttribute("pet_sitter_house");

        if(pet_sitter_houseDto.getCheck_in() != null && pet_sitter_houseDto.getCheck_out() != null) {
            String check_in = pet_sitter_houseDto.getCheck_in();
            String check_out = pet_sitter_houseDto.getCheck_out();

            String[] check_in_arr = check_in.split(" ~ ");
            String[] check_out_arr = check_out.split(" ~ ");

            request.setAttribute("check_in_start", check_in_arr[0].split(" : "));
            request.setAttribute("check_out_start", check_out_arr[0].split(" : "));
            request.setAttribute("check_in_end", check_in_arr[1].split(" : "));
            request.setAttribute("check_out_end", check_out_arr[1].split(" : "));
        }

        return "sregister/pet_sitter02";
    }

    @RequestMapping(value="/sregister/pet_sitter02", method=RequestMethod.POST)
    public String pet_sitter02(HttpServletRequest request, HttpSession session, Pet_sitter_houseDto pet_sitter_houseDto) {

        String[] check_in = request.getParameterValues("check_in");
        String[] check_out = request.getParameterValues("check_out");

        String string_check_in = "";
        String string_check_out = "";

        string_check_in += check_in[0];
        string_check_in += " : ";
        string_check_in += check_in[1];
        string_check_in += " ~ ";
        string_check_in += check_in[2];
        string_check_in += " : ";
        string_check_in += check_in[3];

        string_check_out += check_out[0];
        string_check_out += " : ";
        string_check_out += check_out[1];
        string_check_out += " ~ ";
        string_check_out += check_out[2];
        string_check_out += " : ";
        string_check_out += check_out[3];

        pet_sitter_houseDto.setIdx((int) session.getAttribute("idx"));
        pet_sitter_houseDto.setCheck_in(string_check_in);
        pet_sitter_houseDto.setCheck_out(string_check_out);

        sregisterService.updatePet_sitter02(pet_sitter_houseDto);

        return "redirect:pet_sitter03";
    }

    /*돌봄 환경, 태그*/
    @RequestMapping(value="/sregister/pet_sitter03", method=RequestMethod.GET)
    public String pet_sitter03(HttpServletRequest request, HttpSession session) {

        idx_check(session, request);
        Pet_sitter_houseDto pet_sitter_houseDto = (Pet_sitter_houseDto) request.getAttribute("pet_sitter_house");
        if(pet_sitter_houseDto.getCare_age() != null) {
            String[] care_age = pet_sitter_houseDto.getCare_age().split(", ");
            request.setAttribute("care_age", care_age);
        }

        return "sregister/pet_sitter03";
    }

    @RequestMapping(value="/sregister/pet_sitter03", method=RequestMethod.POST)
    public String pet_sitter03(HttpServletRequest request, HttpSession session, Pet_sitter_houseDto pet_sitter_houseDto) {

        String[] care_age = request.getParameterValues("care_age");
        String string_care_age = "";
        for(int i = 0; i < care_age.length; i++) {
            string_care_age += care_age[i];
            if(i != care_age.length - 1) {
                string_care_age += ", ";
            }
        }
        pet_sitter_houseDto.setCare_age(string_care_age);
        pet_sitter_houseDto.setIdx((int) session.getAttribute("idx"));

        sregisterService.updatePet_sitter03(pet_sitter_houseDto);

        return "redirect:pet_sitter04";
    }

    /*사진 업로드*/
    @RequestMapping(value="/sregister/pet_sitter04", method=RequestMethod.GET)
    public String pet_sitter04(HttpServletRequest request, HttpSession session) {

        idx_check(session, request);

        List<Room_photoDto> room_photoList = (List<Room_photoDto>) request.getAttribute("room_photo");
        if(room_photoList.isEmpty()) {
            request.setAttribute("submit_check", false);
        } else {
            request.setAttribute("submit_check", true);
        }
        return "sregister/pet_sitter04";
    }

    @RequestMapping(value="/sregister/pet_sitter04", method=RequestMethod.POST)
    public String pet_sitter04(HttpServletRequest request, HttpSession session, Room_photoDto room_photoDto) {
        String savePath = request.getServletContext().getRealPath("\\resources\\pet_sitter\\img\\");
        room_photoDto.setIdx((int) session.getAttribute("idx"));

        idx_check(session, request);
        List<Room_photoDto> room_photoList = (List<Room_photoDto>) request.getAttribute("room_photo");

        System.out.println(savePath);
        for(int i = 0; i < room_photoDto.getUploadFile().size(); i++) {

            if(!room_photoDto.getUploadFile().get(i).isEmpty()) {  // 값을 변경 한 파일만
                // 파일 올리기
                MultipartFile uploadFile = room_photoDto.getUploadFile().get(i);
                String fileName = System.currentTimeMillis() + uploadFile.getOriginalFilename();  // 파일명 중복 방지
                room_photoDto.setRoom_photo(fileName);
                room_photoDto.setPhoto_sequence(i);
                try {
                    uploadFile.transferTo(new File(savePath, fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(room_photoList.isEmpty()) {  // DB insert
                    sregisterService.insertRoom_photo(room_photoDto);
                } else {  // DB update
                    File delFile = new File(savePath, room_photoList.get(i).getRoom_photo());
                    delFile.delete();
                    sregisterService.updateRoom_photo(room_photoDto);
                }
            }
        }

        return "redirect:pet_sitter05";
    }

    /*미리보기*/
    @RequestMapping(value="/sregister/pet_sitter05", method=RequestMethod.GET)
    public String pet_sitter05(HttpServletRequest request, HttpSession session) {

        idx_check(session, request);
        Pet_sitter_houseDto pet_sitter_houseDto = (Pet_sitter_houseDto) request.getAttribute("pet_sitter_house");
        User_profileDto pet_sitter_user_profile = memberService.getUser_profile(pet_sitter_houseDto.getUser_email());
        
        request.setAttribute("pet_sitter_user_profile", pet_sitter_user_profile);
        request.setAttribute("sregister", "sregister");
        return "reserve/sitter_detail";
    }

    @RequestMapping(value="/sregister/pet_sitter05", method=RequestMethod.POST)
    public String post_pet_sitter05(HttpServletRequest request, HttpSession session) {

        idx_check(session, request);
        Pet_sitter_houseDto pet_sitter_houseDto = (Pet_sitter_houseDto) request.getAttribute("pet_sitter_house");
        pet_sitter_houseDto.setSregister("yes");
        sregisterService.updatePet_sitter05(pet_sitter_houseDto);
        
        return "reserve/sitter_detail";
    }
}
