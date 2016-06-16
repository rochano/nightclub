package com.nightclub.custom.ckfinder.connector;

import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckfinder.connector.ConnectorServlet;
import com.ckfinder.connector.configuration.ConfigurationFactory;
import com.ckfinder.connector.configuration.Events;
import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.data.BeforeExecuteCommandEventArgs;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.handlers.command.Command;
import com.ckfinder.connector.handlers.command.CopyFilesCommand;
import com.ckfinder.connector.handlers.command.CreateFolderCommand;
import com.ckfinder.connector.handlers.command.DeleteFilesCommand;
import com.ckfinder.connector.handlers.command.DeleteFolderCommand;
import com.ckfinder.connector.handlers.command.DownloadFileCommand;
import com.ckfinder.connector.handlers.command.ErrorCommand;
import com.ckfinder.connector.handlers.command.FileUploadCommand;
import com.ckfinder.connector.handlers.command.GetFilesCommand;
import com.ckfinder.connector.handlers.command.GetFoldersCommand;
import com.ckfinder.connector.handlers.command.IPostCommand;
import com.ckfinder.connector.handlers.command.InitCommand;
import com.ckfinder.connector.handlers.command.MoveFilesCommand;
import com.ckfinder.connector.handlers.command.QuickUploadCommand;
import com.ckfinder.connector.handlers.command.RenameFileCommand;
import com.ckfinder.connector.handlers.command.RenameFolderCommand;
import com.ckfinder.connector.handlers.command.ThumbnailCommand;
import com.ckfinder.connector.handlers.command.XMLCommand;
import com.ckfinder.connector.handlers.command.XMLErrorCommand;
import com.nightclub.custom.ckfinder.connector.handlers.command.CustomFileUploadCommand;

