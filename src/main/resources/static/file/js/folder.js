
$(function () {
    load();
})
var company_id = 0;
var fway_id= 0;
//刷新页面
function load() {
    $.ajax({
        type: "GET",
        url: "/bus/getFolderAJAX.do",
        dataType: "json",
        success: function (data) {
            console.log(data);
            company_id = data.company.id;
            fway_id = data.fway_id;
            //设置姓名
            $("#name").html(data.user.name);
            if(fway_id != 0){//首页不加载新建文件
                $('#selectFile').attr('style','display:inline');
                $('#uploadFile').attr('style','display:inline');
            }else{
                $('#selectFile').attr('style','display:none');
                $('#uploadFile').attr('style','display:none');
            }
            //加载路径组件的
            var way = $("#way");
            way.html("当前路径：");
            $("#way").append($("<a>"+data.company.name+"</a>").attr("href","javascript:void(0);").attr("onclick","toFolder(0)"));
            $.each(data.ways,function (index,item) {
                way.append(">");
                var a=$("<a></a>");
                a.attr("href","javascript:void(0);");
                a.attr("onclick","toFolder("+item.id+")")
                a.html(item.name);
                way.append(a);
            });
            loadFolderAndFil(data.folders,data.fils);
        },
        error: function () {
            alert("发生异常，请重试");
        }

    })
}
//删除文件夹
function delFolder(folder_id) {
    $.ajax({
        type: "GET",
        url: "/bus/expireFolderAJAX.do",
        dataType: "json",
        data:{folder_id:folder_id},
        success: function (data) {
            showMsg(data.msg);
            if(data.state=='FAIL'){
                alert(data.msg);
            }else {
                load();
            }
        }
    })
}
//重命名文件夹
function renameFolder(folder_id) {
    var name = prompt("请输入新的名字").trim();
    if(name == null||name==''){
        alert("请输入文字");
        return;
    }
    $.ajax({
        type: "GET",
        url: "/bus/renameFolder.do",
        dataType: "json",
        data:{folder_id:folder_id,fway_id:fway_id,name:name},
        success: function (data) {
            showMsg(data.msg);
            if(data.state=='FAIL'){
                alert(data.msg);
            }else {
                load();
            }
        }
    })
}
//删除文件
function delFil(fil_id) {
    var b = confirm("确定要删除该文件/文件夹吗?");
    if(b == false){
        return;
    }
    $.ajax({
        type: "GET",
        url: "/bus/expireFile.do",
        dataType: "json",
        data:{fil_id:fil_id,fway_id:fway_id},
        success: function (data) {
            showMsg(data.msg);
            if(data.state=='FAIL'){
                alert(data.msg);
            }else {
                setTimeout(load,100);
            }
        }
    })
}




//重命名文件夹
function renameFil(fil_id) {
    var name = prompt("请输入新的名字").trim();
    if(name == null||name==''){
        alert("请输入文字");
        return;
    }
    $.ajax({
        type: "GET",
        url: "/bus/renameFil.do",
        dataType: "json",
        data:{fil_id:fil_id,fway_id:fway_id,name:name},
        success: function (data) {
            showMsg(data.msg);
            if(data.state=='FAIL'){
                alert(data.msg);
            }else {
                load();
            }
        }
    })
}
//加载文件和文件夹
function loadFolderAndFil(folders,fils) {
    if (folders.length+fils.length==0){
        showMsg("当前目录没有文件");
    }
    var customerid=$("#customerid").val();
    console.log(customerid)
    //加载文件夹和文件
    var html="";
    $.each(folders,function (index,item) {
        html +='<li>\n' +
            '<div class="imgSize">\n'+
            '<img src="/file/showImageByPath?path=img/folder.jpg"  alt='+item.name+'>\n' +
            '</div>\n'+
            '<div class="fileName"><a href="javascript:void(0);" onclick="toFolder('+item.id+')">'+item.name+'</a></div>\n';
        if(customerid == 0){
            html += '<div  class="functionBtn" shiro:hasPermission="folder:update" >\n' +
                '<button class="btn2" onclick="renameFolder('+item.id+')">重命名</button>\n' +
                '<button class="btn2" onclick="delFolder('+item.id+')">删除</button>\n' +
                '</div>\n';
        }
           html += '</li>';
    });
    $.each(fils,function (index,item) {
        html +='<li>\n' +
            '<div class="imgSize">\n'+
            '<img src="/file/showImageByPath?path='+item.imgway+'" alt='+item.name+'>\n'+
            '</div>\n'+
            '<div class="fileName"><a href="/bus/getFile.do?id='+item.id+'&name='+item.name+'">'+item.name+'</a></div>\n';
        if(customerid == 0) {
            html += '<div class="functionBtn" shiro:hasPermission="folder:update">\n' +
            '<button class="btn2" onclick="renameFil(' + item.id + ')">重命名</button>\n' +
            '<button class="btn2" onclick="delFil(' + item.id + ')">删除</button>\n' +
            '</div>\n';
        }
        html +=  '</li>';
    });
    $("#folderSpace").html(html);
}
//创建文件夹
function createFolder(){
    var name = $("#filenameInput").val().trim();
    if(name === null || name===""){
        return;
    }
    $.ajax({
        url:"/bus/createFolder.do",
        type:"post",
        data:{company_id:company_id,fway_id:fway_id,name:name},
        success:function (data) {
            showMsg(data.msg);
            if(data.state=='FAIL'){
                alert(data.msg);
            }else {
                $("#filenameInput").val("");
                load();
            }
        },
        error: function () {
            alert("发生异常，请重试");
        }
    });
}
//上传文件
function uploadFil() {
    var formData = new FormData($('#from')[0]);
    formData.append('fway_id',fway_id);
    $.ajax({
        type: 'POST',
        url: '/bus/uploadFile.do',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data) {
            showMsg(data.msg);
            if(data.state=='FAIL'){
                alert(data.msg);
            }else {
                $("#selectFile").val("");
                load();
            }
        },
        error: function(data) {
            console.log(data)
        }
    })
}
//展示提示信息
function showMsg(msg) {
    $("#msg").html("提示："+msg);
}
//点击发送修改session 然后刷新
function toFolder(tofway_id){
    $.ajax({
        url:"/bus/toFolderAJAX.do",
        type:"post",
        data:{company_id:company_id,tofway_id:tofway_id},
        success:function (data) {
            if(data=='FAIL'){
                alert("请重试");
            }else {
                load();
            }
        },
        error: function () {
            alert("发生异常，请重试");
        }
    });
}
