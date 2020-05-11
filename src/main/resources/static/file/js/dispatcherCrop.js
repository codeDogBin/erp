$(function () {


    initPaginationItem('#pagination-container2', 2, [], render)
    initPaginationItem('#pagination-container3', 3, [], render)
    initPaginationItem('#pagination-container', 1, [], render)

    //用户名查询
    $("#searchCustomer").on("click", function () {
        var name = $("#customerInput").val().trim();
        // if (name === null || name === "") return;
        $.ajax({
            type: "GET",
            url: "/bus/findCustomerByNameLikeAJAX.do",
            data: {custmername: name},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.length == 0) {
                    $("#data-container").html("<h3 style='margin-top:20px;'>暂无用户数据或用户权限不足</h3>");
                    //2，3清空
                    initPaginationItem('#pagination-container2', 2, [], render)
                    initPaginationItem('#pagination-container3', 3, [], render)
                    return;
                }
                initPaginationItem('#pagination-container', 1, data, render)
                //清空2，3
                initPaginationItem('#pagination-container2', 2, [], render)
                initPaginationItem('#pagination-container3', 3, [], render)
            },
            error: function () {
                alert("发生异常，请重试");
            }
        });
    })

    //查询未绑定的公司
    $("#searchCorp").on("click",function () {
        if(indicator === null){
            alert("请先选择一个用户");
            return;
        }
        var companyname = $("#cropInput").val();
        // if(companyname === "" || companyname === null)return;
        $.ajax({
            type: "GET",
            url: "/bus/findUnbindCompanyByNameAJAX.do",
            data: {customerid: indicator,name: companyname},
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (data.length == 0) {
                    $("#data-container3").html("<h3 style='margin-top:20px;text-align: center;'>暂无用户数据或用户权限不足</h3>");
                    return;
                }
                initPaginationItem('#pagination-container3', 3, data, render3)
            },
            error: function () {
                alert("发生异常，请重试");
            }
        })
    })

    //解除绑定事件
    $("#unbindEvent").on("click",function () {
        if(indicator == null){
            alert("请先绑定一个用户");
            return;
        }

        var companies_id = [];
        var companies = $(".bindInp:checked(:checked)");

        if(companies.length === 0){
            alert("您没有选中需要解绑的公司");
            return;
        }

        $.each(companies,function (index,item) {
            companies_id.push(parseInt($(item).val()));
        })

        $.ajax({
            type: "POST",
            url: "/bus/unbindCustomerCompanyAJAX.do",
            traditional: true,
            data: {customerid: indicator,companies_id: companies_id},
            success: function (data) {
                if(data === "OK"){
                    alert("解除绑定成功");
                    //更新数据
                    bindEventToRadio(indicator);
                }else{
                    alert("出现异常，请重试");
                }
            },
            error: function () {
                alert("发生异常，请重试");
            }
        });
    })



    //添加绑定事件
    $("#bindEvent").on("click",function () {
        if(indicator == null){
            alert("请先绑定一个用户");
            return;
        }
        //找到当前渲染的所有的company_id
        var companies_id = [];
        var companies = $(".unbindInp:checked(:checked)");

        if(companies.length === 0){
            alert("您没有选中需要绑定的公司");
            return;
        }

        $.each(companies,function (index,item) {
            companies_id.push(parseInt($(item).val()));
        })

        $.ajax({
            type: "POST",
            url: "/bus/bindCustomerCompanyAJAX.do",
            traditional: true,
            data: {customerid: indicator,companies_id: companies_id},
            success: function (data) {
                if(data === "OK"){
                    alert("添加绑定成功");
                    //更新数据
                    $("#searchCorp").click();
                    bindEventToRadio(indicator);
                }else{
                    alert("出现异常，请重试");
                }
            },
            error: function () {
                alert("发生异常，请重试");
            }
        });
    })
})

var indicator = null;

//input按钮事件函数
function bindEventToRadio(value) {
    indicator = value;
    $.ajax({
        type: "GET",
        url: "/bus/findCustomerCompanyAJAX.do",
        data: {customerid: value},
        dataType: "json",
        success: function (data) {
            // console.log(data)
            if (data.length === 0) {
                $("#data-container2").html("<h3 style='margin-top:20px;text-align: center;'>暂无用户数据或用户权限不足</h3>");
                return;
            }
            initPaginationItem('#pagination-container2', 2, data, render2)
        },
        error: function () {
            alert("发生异常，请重试");
        }
    });
}


//通用渲染函数
function initPaginationItem(id, num, data, template) {
    num = num === 1 ? "" : num;
    $(id).pagination({
        dataSource: data,
        pageSize: 5,
        showGoInput: true,
        showGoButton: true,
        formatGoInput: '跳转:<%= input %>页',
        callback: function (data, pagination) {
            var html = template(data);
            $("#data-container" + num).html("");
            $("#data-container" + num).html(html);
        }
    })
}

//1的渲染函数
function render(data) {
    var html = '<ul class="customerList">';
    $.each(data, function (index, item) {
        //此处留去文件夹的链接
        html += '<li><span>' + item.customername + '</span><input type="radio" onclick="bindEventToRadio(this.value)" name="customerid" value="' + item.id + '"/></li>';
    });
    html += '</ul>';
    return html;
}

//2的渲染函数
function render2(data) {
    var html = '<ul class="customerList2">';
    $.each(data, function (index, item) {
        //此处留去文件夹的链接
        html += '<li><span>' + item.name + '</span><input class="bindInp" type="checkbox" value="' + item.id + '"></input></li>';
    });
    html += '</ul>';
    return html;
}

//3的渲染函数（未完成：添加绑定功能和自动刷新功能）
function render3(data) {
    var html = '<ul class="customerList3">';
    $.each(data, function (index, item) {
        html += '<li><span>' + item.name + '</span><input class="unbindInp" type="checkbox" value="' + item.id + '"></input></li>';
    });
    html += '</ul>';
    return html;
}