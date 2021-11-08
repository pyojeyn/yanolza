$(function () {

    //TODO 양도양수
    var locale = {
        applyLabel: '적용',
        cancelLabel: '취소',
        fromLabel: '시작',
        toLabel: '종료',
        customRangeLabel: '사용자 지정',
        daysOfWeek: ['일', '월', '화', '수', '목', '금','토'],
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        firstDay: 1
    };

    $('[data-role=datepicker]').each(function(){
        $(this).daterangepicker({
            // minDate : moment(),
            format: 'YYYY-MM-DD',
            singleDatePicker: true,
            timePicker24Hour: true,
            timePicker: false,
            showDropdowns: true,
            opens: 'right',
            drops: 'down',
            applyClass: 'btn-primary',
            cancelClass: 'btn-default',
            separator: ' to ',
            locale: locale
        });
    });

    // 세금계산서 발행 여부에 따른 form data 표기 수정
    if ($('input[name="account_type"]:checked').val() === "1") {
        $('input[name="corporate"]').attr('disabled', 'disabled');
        $('input[name="co_name"]').attr('disabled', 'disabled');
        $("input[name='b_condition[]']").attr('disabled', 'disabled');
        $("input[name='b_type[]']").attr('disabled', 'disabled');
        isEnableEtcTextBox("etcCheck", "etcText");
        isEnableEtcTextBox("bizEtcCheck", "bizEtcText");
        $('input[name="co_num"]').attr('disabled', 'disabled');
        $('input[name="address2"]').attr('disabled', 'disabled');
        $('input[name="settlement_email"]').attr('disabled', 'disabled');
        $('#postCode').attr('disabled', 'disabled');
    }

    // 세금계사서 발행여부 체크 로직
    $('input[name="account_type"]').change(function(){
        if ($(this).val() === "1") {
            $('input[name="corporate"]').attr('disabled', 'disabled');
            $('input[name="co_name"]').attr('disabled', 'disabled');
            $("input[name='b_condition[]']").attr('disabled', 'disabled');
            $("input[name='b_type[]']").attr('disabled', 'disabled');
            isEnableEtcTextBox("etcCheck", "etcText");
            isEnableEtcTextBox("bizEtcCheck", "bizEtcText");
            $('input[name="co_num"]').attr('disabled', 'disabled');
            $('input[name="address2"]').attr('disabled', 'disabled');
            $('input[name="settlement_email"]').attr('disabled', 'disabled');
            $('#postCode').attr('disabled', 'disabled');
        } else {
            $('input[name="corporate"]').removeAttr('disabled');
            $('input[name="co_name"]').removeAttr('disabled');
            $("input[name='b_condition[]']").removeAttr('disabled');
            $("input[name='b_type[]']").removeAttr('disabled');
            isEnableEtcTextBox("etcCheck", "etcText");
            isEnableEtcTextBox("bizEtcCheck", "bizEtcText");
            $('input[name="co_num"]').removeAttr('disabled');
            $('input[name="address2"]').removeAttr('disabled');
            $('input[name="settlement_email"]').removeAttr('disabled');
            $('#postCode').removeAttr('disabled');
        }
    });


    // 예약통보 아이템 추가
    $('[data-role=add-number]').on('click',function() {
        $('.form-notification').append($('.notification-item.hide').clone().removeClass('hide'));
    });

    // 예약통보 아이템 제거
    $('.form-notification').on('click','[data-role=remove-number]',function() {
        $(this).closest('.notification-item').remove();
    });

    // 예약통보 아이템 제거
    $('.notification-item').on('click','[data-role=remove-number]',function() {
        $(this).closest('.notification-item').remove();
    });

    $('.change-pw').on('click', function(){
        if ($('.pw-new').hasClass('hide')) {
        	$('#is_pw_change').val("Y");
            $('.pw-new').removeClass('hide');
            $('input[name=old_password]').removeAttr('disabled');
            $('input[name=password]').removeAttr('disabled');
            $('input[name=re_password]').removeAttr('disabled');
        } else {
        	$('#is_pw_change').val("N");
            $('.pw-new').addClass('hide');
            $('input[name=old_password]').attr('disabled', 'disabled');
            $('input[name=password]').attr('disabled', 'disabled');
            $('input[name=re_password]').attr('disabled', 'disabled');
        }
    });

    $('#input_roadAddress').on('click', function(){
       daumPostCode();
    });

    $('input[type="file"]').change(function () {
        var ext = this.value.match(/\.(.+)$/)[1].toLowerCase();
        switch (ext) {
            case 'jpg':
            case 'jpeg':
            case 'png':
            case 'gif':
                $('#fileName').val($(this).val());
                break;
            default:
                alert('이미지만 등록할 수 있습니다.');
                $(this).val('');
                $('#fileName').val('');
        }
    });

    // 통장사본 이미지 삭제
    $(document).on('click', '[data-role=refund-delete]',function() {
        var id = $(this).attr('data-id')
        if (confirm('정말 삭제하시겠습니까?')) {
            $.post('/my/removeRefund/'+id,function(result) {
                if (result == 'SUCCESS') {
                    alert('삭제가 완료 되었습니다.');
                    location.reload();
                } else {
                    alert('삭제 처리에 실패하였습니다. 관리자에게 문의하세요.');
                }
            }).fail(function() {
                alert('오류가 발생했습니다. 관리자에게 문의하세요.');
            });
        }
    });

    // 양도양수증서 삭제
    $(document).on('click', '[data-role=transfer-file-delete]',function() {
        var id = $(this).attr('data-id')
        if (confirm('정말 삭제하시겠습니까?')) {
            $.post('/my/removeTransferFile/'+id,'ano='+$(this).attr('data-ano'),function(result) {
                if (result == 'SUCCESS') {
                    alert('삭제가 완료 되었습니다.');
                    location.reload();
                } else {
                    alert('삭제 처리에 실패하였습니다. 관리자에게 문의하세요.');
                }
            }).fail(function() {
                alert('오류가 발생했습니다. 관리자에게 문의하세요.');
            });
        }
    });

    // 양도양수 예금주 글자수 제한
    $('input[name="transfer_mcalc_deposit_name"]').keyup(function(){
        bytesHandler(this);
    });

    // 양도양수 계좌번호 글자수 제한
    $('input[name="transfer_mcalc_bank_account"]').keyup(function(){
        bytesHandler(this);
    });

    // aphone 값이 없으면 제한
    if( isEmpty($('#aphoneVal').val()) ) {
        $('#aphone').hide();
    }
});


