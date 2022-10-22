$(function(){
    var idVal
    $('.btnSubmit').click(function(){
        idVal = $('#idCheck').val();
        $('#result').html('<strong>'+idVal+'</strong> 은(는) 사용 가능한 아이디 입니다.')
        $('.use').show();
    });
    $('.use').click(function(){
        opener.document.getElementById("userId").value = document.getElementById("idCheck").value
        window.close();
    });
})