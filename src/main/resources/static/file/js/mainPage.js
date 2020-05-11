(function() {
    $(function () {
        load();
        $("#createBtn").on("click",function () {
            var companyname=$("#createCorp").val().trim();
            if(companyname == null || "" == companyname){
                return;
            }
            $.ajax({
                type: "GET",
                url: "/bus/createCompanyAJAX.do",
                data: {name: $("#createCorp").val().trim()},
                success: function (data) {
                    if(data === "OK"){
                        alert("创建成功");
                        load();
                    }else{
                        alert("重复创建或其它异常");
                    }
                },
                error: function (msg) {
                    alert("重复创建或其它异常");
                }
            });
        });

        $("#searchBtn").on("click",function () {
            $.ajax({
                type: "GET",
                url: "/bus/selectCompanyByNameIndexAJAX.do",
                data: {name: $("#searchInput").val().trim()},
                dataType: "json",
                success: function (data) {
                    initPaginationItem(data);
                }
            });
        });

        function load() {
            $.ajax({
                type: "GET",
                url: "/bus/toIndexAjax.do",
                dataType: "json",
                success: function (data) {
                    //1.添加用户名
                    $(".username").text(data.userName);
                    initPaginationItem(data);
                },
                error: function () {
                    alert("发生异常，请重试");
                }
            });
        }

        $("#createCorp").on("blur",function () {
            var name = $("#createCorp").val().trim();
            if(name === "" || name === null)return;
            $.ajax({
                type: "GET",
                url: "/bus/examineCompanyNameAJAX.do",
                data: {name: name},
                success: function (data) {
                    console.log(data);
                    if(data === "FAIL"){
                        alert("公司已存在");
                    }
                }
            });
        });
        
        function initPaginationItem(data) {
            $('#pagination-container').pagination({
                dataSource: data.companies,
                pageSize: 20,
                showGoInput: true,
                showGoButton: true,
                formatGoInput: '跳转:<%= input %>页',
                callback: function (data, pagination) {
                    var html = render(data);
                    $("#data-container").html("");
                    $("#data-container").html(html);
                }
            })
        }


        function render(data) {
            var html = '<table class="companyList"><tr><th>序号</th><th>公司名</th></tr>';
            $.each(data, function (index, item) {
                //此处留去文件夹的链接
                html += '<tr><td>' + (index+1) + '</td><td><a href="javascript:void(0);" onclick="toFolder('+item.id+')">' + item.name + '</a></td><td><a href="javascript:void(0);" shiro:hasPermission="company:rename" onclick="rename('+item.id+')">重命名</a></td></tr>';
            });
            html += '</table>';
            return html;
        }
    })
})();

//点击发送修改session 然后刷新
function toFolder(company_id){
    $.ajax({
        url:"/bus/toFolderAJAX.do",
        type:"post",
        data:{company_id:company_id,tofway_id:0},
        success:function (data) {
            if(data=='FAIL'){
                alert("请重试");
            }else {
                window.location.href="/bus/toFolder.do"
            }
        },
        error: function () {
            alert("发生异常，请重试");
        }
    });
}


function rename(companyid) {
    var name = prompt("请输入修改后的","");
    if(name === "" || name === null)return;
    $.ajax({
        type: "GET",
        url: "/bus/examineCompanyNameAJAX.do",
        data: {name: name},
        success: function (data) {
            if(data === "FAIL"){
                alert("公司已存在");
                return;
            }else {
                $.post("updateCompanyName",{name:name,companyid:companyid},function (res) {
                    if(res.code != 200){
                        alert("修改失败,请检查后重试")
                    }else{
                        window.location.reload();
                    }
                })
            }
        }
    });

}


