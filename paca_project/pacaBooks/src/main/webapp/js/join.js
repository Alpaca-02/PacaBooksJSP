$(function(){
    $('.find').click(function(){
        window.open('id_check.html','미리보기','width=400,height=300');
    });

    // 휴대전화 번호 하이픈 정규식
    $(document).on("keyup", "#phone", function() {
        $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") );
    });

    // 비밀번호 일치여부 확인
    let passCheck = false;

    $(document).on("keyup","#confirmPW", function(){
        let pw=$("#pwd").val();
        let conPw = $(this).val();
        $('#create').css("height",'575px')
        $('.con_PW_not').css("display","inline-block");
        if(pw==conPw){
            passCheck = true;
            $(".con_PW_not").html("비밀번호가 일치합니다.");
            $(".con_PW_not").css("color","black");
        }else{
            passCheck = false;
            $(".con_PW_not").html("비밀번호가 일치하지 않습니다.");
            $(".con_PW_not").css("color","red");
        }
    })
})