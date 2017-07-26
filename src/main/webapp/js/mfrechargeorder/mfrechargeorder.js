$(function () {
    $("#jqGrid").jqGrid({
        url: '../mfrechargeorder/list',
        datatype: "json",
        colModel: [
			{ label: '订单号', name: 'orderNo', width: 80 },
			{ label: '消费', name: 'des', width: 80 },
			{ label: '消费用户', name: 'nickname', width: 80, formatter: function(value, options, row){
                return decodeURI(value);
            } },
			{ label: '消费项目', name: 'title', width: 80 },
			{ label: '消费类型', name: 'operType', width: 80 },
            { label: '消费时间', name: 'createTime', width: 80 }
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
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
        nickname: '',
        orderType: 0
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "mfrechargeorder_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mfrechargeorder/delete",
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
		query: function (event) {
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'nickname': encodeURI(vm.nickname),'orderType': vm.orderType},
                page:1
            }).trigger("reloadGrid");
        }
	}
});