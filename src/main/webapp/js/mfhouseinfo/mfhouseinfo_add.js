var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfHouseInfo:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mfhouseinfo/info/"+id, function(r){
                vm.mfHouseInfo = r.mfHouseInfo;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfHouseInfo.id == null ? "../mfhouseinfo/save" : "../mfhouseinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfHouseInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});