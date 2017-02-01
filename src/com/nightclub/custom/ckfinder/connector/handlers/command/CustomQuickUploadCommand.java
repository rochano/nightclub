package com.nightclub.custom.ckfinder.connector.handlers.command;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.ckfinder.connector.data.ResourceType;
import com.ckfinder.connector.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nightclub.custom.ckfinder.connector.CustomConnectorServlet;

public class CustomQuickUploadCommand extends CustomFileUploadCommand {
	protected void handleOnUploadCompleteResponse(OutputStream out,
			String errorMsg) throws IOException {
		Logger.getLogger(CustomConnectorServlet.class.getName()).log(
				Level.INFO, "CustomQuickUploadCommand handleOnUploadCompleteResponse ");
		if ((this.responseType != null)
				&& (this.responseType.equalsIgnoreCase("json"))) {
			handleJSONResponse(out, errorMsg, null);
		} else {
			out.write("<script type=\"text/javascript\">".getBytes("UTF-8"));
			out.write("window.parent.OnUploadCompleted(".getBytes("UTF-8"));
			out.write(("" + this.errorCode + ", ").getBytes("UTF-8"));
			if (this.uploaded) {
				out.write(

				("'"
						+ ((ResourceType) this.configuration.getTypes().get(
								this.type)).getUrl()
						+ this.currentFolder
						+ FileUtils.backupWithBackSlash(
								FileUtils.encodeURIComponent(this.newFileName),
								"'") + "', ").getBytes("UTF-8"));
				out.write(("'"
						+ FileUtils.backupWithBackSlash(this.newFileName, "'") + "', ")
						.getBytes("UTF-8"));
			} else {
				out.write("'', '', ".getBytes("UTF-8"));
			}
			out.write("''".getBytes("UTF-8"));
			out.write(");".getBytes("UTF-8"));
			out.write("</script>".getBytes("UTF-8"));
		}
	}

	protected void handleOnUploadCompleteCallFuncResponse(OutputStream out,
			String errorMsg, String path) throws IOException {
		Logger.getLogger(CustomConnectorServlet.class.getName()).log(
				Level.INFO, "CustomQuickUploadCommand handleOnUploadCompleteCallFuncResponse ");
		if ((this.responseType != null)
				&& (this.responseType.equalsIgnoreCase("json"))) {
			handleJSONResponse(out, errorMsg, path);
		} else {
			out.write("<script type=\"text/javascript\">".getBytes("UTF-8"));
			this.ckEditorFuncNum = this.ckEditorFuncNum
					.replaceAll("[^\\d]", "");
			
			Logger.getLogger(CustomConnectorServlet.class.getName()).log(
					Level.INFO, "path >> " + path + ", this.newFileName >> " + this.newFileName);
			out.write(

			("window.parent.CKEDITOR.tools.callFunction("
					+ this.ckEditorFuncNum
					+ ", '"
					+ path
					+ FileUtils.backupWithBackSlash(
							(this.newFileName), "'")
					+ "', '" + errorMsg + "');").getBytes("UTF-8"));
			out.write("</script>".getBytes("UTF-8"));
		}
	}

	protected boolean checkFuncNum() {
		return this.ckEditorFuncNum != null;
	}

	public void setResponseHeader(HttpServletResponse response,
			ServletContext sc) {
		response.setCharacterEncoding("utf-8");
		if ((this.responseType != null)
				&& (this.responseType.equalsIgnoreCase("json"))) {
			response.setContentType("application/json");
		} else {
			response.setContentType("text/html");
		}
	}

	private void handleJSONResponse(OutputStream out, String errorMsg,
			String path) throws IOException {
		Logger.getLogger(CustomConnectorServlet.class.getName()).log(
				Level.INFO, "CustomQuickUploadCommand handleJSONResponse ");
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> jsonObj = new HashMap();

		jsonObj.put("fileName", this.newFileName);
		jsonObj.put("uploaded", this.uploaded ? new Integer(1) : new Integer(0));
		if (this.uploaded) {
			if ((path != null) && (!path.equals(""))) {
				jsonObj.put(
						"url",
						path
								+

								FileUtils.backupWithBackSlash(FileUtils
										.encodeURIComponent(this.newFileName),
										"'"));
			} else {
				jsonObj.put(
						"url",

						((ResourceType) this.configuration.getTypes().get(
								this.type)).getUrl()
								+ this.currentFolder
								+

								FileUtils.backupWithBackSlash(FileUtils
										.encodeURIComponent(this.newFileName),
										"'"));
			}
		}
		if ((errorMsg != null) && (!errorMsg.equals(""))) {
			Map<String, Object> jsonErrObj = new HashMap();
			jsonErrObj.put("number", Integer.valueOf(this.errorCode));
			jsonErrObj.put("message", errorMsg);
			jsonObj.put("error", jsonErrObj);
		}
		out.write(gson.toJson(jsonObj).getBytes("UTF-8"));
	}
}
