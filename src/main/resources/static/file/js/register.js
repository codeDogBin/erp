function examineUsername() {
    var username = $("#username").val().trim();
    if (username == null || username == '') {
        return;
    }
    $.ajax({
        url: "examineUsernameAJAX.do",
        type: "post",
        data: {username: username},
        success: function (data) {
            if (data == 'OK') {
                $('#msg').html("该账户可用");
                $('#msg').attr("style", "color: mediumseagreen");
            } else {
                $('#msg').html("该账户已被注册");
                $('#msg').attr("style", "color: red")
            }
        },
        error: function () {
            alert("发生异常，请重试");
        }
    });
}


function examineRegistrationCode() {
    var registrationCode = $("#registrationCode").val().trim();
    if (registrationCode == null || registrationCode == '') {
        $('#msg2').html('');
        return;
    }
    console.log(registrationCode);
    $.ajax({
        url: "examineRegistrationCodeAJAX.do",
        type: "get",
        data: {registrationCode: registrationCode},
        dataType:'json',
        success: function (data) {
            if (data.state == 'OK') {
                $('#msg2').html(data.msg);
                $('#msg2').attr("style", "color: mediumseagreen");
            }else {
                $('#msg2').html('');
            }
        },
        error: function () {
            alert("发生异常，请重试");
        }
    });
}
function examinePassword(){
    var password = $("#password").val().trim();
    var rePassword = $("#rePassword").val().trim();
    if(password==null||password==''){
        return;
    }
    if(rePassword!=password){
        $('#msg3').html("两次密码不一致");
        $('#msg3').attr("style", "color: red");
        return;
    }
    $('#msg3').html("");
    return;
}

//注册
function register() {
    var username = $("#username").val().trim();
    var password = $("#password").val().trim();
    var registrationCode = $("#registrationCode").val().trim();
    if (username == null || username == '') {
        $('#msg').html("用户名不能为空");
        $('#msg').attr("style", "color: red")
        return;
    }
    if (password == null || password == '') {
        $('#msg3').html("密码不能为空");
        $('#msg3').attr("style", "color: red")
        return;
    }
    examinePassword();

    $.ajax({
        url: "register.do",
        type: "post",
        data: {username: username, password: password, registrationCode: registrationCode},
        success: function (data) {
            if (data == 'OK') {
                $('#msg2').html("注册成功，即将跳转到登录页");
                $('#msg2').attr("style", "color: mediumseagreen");
                setTimeout(function () {
                    location.href = "login.html";
                }, 500);
            } else {
                $('#msg2').html("注册失败，检查后重试");
                $('#msg2').attr("style", "color: red")
            }
        },
        error: function () {
            alert("发生异常，请重试");
        }
    });
}