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
@RequestMapping("/hosthousemain")
public class HostController {

    // 호스트 메인 페이지
    @GetMapping("/") 
    public String hosthousemain() {
        return "hosthtml/hosthousemain";
    }

    @GetMapping("/mypageregist")
    public String mypageregist() {
        return "hosthtml/mypageregist";
    }

    @RequestMapping(value="/mypageregist/{id}", method= RequestMethod.GET)
    public String mypageregist_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/mypageregist";
    }

    @GetMapping("/introwrite")
    public String introwrite() {
        return "hosthtml/introwrite";
    }

    @RequestMapping(value="/introwrite/{id}", method= RequestMethod.GET)
    public String introwrite_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/introwrite";
    }

    @GetMapping("/guestdetail")
    public String guestdetail() {
        return "hosthtml/guestdetail";
    }

    @RequestMapping(value="/guestdetail/{id}", method= RequestMethod.GET)
    public String guestdetail_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/guestdetail";
    }

    @GetMapping("/guestroom")
    public String guestroom() {
        return "hosthtml/guestroom";
    }

    @RequestMapping(value="/guestroom/{id}/{hostid}", method= RequestMethod.GET)
    public String guestroom_2(@PathVariable("id") Integer id,@PathVariable("hostid") Integer hostid, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("hostid", hostid);
        return "hosthtml/guestroom";
    }

    @GetMapping("/guestroomregist")
    public String guestroomregist() {
        return "hosthtml/guestroomregist";
    }

    @RequestMapping(value="/guestroomregist/{id}", method= RequestMethod.GET)
    public String guestroomregist_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/guestroomregist";
    }

    @GetMapping("/guestroomdetail")
    public String guestroomdetail() {
        return "hosthtml/guestroomdetail";
    }

    @RequestMapping(value="/guestroomdetail/{id}", method= RequestMethod.GET)
    public String guestroomdetail_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/guestroomdetail";
    }

    @GetMapping("/guestroomedit")
    public String guestroomedit() {
        return "hosthtml/guestroomedit";
    }

    @RequestMapping(value="/guestroomedit/{id}", method= RequestMethod.GET)
    public String guestroomedit_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/guestroomedit";
    }

    @GetMapping("/guestAllReview")
    public String guestAllReview() {
        return "hosthtml/guestAllReview";
    }

    @RequestMapping(value="/guestAllReview/{id}/{hostid}", method= RequestMethod.GET)
    public String guestAllReview_2(@PathVariable("id") Integer id, @PathVariable("hostid") Integer hostid,Model model) {
        model.addAttribute("id", id);
        model.addAttribute("hostid", hostid);
        return "hosthtml/guestAllReview";
    }

    @GetMapping("/guestReview")
    public String guestReview() {
        return "hosthtml/guestReview";
    }


    @RequestMapping(value="/guestReview/{id}/{hostid}", method= RequestMethod.GET)
    public String guestReview_2(@PathVariable("id") Integer id, @PathVariable("hostid") Integer hostid, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("hostid", hostid);
        return "hosthtml/guestReview";
    }

    @GetMapping("/guestReviewdetail")
    public String guestReviewdetail() {
        return "hosthtml/guestReviewdetail";
    }

    @RequestMapping(value="/guestReviewdetail/{id}", method= RequestMethod.GET)
    public String guestReviewdetail_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/guestReviewdetail";
    }

    @GetMapping("/scheduleManagement")
    public String scheduleManagement() {
        return "hosthtml/scheduleManagement";
    }

    @RequestMapping(value="/scheduleManagement/{id}", method= RequestMethod.GET)
    public String scheduleManagement_2(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "hosthtml/scheduleManagement";
    }

    @GetMapping("/book")
    public String book() {
        return "hosthtml/book";
    }

    @GetMapping("/scheduleManagement6")
    public String scheduleManagement6() {
        return "hosthtml/scheduleManagement6";
    }

    @GetMapping("/book2")
    public String book2() {
        return "hosthtml/book2";
    }

    @GetMapping("/scheduleManagement3")
    public String scheduleManagement3() {
        return "hosthtml/scheduleManagement3";
    }

    @GetMapping("/hostimg")
    public String hostimg() {
        return "hosthtml/hostimg";
    }


    @GetMapping("/hostimg_send")
    public String hostimg_send() {
        return "hosthtml/hostimg_send";
    }

}
