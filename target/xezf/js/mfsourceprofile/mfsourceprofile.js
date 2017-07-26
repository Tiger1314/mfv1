$(function () {
    $("#jqGrid").jqGrid({
        url: '../mfsourceprofile/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '来源ID', name: 'sourceId', width: 80 }, 			
			{ label: '对应mf_area的区code', name: 'districtCode', width: 80 }, 			
			{ label: '对应mf_area的区code', name: 'cityCode', width: 80 }, 			
			{ label: '对应mf_area的区code', name: 'provinceCode', width: 80 }, 			
			{ label: '对应house_type,同mf_house_info', name: 'houseType', width: 80 }, 			
			{ label: '对应info_type,同mf_house_info', name: 'infoType', width: 80 }, 			
			{ label: '对应区的profile', name: 'profile', width: 80 }, 			
			{ label: 'profile状态，1为有效的，-1为无效，默认是0', name: 'status', width: 80 }, 			
			{ label: '', name: 'createTime', width: 80 }, 			
			{ label: '', name: 'updateTime', width: 80 }			
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
			
			location.href = "mfsourceprofile_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mfsourceprofile/delete",
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