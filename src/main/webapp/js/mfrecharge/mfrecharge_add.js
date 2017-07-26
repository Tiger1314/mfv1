var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfRecharge:{
            type: 1
		}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mfrecharge/info/"+id, function(r){
                vm.mfRecharge = r.mfRecharge;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfRecharge.id == null ? "../mfrecharge/save" : "../mfrecharge/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfRecharge),
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