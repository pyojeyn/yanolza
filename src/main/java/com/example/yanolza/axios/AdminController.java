package com.example.yanolza.axios;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AllArgsConstructor
@Controller
@RequestMapping("/adminindex")
public class AdminController {

    //메인
    @GetMapping("/")
    public String index() { return  "admin/index"; }

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @GetMapping("/index")
    public String index2() {
        return "admin/index";
    }
    //1 - 사이드바 회원관리 전체회원관리
    @GetMapping("/all-staff")
    public String allstaff() {
        return "admin/all-staff";
    }
    //1 - 사이드바 회원관리 탈퇴회원관리
    @GetMapping("/all-staff_cancle")
    public String allstaffcancle() {
        return "admin/all-staff_cancle";
    }
    //2 - 1 사이드바 호스트관리 전체 호스트관리
    @GetMapping("/all-host")
    public String allhost() {
        return "admin/all-host";
    }
    //2 - 2 사이드바 호스트관리 숙소관리
    @GetMapping("/roommanager")
    public String roommanager() {
        return "admin/roommanager";
    }
    //2 - 3 사이드바 호스트관리 리뷰관리 /reviewmanagement.html
    @GetMapping("/reviewmanagement")
    public String reviewmanagement() {
        return "admin/reviewmanagement";
    }
    //3 - 사이드바 예약/결제내역 예약/결제내역
    @GetMapping("/reservationpayment")
    public String reservationpayment() {
        return "admin/reservationpayment";
    }
    //3 - 1 사이드바 예약/취소내역 취소내역
    @GetMapping("/reservationcancle")
    public String reservationcancle() {
        return "admin/reservationcancle";
    }
    //3 - 2v 예약/결제 취소내역 닉네임 클릭
    @GetMapping("/cancleinformation")
    public String cancleinformation() {
        return "admin/cancleinformation";
    }

