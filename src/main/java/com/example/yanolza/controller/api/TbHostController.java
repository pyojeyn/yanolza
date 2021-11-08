package com.example.yanolza.controller.api;

import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbHost;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbHostApiRequest;
import com.example.yanolza.model.network.response.*;
import com.example.yanolza.service.TbHostApiService;
import com.example.yanolza.service.TbHostServiceTest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
@RequiredArgsConstructor
public class TbHostController extends CrudController<TbHostApiRequest, TbHostApiResponse, TbHost> {
    private final TbHostApiService tbHostApiService;
    private final TbHostServiceTest tbHostServiceTest;

    //호스트 등록(host)
    @PostMapping("/hregist")
    public Header<TbHostApiResponse> hregist(@RequestBody Header<TbHostApiRequest> request) {
        System.out.println(request);
        return tbHostApiService.registh(request);
    }


    //호스트 디테일(admin)  Y 인애들 승인받은 애들
    @GetMapping("/hlist/{id}")
    public Header<TbHostApiResponse> gethost( @PathVariable(name = "id") Integer id){
        return tbHostApiService.gethostdetail(id);

    }
    // 결제창 호스트 비밀번호 받아오기
    @GetMapping("/hostHp/{id}")
    public String getAlm(@PathVariable(name = "id")Integer id){
        return tbHostApiService.getHP(id);
    }

    // ok
    // 호스트 등록 수정(host)1 전달인자에 꼭 id넣어주기
    @PatchMapping("/host1/{id}")
    public Header<TbHostApiResponse> update1(@PathVariable Integer id, @RequestBody Header<TbHostApiRequest> request) {
        return tbHostApiService.updateh1(id, request);
    }

    // 호스트 등록 수정(host)2
    @PatchMapping("/host2/{id}")
    public Header<TbHostApiResponse> update2(@PathVariable Integer id, @RequestBody Header<TbHostApiRequest> request) {
        return tbHostApiService.updateh2(id,request);
    }

    // 호스트 등록 수정(host)3
    @PatchMapping("/host3/{id}")
    public Header<TbHostApiResponse> update3( @PathVariable Integer id,@RequestBody Header<TbHostApiRequest> request) {
        return tbHostApiService.updateh3(id,request);
    }

    // 숙소신청리스트(admin) ok
    @GetMapping("/rhlist")
    public List<TbHostApiRequest> rhlsit(){
        return tbHostApiService.getrhlist();
    }

    // 숙소신청리스트 디테일(admin) ok n.
    @GetMapping("/rhlist/{id}")
    public Header<TbHostApiResponse> getrhdetail(@PathVariable(name = "id") Integer id){
        return tbHostApiService.getrhostdetail(id);
    }

    //호스트 승인하기 버튼
    @PatchMapping("/rhlist/{id}")
    public Header<TbHostApiResponse> admit(@PathVariable Integer id,@RequestBody Header<TbHostApiRequest> request){
        return tbHostApiService.admith(id, request);
    }

    //숙소신청리스트 삭제 ok
    @DeleteMapping("/rhlist/{id}")
    public Header<TbHostApiResponse> delrhost(@PathVariable(name = "id") Integer id){
        return tbHostApiService.rhostdel(id);
    }

    // 호스트 리스트(admin) (user 내 주변)
    @GetMapping("/hlist")
    public List<TbHostApiRequest> hlist(){
        return tbHostApiService.gethlist();
    }

    //원하는 숙소 클릭하면 나오는 페이지 데이터 review (user) 사진포함
    @GetMapping("/{id}/hostroomreview")
    public Header<TbHostTbRoomApiResponse> roomhostre(@PathVariable Integer id){
        return tbHostApiService.hostRoomreview(id);
    }
    // 호스트가 가지고 있는 룸의 예약정보들(host)
    @GetMapping("/{id}/hostroompay")  // 호스트가 가지고있는 룸 가져오는거 가능
    public Header<TbHostTbRoomApiResponse> roomHostp(@PathVariable Integer id){
        return tbHostApiService.hostRoompay(id);
    }
    // 호스트랑 룸   (user)
    @GetMapping("/{id}/hostroom")  // 호스트가 가지고있는 룸 가져오는거 가능
    public Header<TbHostTbRoomApiResponse> roomHost(@PathVariable Integer id){
        return tbHostApiService.hostRoom(id);
    }

    //호스트가 가지고 있는 예약내역(scheduleManagement)
    @GetMapping("/{id}/paylist")
    public Header<TbHostPayResponse> hostpay(@PathVariable(name = "id") Integer id){
        return tbHostServiceTest.hostPay(id);
    }

    //호스트 이미지 test
    @GetMapping("/{id}/hostImg")
    public Header<TbHostImgUserApiResponse> imgHost(@PathVariable Integer id){
        return tbHostApiService.HostImg(id);
    }

    // 종류별로 쏴줄때 유저페이지
    // 모텔리스트 (user)
    @GetMapping("/motellist")
    public List<TbHostApiRequest> mlist(){
        return tbHostApiService.motelList();
    }
    // 호텔리스트 (user)
    @GetMapping("/hotellist")
    public List<TbHostApiRequest> holist(){
        return tbHostApiService.hotelList();
    }
    // 펜션리스트 (user)
    @GetMapping("/panlist")
    public List<TbHostApiRequest> plist(){
        return tbHostApiService.panList();
    }
    // 리조트리스트 (user)
    @GetMapping("/relist")
    public List<TbHostApiRequest> rlist(){
        return tbHostApiService.reList();
    }
    // 게스트하우스리스트 (user)
    @GetMapping("/glist")
    public List<TbHostApiRequest> glist(){
        return tbHostApiService.guesthouselList();
    }
    // 캠핑하우스리스트 (user)
    @GetMapping("/camlist")
    public List<TbHostApiRequest> clist(){
        return tbHostApiService.camList();
    }
    // 한옥리스트 (user)
    @GetMapping("/hanlist")
    public List<TbHostApiRequest> oklist(){
        return tbHostApiService.hanList();
    }
}

