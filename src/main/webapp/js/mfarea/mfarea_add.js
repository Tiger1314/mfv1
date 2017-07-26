var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfArea:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mfarea/info/"+id, function(r){
                vm.mfArea = r.mfArea;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfArea.id == null ? "../mfarea/save" : "../mfarea/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfArea),
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