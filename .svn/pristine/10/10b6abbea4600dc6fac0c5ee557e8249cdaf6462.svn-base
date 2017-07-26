$(function () {
    $("#jqGrid").jqGrid({
        url: '../mfhouseinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '联系电话', name: 'phone', width: 80 }, 			
			{ label: '联系人', name: 'contact', width: 80 }, 			
			{ label: '联系地址', name: 'address', width: 80 }, 			
			{ label: '房源户型', name: 'unit', width: 80 }, 			
			{ label: '房源类型，1为住宅，2为写字楼', name: 'houseType', width: 80 }, 			
			{ label: '信息类型，1为出售，2为出租，3为求购，4为求租', name: 'infoType', width: 80 }, 			
			{ label: '单价（元/㎡或元/月）', name: 'unitPrice', width: 80 }, 			
			{ label: '总价（万）', name: 'totalPrice', width: 80 }, 			
			{ label: '面积（㎡）', name: 'area', width: 80 }, 			
			{ label: '标题', name: 'title', width: 80 }, 			
			{ label: '介绍', name: 'introduction', width: 80 }, 			
			{ label: '图片，多张逗号","分割', name: 'pics', width: 80 }, 			
			{ label: '来源类型ID', name: 'sourceId', width: 80 }, 			
			{ label: '来源主页链接（爬虫使用）', name: 'profile', width: 80 }, 			
			{ label: '来源主页链接（前端展示）', name: 'url', width: 80 }, 			
			{ label: '发布时间', name: 'publicTime', width: 80 }, 			
			{ label: '业务字段，默认为0，-1为虚假房源，1被举报房源', name: 'status', width: 80 }, 			
			{ label: '业务字段，默认为0，1已经推送', name: 'pushedStatus', width: 80 }, 			
			{ label: '对应mf_area省code', name: 'provinceCode', width: 80 }, 			
			{ label: '对应mf_area城市code', name: 'cityCode', width: 80 }, 			
			{ label: '对应mf_area区code', name: 'districtCode', width: 80 }, 			
			{ label: '对应mf_area区code', name: 'businessCode', width: 80 }, 			
			{ label: '标签，多个逗号分割', name: 'tags', width: 80 }, 			
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
			
			location.href = "mfhouseinfo_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mfhouseinfo/delete",
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