lazyload(); // Lazyload

$(function () {
	// 다시예약
	$('.btn_re button').click(function () {
		var ano = $(this).data('ano');
		var adcno = $(this).data('adcno');
		var date = $(this).data('date');
		var rStr = randomString();
		location.href = '/product/detail?ano=' + ano + '&adcno=' + adcno + '&sel_date=' + date + '&sel_date2=' + date + '&_search=' + rStr;
	});

	// 예약상세환불
	$('button.gra_red[data-refund]').click(function () {
		var refundType = $(this).data('refund');
		close_layer();
		pop_twobtn('refund_el', '<p>예약취소/연기는 1일1회만 가능하며 사용된 쿠폰은 소멸됩니다.(5박에 1박 쿠폰 제외)</p><p>예약취소는 신중히 생각하시고 진행해 주시기 바랍니다.</p><p>예약취소를 하시겠습니까?</p>', '아니오', (refundType == 1 ? '예약 취소 요청' : '예약 연기 요청'), 'close_layer()', 'refund_action(' + refundType + ')');
	});

	//  1:1문의 탭
	$('.inquiry .tab a').each(function (e) {
		$(this).click(function (i) {
			i.preventDefault();
			$(this).addClass('active');
			$('.tab_each').hide();
			$('.tab_each').eq(e).show();
		});
	});
});
// 예약환불 및 연기
function refund_action(refund) {
	var refundType = refund;
	$.ajax({
		type: 'POST',
		async: false,
		cache: false,
		url: '/my/setReserveCancelPro_non',
		dataType: 'json',
		data: { 'do_no': $('input[name=cancel_do_no]').val() },
		success: function (data) {
			close_layer();
			var outputMsg = refundType == 1 ? "환불" : "예약연기";

			if (data.code == 200) {
				alert_Msg(outputMsg + "처리가 완료되었습니다.", 1, '/my/reserveList');
			} else {
				alert_Msg(outputMsg + "처리도중 문제가 발생했습니다.<br>고객행복센터에 문의해주세요.");
			}
		},
		error: function (request, status, error) {
			alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		}
	});
}

var iscroll_all;
function iscroll_run_all() {
	iscroll_all = new IScroll('.pop_all .iscroll_reserve', {
		mouseWheel: true,
		interactiveScrollbars: true,
		shrinkScrollbars: 'scale',
		fadeScrollbars: true,
		click: true
	});
}

var iscroll_delay;
function iscroll_run_delay() {
	iscroll_all = new IScroll('.pop_delay .iscroll_reserve', {
		mouseWheel: true,
		interactiveScrollbars: true,
		shrinkScrollbars: 'scale',
		fadeScrollbars: true,
		click: true
	});
}

function pop_all() { // 전액환불요청 팝업
	prevent_scroll();
	$('.pop_all').fadeIn(150);
	$('.bg_dimm').fadeIn(150);
	iscroll_run_all(); // iscroll
	$('.fix_title').bind('touchmove', function (i) { i.preventDefault() }); // 타이틀 터치 방지
}

function pop_delay() { // 예약연기요청 팝업
	prevent_scroll();
	$('.pop_delay').fadeIn(150);
	$('.bg_dimm').fadeIn(150);
	iscroll_run_delay(); // iscroll
	$('.fix_title').bind('touchmove', function (i) { i.preventDefault() }); // 타이틀 터치 방지
}

/* 구매총액 팝업 */
var iscroll_price;

function iscroll_run_price() {
	iscroll_price = new IScroll('.iscroll_price', {
		mouseWheel: true,
		interactiveScrollbars: true,
		shrinkScrollbars: 'scale',
		fadeScrollbars: true,
		click: true
	});
}

function pop_price() {
	var cnt = $('.pop_price ul li').length;

	if (cnt < 3) { // 2개 이하일때
		$('.pop_price .content').addClass('height_align');
	} else {
		null;
	}
	prevent_scroll();
	$('.pop_price').fadeIn(150);
	$('.bg_dimm').fadeIn(150);
	iscroll_run_price(); // iscroll
	$('.fix_title').bind('touchmove', function (i) { i.preventDefault() }); // 타이틀 터치 방지
	align_verticalAll('pop_price');
}

/* 팝업 - 쿠폰등록 */
function coupon_reg() {
	$('.coupon_reg').fadeIn(150);
	$('.bg_dimm').fadeIn(150);
	prevent_scroll();
	align_vertical();
}

/* 당첨자 확인 iscroll */
var iscroll_chk_use;

function iscroll_run_win() {
	iscroll_chk_use = new IScroll('.iscroll_chk_use', {
		mouseWheel: true,
		interactiveScrollbars: true,
		shrinkScrollbars: 'scale',
		fadeScrollbars: true,
		click: true
	});
}

function pop_chk_use() { // 열기
	$('.pop_chk_use').fadeIn(150);
	$('.bg_dimm').fadeIn(150);
	iscroll_run_win();
	iscroll_chk_use.refresh();
}

// 최근본숙소 전체삭제
$.del_recentRoom = function () {
	$.ajax({
		type: 'POST',
		async: false,
		cache: false,
		url: '/my/delRecentRooms_non',
		dataType: 'json',
		data: {},
		success: function (data) {
			window.location.reload();
		},
		error: function (request, status, error) {
			alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		}
	});
}