function getTextLength(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        if (escape(str.charAt(i)).length == 6) {
            len++;
        }
        len++;
    }
    return len;
}

function bytesHandler(obj){
    var text = $(obj).val();
    if(getTextLength(text) >= 40){
        alert("최대 20자 까지 입력가능합니다");
    }
}

// daum api 주소
function daumPostCode() {
    new daum.Postcode(
    {
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', '
                    + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if (fullRoadAddr !== '') {
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('input_roadAddress').value = fullRoadAddr;
            // document.getElementById('input_jibunAddress').value = data.jibunAddress;
            document.getElementById('input_roadAddress2').value = fullRoadAddr;

            //안연주 주소값을 폼으로 보내는

            // daumGeo();
        }
    }).open();
}

function isEnableEtcTextBox(checkId, textId) {
    if($("input:checkbox[id=" + checkId + "]").is(":checked") && !$("input:checkbox[id=" + checkId + "]").is(":disabled")) {
        $("input:text[id=" + textId + "]").removeAttr("disabled");
    } else {
        $("input:text[id=" + textId + "]").attr("disabled", "disabled");
    }
}

function setAphoneMapping() {
    var phoneNum = $('select[name=phone1]').val()+'-'+$('input[name=phone2]').val()+'-'+$('input[name=phone3]').val();
    var telNum = $('select[name=tel1]').val()+'-'+$('input[name=tel2]').val()+'-'+$('input[name=tel3]').val();
    var phoneRegExp = /^\d{2,4}-\d{3,4}-\d{4}$/;
    var chkPhoneType = $('select[name=chkPhoneType]').val();  // 안심번호로 할당될 착신번호 타입
    var aphoneMapping = ''; // 안심번호로 할당될 번호
    var aphone = $('input[name=aphoneVal]').val(); // 안심번호
    var ano = $('input[name=ano]').val();

    if (isEmpty(chkPhoneType)) {
        alert('착신번호를 선택해주세요');
        return false;
    }else if(chkPhoneType == 'phone') {
        if (!phoneNum.match(phoneRegExp)){
            alert("휴대폰 번호 형식이 잘못 되었습니다.");
            $('input[name=phone2]').focus();
            return false;
        }
        aphoneMapping = phoneNum;
    }else if (chkPhoneType == 'tel') {
        if (!telNum.match(phoneRegExp)){
            alert("대표 번호 형식이 잘못 되었습니다.");
            $('input[name=tel2]').focus();
            return false;
        }
        aphoneMapping = telNum;
    }

    $.ajax({
        method : 'POST',
        url : '/my/aphoneMapping',
        data : {'aphone' : aphone, 'aphoneMapping' : aphoneMapping, 'aphoneType' : chkPhoneType, 'ano' : ano},
        dataType : 'json',
        success : function(res) {
            if(res.rtv){
                $('#aphone').show();
                $('#aphone').html(maskingSafeNumber(res.aphone));
                $('#aphoneVal').val(res.aphone);
                $('#aphoneMappingChk').val(aphoneMapping.replace(/-/g,''));
                $('#aphoneMappingTypeChk').val(chkPhoneType);
                alert("안심번호 할당 되었습니다.");
            } else {
                alert('담당 개발자에게 문의바랍니다.(급하시다면 skt 관리자페이지를 이용바랍니다.)');
            }
        },
        error: function() {
            alert('담당 개발자에게 문의바랍니다.(급하시다면 skt 관리자페이지를 이용바랍니다.)');
        }
    });

}

