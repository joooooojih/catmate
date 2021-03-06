package com.catmate.sregister;

import java.io.File;
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
import com.catmate.mypage.service.MypageService;
import com.catmate.reserve.service.ReserveService;
import com.catmate.sregister.service.SregisterService;

@Controller
public class Pet_sitter {

    @Autowired
    SregisterService sregisterService;
    @Autowired
    ReserveService reserveService;
    @Autowired
    MypageService mypageService;

    /*제목, 집 주소*/
    @RequestMapping(value="/sregister/pet_sitter01", method=RequestMethod.GET)
    public String pet_sitter01(HttpSession session, HttpServletRequest request) {
        return "sregister/pet_sitter01";
    }

    @RequestMapping(value="/sregister/pet_sitter01", method=RequestMethod.POST)
    public String pet_sitter01(HttpServletRequest request, HttpSession session, Pet_sitter_houseDto pet_sitter_houseDto) {
        
        int idx = (Integer) session.getAttribute("idx");
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

        pet_sitter_houseDto.setIdx( (Integer) session.getAttribute("idx") );
        pet_sitter_houseDto.setCheck_in(string_check_in);
        pet_sitter_houseDto.setCheck_out(string_check_out);

        sregisterService.updatePet_sitter02(pet_sitter_houseDto);

        return "redirect:pet_sitter03";
    }

    /*돌봄 환경, 태그*/
    @RequestMapping(value="/sregister/pet_sitter03", method=RequestMethod.GET)
    public String pet_sitter03(HttpServletRequest request, HttpSession session) {

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
        
        if(pet_sitter_houseDto.getPatient_dog() == null) {
            pet_sitter_houseDto.setPatient_dog("no");
        }
        if(pet_sitter_houseDto.getLicense() == null) {
            pet_sitter_houseDto.setLicense("no");
        }
        if(pet_sitter_houseDto.getOutdoor_bowels() == null) {
            pet_sitter_houseDto.setOutdoor_bowels("no");
        }
        if(pet_sitter_houseDto.getHomemade_snacks() == null) {
            pet_sitter_houseDto.setHomemade_snacks("no");
        }
        if(pet_sitter_houseDto.getFirst_aid() == null) {
            pet_sitter_houseDto.setFirst_aid("no");
        }
        if(pet_sitter_houseDto.getMedication_possible() == null) {
            pet_sitter_houseDto.setMedication_possible("no");
        }
        
        pet_sitter_houseDto.setCare_age(string_care_age);
        pet_sitter_houseDto.setIdx((Integer) session.getAttribute("idx"));

        sregisterService.updatePet_sitter03(pet_sitter_houseDto);

        return "redirect:pet_sitter04";
    }

    /*사진 업로드*/
    @RequestMapping(value="/sregister/pet_sitter04", method=RequestMethod.GET)
    public String pet_sitter04(HttpServletRequest request, HttpSession session) {

        List<Room_photoDto> room_photoList = (List<Room_photoDto>) request.getAttribute("room_photoList");
        if(room_photoList.isEmpty()) {
            request.setAttribute("submit_check", false);
        } else {
            request.setAttribute("submit_check", true);
        }
        return "sregister/pet_sitter04";
    }

    @RequestMapping(value="/sregister/pet_sitter04", method=RequestMethod.POST)
    public String pet_sitter04(HttpServletRequest request, HttpSession session, Room_photoDto room_photoDto) {
        String savePath = request.getServletContext().getRealPath("\\resources\\img\\pet_sitter_img\\");
        room_photoDto.setIdx((Integer) session.getAttribute("idx"));

        List<Room_photoDto> room_photoList = (List<Room_photoDto>) request.getAttribute("room_photoList");

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
        int idx = (Integer) session.getAttribute("idx");
        Pet_sitter_houseDto pet_sitter_houseDto = (Pet_sitter_houseDto) request.getAttribute("pet_sitter_house");
        User_profileDto pet_sitter_user_profile = mypageService.getUser_profile(pet_sitter_houseDto.getUser_email());
        
        if(pet_sitter_houseDto.getSregister().equals("yes")) {
            session.setAttribute("idx", 0);
            return "redirect:/reserve/sitter_detail?idx=" + idx;
        } else {
            request.setAttribute("house_user_profile", pet_sitter_user_profile);
            return "reserve/sitter_detail";
        }
        
    }

    @RequestMapping(value="/sregister/pet_sitter05", method=RequestMethod.POST)
    public String post_pet_sitter05(HttpServletRequest request, HttpSession session) {

        int idx = (Integer) session.getAttribute("idx");
        Pet_sitter_houseDto pet_sitter_houseDto = (Pet_sitter_houseDto) request.getAttribute("pet_sitter_house");
        pet_sitter_houseDto.setSregister("yes");
        sregisterService.updatePet_sitter05(pet_sitter_houseDto);
        
        session.setAttribute("idx", null);
        return "redirect:/reserve/sitter_detail?idx=" + idx;
    }
}
