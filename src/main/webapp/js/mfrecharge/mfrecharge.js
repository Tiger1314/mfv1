$(function () {
    $("#jqGrid").jqGrid({
        url: '../mfrecharge/list',
        datatype: "json",
        colModel: [
			{ label: '标题', name: 'title', width: 80 },
			{ label: '原价', name: 'oldPrice', width: 80 },
			{ label: '折扣信息', name: 'discount', width: 80 }, 			
			{ label: '天数', name: 'days', width: 80 }, 			
			{ label: '价格', name: 'price', width: 80 }, 			
			{ label: '充值类型', name: 'type', width: 80, formatter: function(value, options, row){
        		var str = "";

				if(value == 1){
					str = "包月";
				}
				else if(value == 2){
					str = "蜗牛币";
				}

        		return str;
			} },
			{ label: '蜗牛币', name: 'wnb', width: 80 },
			{ label: '', name: 'createTime', width: 80 }
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
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "mfrecharge_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mfrecharge/delete",
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