    //3 - 2 - 1v 예약/결제 취소내역 닉네임 클릭
    @RequestMapping(value="/cancleinformation/{id}", method= RequestMethod.GET)
    public String cancleinformation2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "/admin/cancleinformation";
    }

    //4 - 사이드바 문의관리 1:1문의관리
    @GetMapping("/qustion")
    public String qustion() {
        return "admin/qustion";
    }
    //5 - 사이드바 결제관리 결제시스템관리
    @GetMapping("/payments")
    public String payments() {
        return "admin/payments";
    }

    //1 - v 전체 회원관리 닉네임 클릭
    @GetMapping("/customerinformation")
    public String customerinformation() {
        return "admin/customerinformation";
    }

    //1 - v 전체 회원관리 닉네임 클릭 글보기 페이지
    @RequestMapping(value="/customerinformation/{id}", method= RequestMethod.GET)
    public String customerinformationDetail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "/admin/customerinformation";
    }

    //2 - 1v 전체 호스트관리 호스트 정보(이름) 클릭 roomInformation
    @GetMapping("/roomInformation")
    public String roomInformation() {
        return "admin/roomInformation";
    }

    //2 - 1v 전체 호스트관리 호스트 정보(이름) 클릭 글보기 페이지 roomInformation 뭐들지
    @RequestMapping(value="/roomInformation/{id}", method= RequestMethod.GET)
    public String roomInformation(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "/admin/roomInformation";
    }

    // 2-1v 리뷰 사진
    @GetMapping("/roomphoto")
    public String roomphoto() {
        return "admin/roomphoto";
    }

    //2 - 1v  리뷰 사진 닉네임 클릭
    @RequestMapping(value="/roomphoto/{id}", method= RequestMethod.GET)
    public String roomphoto2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "/admin/roomphoto";
    }
    @GetMapping("/roomdetails")
    public String roomdetails() {
        return "admin/roomdetails";
    }

    //2 - 1v 전체 호스트관리 호스트 정보(이름) 클릭 글보기 페이지에서 숙소정보보기
    @RequestMapping(value="/roomdetails/{id}/{tbMemId}", method= RequestMethod.GET)
    public String roomdetails(@PathVariable("id") Integer id,@PathVariable("tbMemId") Integer tbMemId, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("tbMemId",tbMemId);
        return "/admin/roomdetails";
    }
    @GetMapping("/roomdetails_n")
    public String roomdetails_n() {
        return "admin/roomdetails_n";
    }

    //2 - 1v 전체 호스트관리 호스트 정보(이름) 클릭 글보기 페이지에서 숙소정보보기
    @RequestMapping(value="/roomdetails_n/{id}/{tbMemId}", method= RequestMethod.GET)
    public String roomdetails_n(@PathVariable("id") Integer id,@PathVariable("tbMemId") Integer tbMemId, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("tbMemId",tbMemId);
        return "/admin/roomdetails_n";
    }


    //2 - 2v 숙소관리 호스트 정보(이름) 클릭/roomregistration/{id?}
    @GetMapping("/roomregistration")
    public String roomregistration() {
        return "/admin/roomregistration";
    }

    //2 - 2v 숙소관리 호스트 정보(이름) 클릭/roomregistration/{id?}
    @RequestMapping(value="/roomregistration/{id}/{tbMemId}", method= RequestMethod.GET)
    public String roomregistration(@PathVariable("id") Integer id,@PathVariable("tbMemId") Integer tbMemId,  Model model) {
        model.addAttribute("id", id);
        model.addAttribute("tbMemId",tbMemId);
        return "/admin/roomregistration";
    }

    //2 - 2vv 숙소관리 호스트 정보(이름) 숙소사진, 승인하기
    @GetMapping("/gallery")
    public String gallery() {
        return "admin/gallery";
    }

    //2 - 2vv 숙소관리 호스트 정보(이름) 숙소사진 host id
    @RequestMapping(value="/gallery/{id}", method= RequestMethod.GET)
    public String roomregistration(@PathVariable("id") Integer id,  Model model) {
        model.addAttribute("id", id);
        return "/admin/gallery";
    }

    //회원정보 edit
    @GetMapping("/edit-staff")
    public String editstaff() {
        return "admin/edit-staff";
    }

    //회원정보 delete

    //2 - 3v 리뷰관리 닉네임 클릭
    @GetMapping("/reviewdetails")
    public String reviewdetails() {
        return "admin/reviewdetails";
    }


    //2 - 3v 리뷰관리 닉네임 클릭
    @RequestMapping(value="/reviewdetails/{id}", method= RequestMethod.GET)
    public String reviewdetails(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "/admin/reviewdetails";
    }

    // 2-1v 리뷰 사진
    @GetMapping("/reviewphoto")
    public String reviewphoto() {
        return "admin/reviewphoto";
    }


    //2 - 1v  리뷰 사진 닉네임 클릭
    @RequestMapping(value="/reviewphoto/{id}", method= RequestMethod.GET)
    public String reviewphoto2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "/admin/reviewphoto";
    }

    //3 - v 예약/결제내역 닉네임 클릭
    @GetMapping("/paymentinformation")
    public String paymentinformation() {
        return "admin/paymentinformation";
    }

    //3 - v 예약/결제내역 닉네임 클릭
    @RequestMapping(value="/paymentinformation/{id}", method= RequestMethod.GET)
    public String paymentinformation2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "/admin/paymentinformation";
    }

    //4 - v  1:1문의관리 닉네임 클릭
    @GetMapping("/qustionprofile")
    public String qustionprofile() {
        return "admin/qustionprofile";
    }


    //4 - v  1:1문의관리 닉네임 클릭 que id
    @RequestMapping(value="/qustionprofile/{id}", method= RequestMethod.GET)
    public String qustionprofile_2(@PathVariable("id") Integer id,  Model model) {
        model.addAttribute("id", id);
        return "/admin/qustionprofile";
    }
}
