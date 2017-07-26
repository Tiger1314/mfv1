/**
 * Created by admin on 2017/6/13.
 */
var id = T.p("id");
$(function () {
    $("#jqGrid").jqGrid({
        url: '../mfcomplaints/listhouse',
        postData: {'houseId': id},
        datatype: "json",
        colModel: [
            { label: '举报人', name: 'nickname', width: 80, key:true, formatter: function(value, options, row){

                var openId = row['openId'];

                var str = '<a href="user_view.html?id=' + openId +'">' + decodeURI(value) + '</a>';

                return str;
            } },
            { label: '举报类型', name: 'comType', width: 80, formatter: function (value, options, row) {
                var str = '';

                if(value == 1){
                    str = '虚假房源';
                }
                else{
                    str = '经纪人房源';
                }

                return str;
            } },
            { label: '举报时间', name: 'createTime', width: 80 },
            { label: '历史举报次数', name: 'comcount', width: 80 }
        ],
        viewrecords: true,
        height: 400,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
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
        mfHouseInfo: {}
    },
    methods: {
        getInfo: function(id){
            $.get("../mfhouseinfo/info/"+id, function(r){
                vm.mfHouseInfo = r.mfHouseInfo;
            });
        },
        back: function (event) {
            history.go(-1);
        }
    },
    created: function () {
        this.getInfo(id);
    }
});