// 회원탈퇴
function logoutHome() {
	window.location.href = '/userindex/';
}

// 비밀번호 확인
function userPasswordCheck(uType) {
	//var password = $('#passwordUser').val();
	var reason = $('input[name="radios"]:checked').val();
	var reasonMsg = $('input[name="radios"]:checked').siblings('.reason-innder-box').children('textarea').val();
	/*
	console.log("password" + password);
	console.log("reason" + reason);
	console.log("reasonMsg" + reasonMsg);
	*/

	if (uType == 1) {
		$.ajax({
			type: 'GET',
			url: '/my/userPasswordCheck?password=' + password,
			dataType: 'json',
			success: function (data) {
				if (data.code == 200) {
					$("#reason").val(reason);
					$("#reasonMsg").val(reasonMsg);
					moveStep(2); // 회원탈퇴페이지 이동
				} else {
					toastPop(data.msg);
				}
			}
		});
	} else {
		$("#reason").val(reason);
		$("#reasonMsg").val(reasonMsg);
		moveStep(2); // 회원탈퇴페이지 이동
	}
}

//탈퇴 처리
	function userWithdrawal() {
		var uno = $('#uno').val();
		var reason = $('#reason').val();
		var reasonMsg = $('#reasonMsg').val();

		$.ajax({
			type: 'GET',
			url: '/my/userWithdrawal?uno=' + uno + '&reason=' + reason + '&reasonMsg=' + reasonMsg,
			dataType: 'json',
			success: function (result) {
				if (typeof result.data.uno == "undefined" || result.data.uno === null) {
					alert(result.meta.message);
					return false;
				} else {
					window.location.href = '/userindex/';
				}
			}
		});
	}

// 회원탈퇴 레이어 팝업 OPEN
	function withdrawPopOpen(stepNum) {
		var step = stepNum ? stepNum : 0;
		var conts = $('.cont-step');
		$(conts).hide();
		$(conts).eq(step).show();
		pop_layer_w('new_pop_withdraw');
	};

// 회원탈퇴 초기화
	function withdrawInit(el) {
		var el = el ? el : $('.cont-step');
		el.find('.cont_section').scrollTop(0);
		el.find('input').prop('checked', false);
		el.find('input[type=password]').val('');
		el.find('input[type=text]').val('');
		el.find('.inner-textarea').val('');
		$('#passwordCheckBtn').addClass('disabled');
		$('#passwordCheckBtn').prop('disabled', true);
		$('#agreeBtn').addClass('disabled');
	}

// 탈퇴팝업 app전용 toast
	function toastShow(e, elItem, msg) {
		e.preventDefault();
		var el = elItem;
		var msg = msg ? msg : '&lsquo;여기어때 APP&rsquo;에서만 확인 가능합니다.';
		toastPop(msg);
	}

// 탈퇴팝업 OPEN Next Page
	function nextStep(isBtn) {
		$('.cont-step').hide();
		$(isBtn).parents('.cont-step').next().show();
	}

// 탈퇴팝업 OPEN Select Page
	function moveStep(pageNum) {
		withdrawInit();
		$('.cont-step').hide();
		$('.cont-step').eq(pageNum).show();
	}

// step2 체크박스시 버튼 활성화
function pwRdoValid(utype) {
	if (utype >= 2) {
		$('input:radio[name=radios]').change(function () {
			validSubmitBtn($(this).is(':checked'));
		})
	} else {
		$('input:radio[name=radios]').change(function () {
			if ($(this).is(':checked')) {
				validSubmitBtn(true);
			} else {
				validSubmitBtn(false);
			}
		})
	}
}

function validSubmitBtn(validNext) {
	var pwChkBtn = $('#passwordCheckBtn');

	if (validNext) {
		$(pwChkBtn).removeClass('disabled');
		$(pwChkBtn).prop('disabled', false);
	} else {
		$(pwChkBtn).addClass('disabled');
		$(pwChkBtn).prop('disabled', true);
	}
}




//탈퇴 step3 버튼 클릭
function lastAgree() {
	var agree = $('#withdrawAgreeCheck');
	if (!agree.is(':checked')) {
		alert_Msg('주의사항 모두 동의해 주세요.');
		agree.focus();
		return false;
	} else {
		pop_twobtn(
			'withdrawAgree',
			'<p>회원 탈퇴 시 계정 정보 및 보유중인 <br/>무료 (무상) 포인트와 쿠폰은 삭제되어 복구가 불가해요.</p><br /><p>정말로 탈퇴하시겠어요?</p>',
			'더 써볼래요',
			'떠날래요',
			'layer_w_close(),close_layer()',
			'userWithdrawal()'
		);
	}
}
//탈퇴 step3 주의사항 체크 버튼 활성화
function agreeChk() {
	var agree = $('#withdrawAgreeCheck');
	$(agree).change(function () {
		if ($(agree).is(':checked')) {
			$('#agreeBtn').removeClass('disabled');
		} else {
			$('#agreeBtn').addClass('disabled');
		}
	});
	$('input:radio[name=radios]').change(function(){
		if($(this).is(':checked') && $(pwInputText).val().length >= 4) {
			validSubmitBtn(true)
		} else {
			validSubmitBtn(false)
		}
	})
}
