var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfSourceProfile:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mfsourceprofile/info/"+id, function(r){
                vm.mfSourceProfile = r.mfSourceProfile;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfSourceProfile.id == null ? "../mfsourceprofile/save" : "../mfsourceprofile/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfSourceProfile),
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