$(function () {
    $("#jqGrid").jqGrid({
        url: '../mfuser/list',
        datatype: "json",
        colModel: [
			{ label: '唯一标识', name: 'openId', width: 150, key: true },
			{ label: '会员时间', name: 'expireDate', width: 80 },
			{ label: '昵称', name: 'nickname', width: 80, formatter: function(value, options, row){
                return decodeURI(value);
			} },
            { label: '国家', name: 'country', width: 30 },
			{ label: '省', name: 'province', width: 30 },
            { label: '市', name: 'city', width: 30 },
			{ label: '蜗牛币', name: 'wnb', width: 30 },
			{ label: '勿扰模式', name: 'wrStatus', width: 30, formatter: function(value, options, row){
        		var str = "";

        		if(value == 1){
        			str = "是";
				}
				else{
        			str = "否";
				}

        		return str;
			} },
			{ label: '最后交互时间', name: 'lastTime', width: 80 },
			{ label: '关注时间', name: 'createTime', width: 80 }
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50,100],
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
        nickname: ''
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "mfuser_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mfuser/delete",
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
		query: function(){
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'nickname': encodeURI(vm.nickname)},
                page:1
            }).trigger("reloadGrid");
		},
        sendwnb: function () {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }
            location.href = "send_wnb.html?ids="+ids;
        }
	}
});