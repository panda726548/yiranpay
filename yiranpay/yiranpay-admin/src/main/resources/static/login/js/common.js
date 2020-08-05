$(function(){

    $("#x-nav > li").click(function(){
        $(this).addClass('on').siblings('li').removeClass('on');
        var toggle = $(this).find('ul').attr('toggle');
        //console.log(toggle);
        if(toggle == 'on'){
            $(this).find('ul').stop().hide().attr({"toggle":"off"});
        }else{
            $(this).find('ul').stop().show(300).attr({"toggle":"on"});
            //alert(1);
        }

    })
});

//存入左边nav标记
function setRel(mudel){
    mudel=mudel.toLowerCase();
    //alert(mudel);return false;
    if (window.localStorage) {  //判断是否支持localStorage
        localStorage.setItem(mudel+"_Rel", "60");
        localStorage.setItem(mudel+"_Rel", mudel);
    } else {  //不支持的话使用Cookie存贮
        $.cookie(mudel+"_Rel", mudel, { expires: 1 });
    }
}
//获取左边nav标记
function getRel(mudel){
    mudel=mudel.toLowerCase();
    if (window.localStorage) {  //判断是否支持localStorage
        var thisreturn=localStorage.getItem(mudel+"_Rel");
    } else {  //不支持的话使用Cookie存贮
        var thisreturn=$.cookie(mudel+"_Rel");
    }//alert(thisreturn);
    return thisreturn;
}
//执行标记nav
function selectRel(mudel){
    mudel=mudel.toLowerCase();
    var xrel=getRel(mudel);
    //alert(xrel);
    if(xrel!='' && xrel!=null){
        $('#'+xrel).addClass('on').parent('li').parent('ul').attr({"toggle":"on"});
        $('#'+xrel).addClass('on').parent('li').parent('ul').stop().show();
        $('#'+xrel).parent('li').siblings('li').find('a').removeClass('on');
        $('#'+xrel).addClass('on').parent('li').parent('ul').parent('li').addClass('on');
        $('#'+xrel).parent('li').parent('ul').parent('li').siblings('li').removeClass('on');
        $('#'+xrel).parent('li').addClass('on');
    }
}

/**
 * author LvGang
 * 加载等待函数
 * time 时间 单位/秒
 */
function loading(time = 3) {
    time = time * 1000;
    new Vue({
        created:function(){
            const loading = this.$loading({
                lock: true,
                text: 'Loading',
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.7)'
            });
            setTimeout(() => {
                loading.close();
            }, time);
        }
    });
}

/**
 * author LvGang
 * 通知
 * time 时间 单位/秒
 */
function notice(msg) {
    new Vue({
        created:function(){
            methods: {
                this.$notify({
                    title: '成功',
                    message: msg,
                    type: 'success'
                });
            }
        }
    });
}


/**
 * author LvGang
 * 提示消息函数
 * @param msg   提示消息
 * @param type  弹窗类型
 */
function message(msg, type, func=null) {
    var types;
    switch (type) {
        case 1: types = 'success'; break; // 成功
        case 2: types = 'error'; break;   // 错误
        case 3: types = 'warning'; break; // 警告
        default: types = 'success';

    }
    new Vue({
        created:function(){
            const title = this.$message({
                message: msg,
                type: types,
                center: true,
                onClose:function(){
                    if(func=='reload'){
                        location.reload();
                    }else{
                        //alert(func);
                        if (func != null) {
                            window.location.href=func;
                        }
                    }

                }

            });
        }
    });
}

/**
 * author Mr.zhou
 * 3m刷新
 */
function timeOut(){
    setTimeout(function(){
        history.go(0)
    },3000);
}
