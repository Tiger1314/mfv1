$(function () {
    $("#jqGrid").jqGrid({
        url: '../mfcomplaints/list',
        datatype: "json",
        colModel: [
			{ label: '房源ID', name: 'houseId', width: 80, key:true },
			{ label: '房源标题', name: 'houseTitle', width: 80, formatter: function (value, options, row) {
                var houseId = row['houseId'];
                var str = '<a href="house_view.html?id=' + houseId +'">' + value + '</a>'
                return str;
            } },
			{ label: '房源连接', name: 'houseUrl', width: 80, formatter:function (value, options, row) {
				var str = '';

				if(value != null && value != ''){
					str = '<a target="_blank" href="' + value + '">查询</a>';
				}

				return str;
            } },
			{ label: '举报次数', name: 'comcount', width: 80 }
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
		update: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要设置为可疑房源？', function(){
                $.ajax({
                    type: "POST",
                    url: "../mfcomplaints/complaints",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定驳回房源投诉？', function(){
				$.ajax({
					type: "POST",
				    url: "../mfcomplaints/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});