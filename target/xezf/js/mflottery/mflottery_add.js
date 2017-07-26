var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		mfLottery:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../mflottery/info/"+id, function(r){
                vm.mfLottery = r.mfLottery;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.mfLottery.id == null ? "../mflottery/save" : "../mflottery/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mfLottery),
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