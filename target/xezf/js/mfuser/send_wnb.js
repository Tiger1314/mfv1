var ids = T.p("ids");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"赠送蜗牛币",
        sendwn:{
            tzFlag: 0,
			ids: ''
		}
	},
	methods: {
		saveOrUpdate: function (event) {
			vm.sendwn.ids = ids;
			var url = "../mfuser/send";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.sendwn),
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