$(document).ready(function() {
	var options = {
			beforeSend : function() {
				var relatedId = $("#relatedId").val();
				var html = "<div class='progressbar'></div><div class='percent'>0%</div>";
				$("#" + relatedId).html(html);
				$("#" + relatedId + " .progressbar").width('0%');
			},
			uploadProgress : function(event, position, total, percentComplete) {
				var relatedId = $("#relatedId").val();
				$("#" + relatedId + " .progressbar").width(percentComplete + "%");
				$("#" + relatedId + " .percent").html(percentComplete + "%");
			},
			success : function() {
				var relatedId = $("#relatedId").val();
				$("#" + relatedId + " .progressbar").width("100%");
				$("#" + relatedId + " .percent").html("100%");
			},
			complete : function(response) {
				var data = JSON.parse(response.responseText);
				var image = "<img src='" + (data.path + data.fileName) + "' />";
				var relatedId = $("#relatedId").val();
				$("#" + relatedId).html(image);
				$("#" + relateUploadField[relatedId]).val(data.fileName);
				$("#myfile").val("");
			}, 
			error : function() {
				$("#" + relatedId).html("<font color='red'>Error: unable to upload files</font>");
			}
	};
	$("#uploadForm").ajaxForm(options);
});