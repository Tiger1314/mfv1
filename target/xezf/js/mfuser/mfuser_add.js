var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfUser:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mfuser/info/"+id, function(r){
                vm.mfUser = r.mfUser;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfUser.id == null ? "../mfuser/save" : "../mfuser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfUser),
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