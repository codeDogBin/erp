function recoverFolder(value) {
    $.ajax({
        type: "GET",
        url: "/bus/recoverFolder.do",
        data: {fol_id: value},
        success: function(data) {
            if(data === "OK"){
                alert("恢复成功");
                load();
            }
        }
    });
}
function recoverFil(value) {
    $.ajax({
        type: "GET",
        url: "/bus/recoverFil.do",
        data: {fil_id: value},
        success: function(data) {
            if(data === "OK"){
                alert("恢复成功");
                load();
            }
        }
    });
}



$(function () {
    load();
})


function initPaginationItem(containnerId, paginationId, data, flag) {
    $(containnerId).pagination({
        dataSource: data,
        pageSize: 20,
        showGoInput: true,
        showGoButton: true,
        formatGoInput: '跳转:<%= input %>页',
        callback: function (data, pagination) {
            // template method of yourself
            var html = render(data);
            if(flag){
                html = render(data);
            }else{
                html = render2(data);
            }
            $(paginationId).html(html);
        }
    })
}
function load() {
    $.ajax({
        type: "GET",
        url: "/bus/getAllExpire.do",
        dataType: "json",
        success: function (data) {
            console.log(data);
            initPaginationItem("#data-container", "#pagination-container", data.folders,true);
            initPaginationItem("#data-container2", "#pagination-container2", data.files,false);
        }
    });

}

function render(data) {
    var html = '<table class="fileList"><tr><th>文件编号</th><th>资源链接</th><th><a href="' + "恢复链接" + '">恢复链接</a></th></tr>';
    $.each(data, function (index, item) {
        html += '<tr><td>'
            + (index+1) + '</td><td><a href="' + item.way + '">' +
            item.name + '</a></td><td><span class="recoverFolder" onclick="recoverFolder('+item.id+')"  value="' +
            item.id + '">恢复</span></td></tr>';
    });
    html += '</table>';
    return html;
}

function render2(data) {
    var html = '<table class="fileList"><tr><th>文件编号</th><th>资源链接</th><th><a href="' + "恢复链接" + '">恢复链接</a></th></tr>';
    $.each(data, function (index, item) {
        //此处留去文件夹的链接
        html += '<tr><td>'
            + (index+1) + '</td><td><a href="' + item.way + '">' +
            item.name + '</a></td><td><span class="recoverFile" onclick="recoverFil('+item.id+')" value="' +
            item.id + '">恢复</span></td></tr>';
    });
    html += '</table>';
    return html;
}