// 안심번호 마스킹
function maskingSafeNumber (aphone) {
    var aphone1 = aphone.substr(0, 4);
    var aphone2 = aphone.substr(4, 4);
    var result = aphone1+'-'+aphone2+'-****';

    return result;
}

function isNotEmpty(val) {
    if (val !== null && val !== '' && typeof val !== 'undefined') {
        return true;
    }
    return false;
}

function isEmpty(val) {
    return !isNotEmpty(val);
}


$('#my-form').submit(function (event) {
    var phone1 = $('select[name=phone1]');
    var phone2 = $('input[name=phone2]');
    var phone3 = $('input[name=phone3]');
    var phoneNumber = phone1.val()+'-'+phone2.val()+'-'+phone3.val();


    var tel1 = $('select[name=tel1]');
    var tel2 = $('input[name=tel2]');
    var tel3 = $('input[name=tel3]');
    var telNumber = tel1.val()+'-'+tel2.val()+'-'+tel3.val();

    var chkPhoneType = $('select[name=chkPhoneType]').val(); // 착신번호선택 타입
    var aphoneMappingChk = $('#aphoneMappingChk').val(); // 안심번호 할당한 번호
    var aphoneMappingTypeChk = $('#aphoneMappingTypeChk').val(); // 안심번호 할당한 착신번호 타입
    var phoneRegExp = /^\d{2,4}-\d{3,4}-\d{4}$/;

    if (phone1.val().length === 0) {
        alert('휴대폰 번호를 입력해주세요.');
        phone1.focus();
        return false;
    }
    if (phone2.val().length === 0) {
        alert('휴대폰 번호를 입력해주세요.');
        phone2.focus();
        return false;
    }
    if (phone3.val().length === 0) {
        alert('휴대폰 번호를 입력해주세요.');
        phone3.focus();
        return false;
    }

    if (!phoneNumber.match(phoneRegExp)){
        alert("휴대폰 번호 형식이 잘못 되었습니다.");
        phone2.focus();
        return false;
    }

    if (tel1.val().length === 0) {
        alert('대표 번호를 입력해주세요.');
        tel1.focus();
        return false;
    }
    if (tel2.val().length === 0) {
        alert('대표 번호를 입력해주세요.');
        tel2.focus();
        return false;
    }
    if (tel3.val().length === 0) {
        alert('대표 번호를 입력해주세요.');
        tel3.focus();
        return false;
    }

    if (!telNumber.match(phoneRegExp)){
        alert("대표 번호 형식이 잘못 되었습니다.");
        tel2.focus();
        return false;
    }

    if( isNotEmpty(aphoneMappingTypeChk) && aphoneMappingTypeChk != chkPhoneType ) {
        alert("안심번호를 할당해주세요. (착신번호 선택이 다릅니다)");
        return false;
    }else if(aphoneMappingTypeChk == 'phone' && phoneNumber.replace(/\-/g,'') != aphoneMappingChk.replace(/\-/g,'')){
        alert("안심번호를 할당해주세요. (할당된 번호가 다릅니다)");
        return false;
    }else if(aphoneMappingTypeChk == 'tel' && telNumber.replace(/\-/g,'') != aphoneMappingChk.replace(/\-/g,'')){
        alert("안심번호를 할당해주세요. (할당된 번호가 다릅니다)")
        return false;
    }

    if ($('input[name="account_type"]:checked').val() === '2') {
        var corporate = $('input[name=corporate]');
        var co_name = $('input[name=co_name]');
        var b_condition = $("input[name='b_condition[]']:checked");
        var b_type = $("input[name='b_type[]']:checked");
        var co_num = $('input[name=co_num]');
        var address1 = $('input[name=address1]');
        var settlement_email = $('input[name=settlement_email]');

        if (corporate.val().length === 0) {
            alert('상호명(법인명)을 입력해주세요.');
            corporate.focus();
            return false;
        }
        if (co_name.val().length === 0) {
            alert('등록증상 대표명을 입력해주세요.');
            co_name.focus();
            return false;
        }
        if (b_condition.length === 0) {
            alert('업태를 선택해주세요.');
            $("input[name='b_condition[]']").focus();
            return false;
        }
        if (b_type.length === 0) {
            alert('업종을 선택해주세요.');
            $("input[name='b_type[]']").focus();
            return false;
        }
        if (co_num.val().length === 0) {
            alert('사업자 등록번호를 입력해주세요.');
            co_num.focus();
            return false;
        }
        if (address1.val().length === 0) {
            alert('업체주소를 입력해주세요.');
            address1.focus();
            return false;
        }
        if (settlement_email.val().length === 0) {
            alert('세금계산서 이메일을 입력해주세요.');
            settlement_email.focus();
            return false;
        }


    }


    if($('input[name="is_transfer"]').is(":checked")){

        if($('input[name="member_transfer_date"]').val() && $('input[name="member_transfer_date"]').val() >= $('input[name="transfer_date"]').val()){
            alert('기존 양도양수 기준일보다 과거 일자로 등록하실 수 없습니다.');
            return false;
        }

        if($('input[name="transfer_date"]').val() == undefined || $('input[name="transfer_date"]').val() ==""){
            alert('양도양수 기준일을 입력해 주세요.');
            $('input[name="transfer_date"]').focus();
            return false;
        }

        var datatimeRegexp = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;

        if ( !datatimeRegexp.test($('input[name="transfer_date"]').val()) ) {
            //alert("양도양수 기준일은 “년-월-일“ 형태로 입력해 주세요.");
            alert("날짜는 YYYY-MM-DD 형식으로 입력해주세요.");
            $('input[name="transfer_date"]').focus();
            return false;
        }

/*        var transferDate = moment($('input[name="transfer_date"]').val()).format("YYYYMMDD");
        var nowDate = moment().format("YYYYMMDD");

        if(transferDate < nowDate){
            alert("양도양수 기준일은 과거로 입력이 불가능합니다.");
            $('input[name="transfer_date"]').focus();
            return false;
        }*/

        if ( isNaN(parseInt(Number($('input[name="new_ano"]').val()))) ) {
            alert('신규 제휴점 번호는 숫자만 입력 가능합니다.');
            $('input[name="new_ano"]').focus();
            return false;
        }

        // [BOD-2272] 거래은행,예금주,계좌번호 중 값이 1개이상 입력시 모두 체크
        if ($('select[name="transfer_mcalc_bank_name"]').val() || $('input[name="transfer_mcalc_deposit_name"]').val() || $('input[name="transfer_mcalc_bank_account"]').val()){
           if (!$('select[name="transfer_mcalc_bank_name"]').val() || !$('input[name="transfer_mcalc_deposit_name"]').val() || !$('input[name="transfer_mcalc_bank_account"]').val()){
                alert("양도양수의계좌정보를확인해주세요.");
                return false;
            }

            if ($('input[name="transfer_mcalc_bank_account"]').val() && $.isNumeric($('input[name="transfer_mcalc_bank_account"]').val()) != true){
                alert("계좌정보를 확인해주세요.");
                return false;
            }
        }


    }



    // 예약통보 체크
    var noti = $('input[name=notification]');
    var notifications = [];
    var notifications_validation = true;
    var noti_method_check = true;
    var noti_email_check = true;
    var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

    $('.notification-item').not('.hide').each(
        function() {

                // name,method,number,mon~sun,from,to
                var notification = '';
                if ($(this).find('[data-name=notification_name]').val() == '') {
                    notifications_validation = false;
                } else {
                    notification += $(this).find('[data-name=notification_name]').val();
                }

                if ($(this).find('[data-name=notification_method]').val() == '') {
                    notifications_validation = false;
                } else {
                    notification += '|' + $(this).find('[data-name=notification_method]').val();
                }

                if ($(this).find('[data-name=notification_number]').val() == '') {
                    notifications_validation = false;
                } else {
                    if (($(this).find('[data-name=notification_method]').val()) === 'EMAIL') {
                        if (!$(this).find('[data-name=notification_number]').val().match(regExp)) {
                            noti_email_check = false;
                        }
                        notification += '|' + $(this).find('[data-name=notification_number]').val();
                    } else {
                       if ($.isNumeric($(this).find('[data-name=notification_number]').val()) == true) {
                            notification += '|' + $(this).find('[data-name=notification_number]').val();
                       } else {
                            noti_method_check = false;
                       }
                    }
                }

                notification += '|';

                if ($(this).find('[data-name=notification_mon]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_tue]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_wed]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_thr]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_fri]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_sat]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';
    
                if ($(this).find('[data-name=notification_sun]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';


                notification += '|'+ $(this).find('[data-name=notification_from]').val();
                notification += '|'+ $(this).find('[data-name=notification_to]').val();

                notifications.push(notification);
            }
            );
    if (!notifications_validation) {
        alert('[예약알리미] 설정을 확인하세요.');
        return false;
    }

    if (!noti_method_check) {
        alert('[예약알리미] 문자메시지와 FAX는 숫자만 입력해주세요.\n\n(띄어쓰기, "-" 제외)');
        return false;
    }

    if (!noti_email_check) {
        alert('[예약알리미] 유효한 이메일 주소를 입력해주세요.');
        return false;
    }

    //패스워드 체크
    if ($('#is_pw_change').val() == 'Y') {
        var password = $('input[name=password]').val().trim();
        var re_password = $('input[name=re_password]').val().trim();

        if (password == '') {
            alert('새로운 비밀번호를 입력해 주세요.');
            return false;
        }

        var regPwd = /^(?=.*[a-zA-Z])(?=.*[#\&\\+\-%@=\/\\\:;,\.\'\"\^`~\_|\!\/\?\*$#<>()\[\]\{\}])(?=.*[0-9])/;
        if (regPwd.test(password)==false || password.length <= 7) {
            alert('영문,숫자,특수문자 포함 8자리 이상의 비밀번호로 설정하세요.');
            return false;
        }

        var regPwdChain = /([a-zA-Z0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'])\1\1\1/;
        if (regPwdChain.test(password)==true) {
            alert('4자 이상 동일문자를 연속적으로 사용할 수 없습니다.');
            return false;
        }

        if (re_password == '') {
            alert('확인을 위해 새 비밀번호를 입력해 주세요.');
            return false;
        }

        if ( password != re_password ) {
            alert('새 비밀번호와 일치하지 않습니다.');
            return false;
        }
    }

    noti.val(notifications);

    return true;
    event.preventDefault();
});

$('#my-view-form').submit(function (event) {
    // 예약통보 체크
    var noti = $('input[name=notification]');
    var notifications = [];
    var notifications_validation = true;
    var noti_method_check = true;
    var noti_email_check = true;
    var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

    $('.notification-item').not('.hide').each(
        function() {

                // name,method,number,mon~sun,from,to
                var notification = '';
                if ($(this).find('[data-name=notification_name]').val() == '') {
                    notifications_validation = false;
                } else {
                    notification += $(this).find('[data-name=notification_name]').val();
                }

                if ($(this).find('[data-name=notification_method]').val() == '') {
                    notifications_validation = false;
                } else {
                    notification += '|' + $(this).find('[data-name=notification_method]').val();
                }

                if ($(this).find('[data-name=notification_number]').val() == '') {
                    notifications_validation = false;
                } else {
                    if (($(this).find('[data-name=notification_method]').val()) === 'EMAIL') {
                        if (!$(this).find('[data-name=notification_number]').val().match(regExp)) {
                            noti_email_check = false;
                        }
                        notification += '|' + $(this).find('[data-name=notification_number]').val();
                    } else {
                       if ($.isNumeric($(this).find('[data-name=notification_number]').val()) == true) {
                            notification += '|' + $(this).find('[data-name=notification_number]').val();
                       } else {
                            noti_method_check = false;
                       }
                    }
                }

                notification += '|';
                if ($(this).find('[data-name=notification_mon]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_tue]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_wed]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_thr]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_fri]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';

                if ($(this).find('[data-name=notification_sat]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';


                if ($(this).find('[data-name=notification_sun]').is(":checked"))
                    notification += '1';
                else
                    notification += '0';



                notification += '|'+ $(this).find('[data-name=notification_from]').val();
                notification += '|'+ $(this).find('[data-name=notification_to]').val();

                notifications.push(notification);
            }
            );
    if (!notifications_validation) {
        alert('[예약알리미] 설정을 확인하세요.');
        return false;
    }

    if (!noti_method_check) {
        alert('[예약알리미] 문자메시지와 FAX는 숫자만 입력해주세요.\n\n(띄어쓰기, "-" 제외)');
        return false;
    }

    if (!noti_email_check) {
        alert('[예약알리미] 유효한 이메일 주소를 입력해주세요.');
        return false;
    }
    //패스워드 체크
    if ($('#is_pw_change').val() == 'Y') {
        var password = $('input[name=password]').val().trim();
        var re_password = $('input[name=re_password]').val().trim();

        if (password == '' || re_password == '') {
            alert('새로운 비밀번호를 입력해 주세요.');
            return false;
        }

        var regPwd = /^(?=.*[a-zA-Z])(?=.*[#\&\\+\-%@=\/\\\:;,\.\'\"\^`~\_|\!\/\?\*$#<>()\[\]\{\}])(?=.*[0-9])/;
        if (regPwd.test(password)==false || password.length <= 7) {
            alert('영문,숫자,특수문자 포함 8자리 이상의 비밀번호로 설정하세요.');
            return false;
        }

        var regPwdChain = /([a-zA-Z0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'])\1\1\1/;
        if (regPwdChain.test(password)==true) {
            alert('4자 이상 동일문자를 연속적으로 사용할 수 없습니다.');
            return false;
        }

        if ( password != re_password ) {
            alert('새 비밀번호와 일치하지 않습니다.');
            return false;
        }
    }

    noti.val(notifications);

    return true;
    event.preventDefault();
});