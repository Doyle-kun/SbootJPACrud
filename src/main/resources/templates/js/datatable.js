$(document).ready( function () {
	var data = [];
	 var table = $('#employeesTable').DataTable({
		    "bProcessing": true,
			"sAjaxSource": "/employee",
			"sAjaxDataProp": "",
		    "aoColumns": [
		        { "mData": "Title" },
		        { "mData": "Price" }
	 })
});