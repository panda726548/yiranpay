// 打开遮罩层
function loading (message) {
	$.blockUI({ message: '<div class="loaderbox"><img src="/images/loading.gif" width="16px" height="16px"> ' + message + '</div>' });
};
// 关闭遮罩层
function closeLoading() {
	setTimeout(function(){
		$.unblockUI();
	}, 50);
};
// 重新加载
function reload() {
	parent.location.reload();
}