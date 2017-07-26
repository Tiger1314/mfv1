var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfSource:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mfsource/info/"+id, function(r){
                vm.mfSource = r.mfSource;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfSource.id == null ? "../mfsource/save" : "../mfsource/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfSource),
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