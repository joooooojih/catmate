package com.catmate.mypage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catmate.dto.Pet_profileDto;
import com.catmate.dto.User_profileDto;
import com.catmate.mypage.service.MypageService;

@Controller
public class Pet_profile {

    @Autowired
    MypageService mypageService;
    
    @RequestMapping(value="/mypage/pet_profile/pet_profile", method=RequestMethod.GET)
    public String pet_profile (HttpServletRequest request, HttpSession session) {
        
        request.setAttribute("pet_profileList", mypageService.getPet_profileList((User_profileDto) session.getAttribute("user_profile")));
        
        return "mypage/pet_profile/pet_profile";
    }
    
    @RequestMapping(value="/mypage/pet_profile/pet_profile_reg", method=RequestMethod.GET)
    public String pet_profile_reg (HttpServletRequest request, HttpSession session, Pet_profileDto pet_profileDto) {
        
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        
        if(pet_profileDto.getIdx() != 0) {
            pet_profileDto.setUser_email(user_profileDto.getUser_email());
            request.setAttribute("pet_profile", mypageService.getPet_profile(pet_profileDto));
            
            request.setAttribute("root", "update_pet_profile_reg");
        } else {
            request.setAttribute("root", "insert_pet_profile_reg");
        }
        
        List<String> titleList = new ArrayList<String>();
        titleList.add("중성화 수술 여부");
        titleList.add("다른 개와 친화적인가요?");
        titleList.add("잘 짖습니까?");
        titleList.add("앓고 있는 지병이나 이전에 다쳤던 적이 있나요?");
        titleList.add("당신의 반려견은 대소변을 잘 가리는 편입니까?");
        titleList.add("반려견은 실내에서 마킹(영역표시)를 하나요?");
        titleList.add("평소 분리불안증이 있는 편인가요?");
        titleList.add("(심장사상충)약을 복용하였습니까? (월 1회/미 복용시 산책 금지)");
        titleList.add("(종합 7종 백신)접종을 하였습니까? (연 1회)");
        titleList.add("(코로나 장염)접종을 하였습니까? (연 1회)");
        titleList.add("(켄넬코프)접종을 하였습니까? (연 1회)");
        
        List<String> nameList = new ArrayList<String>();
        nameList.add("neutralization");
        nameList.add("friendly");
        nameList.add("barks");
        nameList.add("disease");
        nameList.add("feces");
        nameList.add("mark");
        nameList.add("separation_anxiety");
        nameList.add("heartworm");
        nameList.add("vaccine");
        nameList.add("corona_enteritis");
        nameList.add("kennelkov");
        
        request.setAttribute("titleList", titleList);
        request.setAttribute("nameList", nameList);
        
        return "mypage/pet_profile/pet_profile_reg";
    }
    
    // insert
    @RequestMapping(value="/mypage/pet_profile/insert_pet_profile_reg", method=RequestMethod.POST)
    public String insert_pet_profile_reg (HttpServletRequest request, HttpSession session, Pet_profileDto pet_profileDto) {
        String savePath = request.getServletContext().getRealPath("\\resources\\img\\dog_img\\");
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        
        pet_profileDto.setUser_email(user_profileDto.getUser_email());
        if(!pet_profileDto.getUploadFile().isEmpty()) {  // 파일을 바꿨다면
            
            String fileName = System.currentTimeMillis() + pet_profileDto.getUploadFile().getOriginalFilename();
            try {
                pet_profileDto.getUploadFile().transferTo(new File(savePath, fileName));
                pet_profileDto.setPet_photo(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {  // 파일을 바꾸지 않았다면
            pet_profileDto.setPet_photo("base.png");
        }
        mypageService.insertPet_profile(pet_profileDto);
        
        return "redirect:pet_profile";
    }
    
    // update
    @RequestMapping(value="/mypage/pet_profile/update_pet_profile_reg", method=RequestMethod.POST)
    public String update_pet_profile_regPost (HttpServletRequest request, HttpSession session, Pet_profileDto updatePet_profileDto) {
        
        String savePath = request.getServletContext().getRealPath("\\resources\\img\\dog_img\\");
        System.out.println(savePath);
        User_profileDto user_profileDto = (User_profileDto) session.getAttribute("user_profile");
        updatePet_profileDto.setUser_email(user_profileDto.getUser_email());
        
        Pet_profileDto pet_profileDto = mypageService.getPet_profile(updatePet_profileDto);
        if(!updatePet_profileDto.getUploadFile().isEmpty()) {  // 파일을 바꿨다면
            
            String fileName = System.currentTimeMillis() + updatePet_profileDto.getUploadFile().getOriginalFilename();
            
            try {
                updatePet_profileDto.getUploadFile().transferTo(new File(savePath, fileName));
                updatePet_profileDto.setPet_photo(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if(!pet_profileDto.getPet_photo().equals("base.png")) {  // 기존 사진 삭제
                File delFile = new File(savePath, pet_profileDto.getPet_photo());
                delFile.delete();
            }
            
        } else {  // 파일을 바꾸지 않았다면
            updatePet_profileDto.setPet_photo(pet_profileDto.getPet_photo());
        }
        mypageService.updatePet_profile(updatePet_profileDto);
        
        return "redirect:pet_profile";
    }
}
