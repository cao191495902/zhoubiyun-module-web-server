/**
 * Created by Administrator on 2018/5/24 0024.
 */
/*倒计时*/
function time(timeNumStr) {
    var $timeNum = $(timeNumStr);
    var num = parseInt($timeNum.data("time"));
    num--;
    $timeNum.text(num + "s").data("time", num);
    !$timeNum.hasClass("curr") && $timeNum.addClass("curr");
    if (num == 0) {
        $timeNum.text(90);
        $timeNum.data("time", 90);
        $timeNum.text("重新发送");
        clearTimeout(timer)
    } else {
        timer = setTimeout(function () {
            time(timeNumStr, 90)
        }, 1000);
    }
}

/*
 *重新发送
 * url:请求地址
 * */

/*function re_send(obj, pobj, url, action, option) {
    var $obj = $(obj),
        option = option || {};

    $obj.on("click", function () {
        var $this = $(this);
        time(this);
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            data: {
                phone: $(pobj).val(),//需要传给后台的字符串，手机号码
                action: action
            }
        }).done(function (res) {
            return typeof(option.success) == "function" && option.success($obj, res)
        })
    })
}

/!*提交表单*!/
function formSubmit(option) {
    var option = option || {};
    $("form").on("submit", function (e) {
        e.preventDefault();
        var flag = typeof(option.flag) !== "undefined" && option.flag;
        var $this = $(this);
        var $submit = $this.find("input[type='submit']");
        var data = $this.serialize();
        var url = $this.attr("action");
        if (flag) {
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: data
            }).done(function (res) {
                if (res.code == 200) {
                    flag = true;
                }
                return typeof(option.callback) == "function" && option.callback($this, res)
            })
        }
    })
}*/

$(".register").on('click', function () {
    $(".new_user").show();
});
$(".login").on('click', function () {
    $(".loginBox").show().find(".mobileForm").show().siblings("form").hide();
});
$(".login_btn").on("click", function () {
    var $this = $(this);
    $this.parents(".form").hide().siblings().show();
})
$(".register_btn").on("click", function () {
    $(this).parents(".dialog").hide();
    $(".new_user").show();
})
$(".close").on("click", function () {
    $(this).parents(".dialog").hide();
});
$(".view").on("click", function () {
    var $this = $(this);
    if ($this.hasClass("curr") || $.trim($this.siblings(".text_input").val()) == "") {
        $this.removeClass("curr");
        $this.siblings(".text_input").attr("type", "password");
    } else {
        $this.addClass("curr");
        $this.siblings(".text_input").attr("type", "text");
    }
})

function toast(text) {
    var html = '<div class="toast">' + text + '</div>'
    $("body").append(html);
    setTimeout(function () {
        $("body").find(".toast").remove();
    },1000)
}

/*注册下的短信验证*/
// re_send(".code_send", "url", ({
//     success: function (obj, res) {
//         //验证码发送成功回调函数
//
//     }
// }))
/*end*/

/*提交表单方法调用*/
// formSubmit({
//     flag: true,
//     callback: function (obj, res) {
//         //回调函数
//         toast("11");
//     }
// })
/*
 *end
 */


var midCookieName = "current_login_account";
var nickNameCookieName = "nick_name";
if ($("#isLogin").val() == "true") {
    $("#nickName").html(getCookie(nickNameCookieName));
};

/*注册短信发送*/
$("#registVcode").on('click', function () {
    var phone = $("#registPhone").val();
    if (phone.length == 0) {
        toast("请输入手机号")
        return false;
    }
    var data = {
        action: 1,
        phone : phone
    };
    sendSms(data,this);
});

/*登录短信发送*/
$("#loginVcode").on('click', function () {
    var phone = $("#loginPhone").val();
    if (phone.length == 0) {
        toast("请输入手机号")
        return false;
    }
    var data = {
        action: 2,
        phone : phone
    };
    sendSms(data,this);
});

function sendSms(data,obj) {
    $.ajax({
        url: "http://iospkg.d.cn/dl/sendSms.html",
        type: "post",
        dataType: "json",
        data: data,
        success: function(data){
            if(data.code==200){
                time(obj);
            }
            toast(data.msg);

        },
        error: function(){
            alert("获取出错！");
        }
    });
}

function rsa(arg){
    setMaxDigits(130);
    var PublicExponent = "10001";
    var modulus = "be44aec4d73408f6b60e6fe9e3dc55d0e1dc53a1e171e071b547e2e8e0b7da01c56e8c9bcf0521568eb111adccef4e40124b76e33e7ad75607c227af8f8e0b759c30ef283be8ab17a84b19a051df5f94c07e6e7be5f77866376322aac944f45f3ab532bb6efc70c1efa524d821d16cafb580c5a901f0defddea3692a4e68e6cd";
    var key = new RSAKeyPair(PublicExponent,"",modulus);
    return encryptedString(key, arg);
}

function loginAjax(url, data) {
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        data: data,
        success: function(data){
            if (data.code == 200){
                setCookie(midCookieName, data.mid);
                setCookie(nickNameCookieName, data.nickname);
                window.location.reload();
            } else {
                toast(data.msg);
            }
        },
        error: function(){
            alert("获取出错！");
        }
    });
}

//注册
function regist() {
    var data = {
        channelId : $("#channelId").val(),
        phone : $("#registPhone").val(),
        password : rsa($("#registPassword").val()),
        vcode : $("#registvVerifyCode").val()
    };
    loginAjax("http://iospkg.d.cn/dl/iosReg.html", data);
}

//手机号登录
function loginPhoneF() {
    var data = {
        channelId : $("#channelId").val(),
        phone : $("#loginPhone").val(),
        vcode : $("#loginVerifyCode").val()
    };
    loginAjax("http://iospkg.d.cn/dl//loginPhone.html", data);
}

//账号密码登录
function login() {
    var data = {
        channelId : $("#channelId").val(),
        account : $("#account").val(),
        password : rsa($("#password").val())
    };
    loginAjax("http://iospkg.d.cn/dl//login.html", data);
}

function setCookie(name,value)
{
    //var Days = 30;
    // var exp = new Date();
    // exp.setTime(exp.getTime() + Days*24*60*60*1000);
    var exp = "-1";
    document.cookie = name + "="+ escape (value) + ";expires=" + exp;
}

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

//登出
function loginOut() {
    var data = {
        channelId : $("#channelId").val(),
        mid : getCookie(midCookieName)
    };
    $.ajax({
        url: "http://iospkg.d.cn/dl/loginOut.html",
        type: "post",
        dataType: "json",
        data: data,
        success: function(data){
            if (data.code == 200){
                delCookie(midCookieName);
                delCookie(nickNameCookieName);
                window.location.reload();
            } else {
                toast(data.msg);
            }
        },
        error: function(){
            alert("获取出错！");
        }
    });
}








