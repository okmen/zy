/**
 * 绑定enter键
 */
$(function() {
	$("#searchTxt").bind("keydown", EnterSearch);
})
function EnterSearch(eventobject) {
	if (eventobject.keyCode == 13) {
		search();
	}
}

/**
 * 搜索
 */
function search() {
	var key = $("#searchTxt").val();
	$.ajax({
		url : 'search/keyword',
		async : false,// false不打引号
		type : 'POST',// POST最好大写
		dataType : 'json',// 一定注意T是大写的
		data : {
			key : key
		},
		success : function(data) {
		},
		error : function(data) {
		}
	});
	window.location.href = "search?key=" + key;
}