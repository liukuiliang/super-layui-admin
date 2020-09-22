layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("哪个后台能这样登录？别给爷整事啊",{
            time:5000
        });
    });

    //登录按钮
    form.on("submit(login)",function(){
        // $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        let userName = document.getElementById("userName").value;
        let password = document.getElementById("password").value;
        setTimeout(function(){
            $.ajax({
                type:"GET",
                url:"/user/login",
                data:{
                  userName:userName,
                  password:password
                },
                dataType:"json", // 预期返回值类型
                success:function(data){
                    console.log(data)
                    window.location.href = "/index.html";
                // if(data.success){
                //     window.location.href = "/index.html";
                // }
            },
                error:function(){
                    layer.msg('用户名或密码错误');
                }
            });
        },1000);
        return false;
    });

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