public class CustomConnectorServlet extends ConnectorServlet {
	private Exception startException;
	private static final long serialVersionUID = 2960665641425153638L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		getResponse(request, response, false);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		getResponse(request, response, true);
	}

	private void getResponse(HttpServletRequest request,
			HttpServletResponse response, boolean post) throws ServletException {
		if ((this.startException != null)
				&& (Boolean.valueOf(getServletConfig()
						.getInitParameter("debug")).booleanValue())) {
			throw new ServletException(this.startException);
		}
		String command = request.getParameter("command");
		IConfiguration configuration = null;
		try {
			configuration = ConfigurationFactory.getInstace().getConfiguration(
					request);
			if (configuration == null) {
				throw new Exception(
						"Configuration wasn't initialized correctly. Check server logs.");
			}
		} catch (Exception e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))
					.booleanValue()) {
				Logger.getLogger(CustomConnectorServlet.class.getName())
						.log(Level.SEVERE,
								"Configuration wasn't initialized correctly. Check server logs.",
								e);
			}
			throw new ServletException(e);
		}
		try {
			if ((command == null) || (command.equals(""))) {
				throw new ConnectorException(10, false);
			}
			configuration.setDebugMode(Boolean.valueOf(
					getServletConfig().getInitParameter("debug"))
					.booleanValue());
			boolean isNativeCommand;
			if (CommandHandlerEnum.contains(command.toUpperCase())) {
				isNativeCommand = true;
				CommandHandlerEnum cmd = CommandHandlerEnum.valueOf(command
						.toUpperCase());
				if ((((cmd.getCommand() instanceof IPostCommand)) || (post))
						&& (!CommandHandlerEnum.FILEUPLOAD.equals(cmd))
						&& (!CommandHandlerEnum.QUICKUPLOAD.equals(cmd))) {
					checkPostRequest(request);
				}
			} else {
				isNativeCommand = false;
			}
			BeforeExecuteCommandEventArgs args = new BeforeExecuteCommandEventArgs();
			args.setCommand(command);
			args.setRequest(request);
			args.setResponse(response);
			if (configuration.getEvents() != null) {
				if (configuration.getEvents().run(
						Events.EventTypes.BeforeExecuteCommand, args,
						configuration)) {
					if (!isNativeCommand) {
						command = null;
					}
					executeNativeCommand(command, request, response,
							configuration, isNativeCommand);
				}
			} else {
				if (!isNativeCommand) {
					command = null;
				}
				executeNativeCommand(command, request, response, configuration,
						isNativeCommand);
			}
		} catch (IllegalArgumentException e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))
					.booleanValue()) {
				Logger.getLogger(CustomConnectorServlet.class.getName()).log(
						Level.SEVERE, "Couldn't execute native command.", e);
				response.reset();
				throw new ServletException(e);
			}
			handleError(new ConnectorException(10, false), configuration,
					request, response, command);
		} catch (ConnectorException e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))
					.booleanValue()) {
				Logger.getLogger(CustomConnectorServlet.class.getName()).log(
						Level.SEVERE, e.getErrorMessage(),
						e.getException() != null ? e.getException() : e);
				response.reset();
				throw new ServletException(e.getException());
			}
			handleError(e, configuration, request, response, command);
		}
	}

	private void executeNativeCommand(String command,
			HttpServletRequest request, HttpServletResponse response,
			IConfiguration configuration, boolean isNativeCommand)
			throws IllegalArgumentException, ConnectorException {
		if (isNativeCommand) {
			CommandHandlerEnum cmd = CommandHandlerEnum.valueOf(command
					.toUpperCase());
			Logger.getLogger(CustomConnectorServlet.class.getName()).log(
					Level.INFO, "command.toUpperCase() >> " + command.toUpperCase());
			cmd.execute(request, response, configuration, getServletContext(),
					new Object[0]);
		} else {
			throw new ConnectorException(10, false);
		}
	}

	private void checkPostRequest(HttpServletRequest request)
			throws ConnectorException {
		if ((request.getParameter("CKFinderCommand") == null)
				|| (!request.getParameter("CKFinderCommand").equals("true"))) {
			throw new ConnectorException(109, true);
		}
	}

	private void handleError(ConnectorException e,
			IConfiguration configuration, HttpServletRequest request,
			HttpServletResponse response, String currentCommand)
			throws ServletException {
		try {
			if ((currentCommand != null) && (!currentCommand.equals(""))) {
				Command command = CommandHandlerEnum.valueOf(
						currentCommand.toUpperCase()).getCommand();
				if ((command instanceof XMLCommand)) {
					CommandHandlerEnum.XMLERROR.execute(request, response,
							configuration, getServletContext(),
							new Object[] { e });
				} else {
					CommandHandlerEnum.ERROR.execute(request, response,
							configuration, getServletContext(),
							new Object[] { e });
				}
			} else {
				CommandHandlerEnum.XMLERROR.execute(request, response,
						configuration, getServletContext(), new Object[] { e });
			}
		} catch (Exception e1) {
			throw new ServletException(e1);
		}
	}

	private static enum CommandHandlerEnum {
		INIT(new InitCommand()), GETFOLDERS(new GetFoldersCommand()), GETFILES(
				new GetFilesCommand()), THUMBNAIL(new ThumbnailCommand()), DOWNLOADFILE(
				new DownloadFileCommand()), CREATEFOLDER(
				new CreateFolderCommand()), RENAMEFILE(new RenameFileCommand()), RENAMEFOLDER(
				new RenameFolderCommand()), DELETEFOLDER(
				new DeleteFolderCommand()), COPYFILES(new CopyFilesCommand()), MOVEFILES(
				new MoveFilesCommand()), DELETEFILES(new DeleteFilesCommand()), FILEUPLOAD(
				new CustomFileUploadCommand()), QUICKUPLOAD(new QuickUploadCommand()), XMLERROR(
				new XMLErrorCommand()), ERROR(new ErrorCommand());

		private Command command;
		private static final HashSet<String> enumValues = new HashSet();

		private CommandHandlerEnum(Command command1) {
			this.command = command1;
		}

		private static void setEnums() {
			for (CommandHandlerEnum enumValue : CommandHandlerEnum.values()) {
				enumValues.add(enumValue.name());
			}
		}

		public static boolean contains(String enumValue) {
			if (enumValues.isEmpty()) {
				setEnums();
			}
			for (String value : enumValues) {
				if (value.equals(enumValue)) {
					return true;
				}
			}
			return false;
		}

		private void execute(HttpServletRequest request,
				HttpServletResponse response, IConfiguration configuration,
				ServletContext sc, Object... params) throws ConnectorException {
			Command com = null;
			try {
				com = (Command) this.command.getClass().newInstance();
			} catch (IllegalAccessException e1) {
				throw new ConnectorException(10);
			} catch (InstantiationException e1) {
				throw new ConnectorException(10);
			}
			if (com == null) {
				throw new ConnectorException(10);
			}
			com.runCommand(request, response, configuration, params);
		}

		public Command getCommand() {
			return this.command;
		}
	}
}
