if (!com)
	var com = {}
if (!com.corejsf) {
	com.corejsf = {
		showProgress : function(data) {

			var inputId = data.source.id
			var progressbarId = inputId.substring(0, inputId.length
					- "name".length)
					+ "pole";

			progressbarId = com.corejsf.selectorEscape(progressbarId);

			if (data.status == "begin")
				$('#' + progressbarId ).show();
			else if (data.status == "success")
				$('#' + progressbarId ).hide();
		},

		selectorEscape : function(val){
			return val.replace(/[ !"#$%&'()*+,.\/:;<=>?@\[\\\]^`{|}~]/g, '\\$&');
		}
	}
}