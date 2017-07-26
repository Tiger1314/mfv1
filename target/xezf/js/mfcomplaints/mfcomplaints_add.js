var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfComplaints:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mfcomplaints/info/"+id, function(r){
                vm.mfComplaints = r.mfComplaints;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfComplaints.id == null ? "../mfcomplaints/save" : "../mfcomplaints/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfComplaints),
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