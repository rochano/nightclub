package com.nightclub.util;

import java.util.HashMap;

public class FileUtil {
	private static final HashMap<String, String> imgMimeTypeMap = new HashMap(57);
  
	static {
		imgMimeTypeMap.put(".art", "image/x-jg");
		imgMimeTypeMap.put(".bm", "image/bmp");
		imgMimeTypeMap.put(".bmp", "image/bmp");
		imgMimeTypeMap.put(".dwg", "image/vnd.dwg");
		imgMimeTypeMap.put(".dxf", "image/vnd.dwg");
		imgMimeTypeMap.put(".fif", "image/fif");
		imgMimeTypeMap.put(".flo", "image/florian");
		imgMimeTypeMap.put(".fpx", "image/vnd.fpx");
		imgMimeTypeMap.put(".g3", "image/g3fax");
		imgMimeTypeMap.put(".gif", "image/gif");
		imgMimeTypeMap.put(".ico", "image/x-icon");
		imgMimeTypeMap.put(".ief", "image/ief");
		imgMimeTypeMap.put(".iefs", "image/ief");
		imgMimeTypeMap.put(".jut", "image/jutvision");
		imgMimeTypeMap.put(".mcf", "image/vasa");
		imgMimeTypeMap.put(".nap", "image/naplps");
		imgMimeTypeMap.put(".naplps", "image/naplps");
		imgMimeTypeMap.put(".nif", "image/x-niff");
		imgMimeTypeMap.put(".niff", "image/x-niff");
		imgMimeTypeMap.put(".pct", "image/x-pict");
		imgMimeTypeMap.put(".pcx", "image/x-pcx");
		imgMimeTypeMap.put(".pgm", "image/x-portable-graymap");
		imgMimeTypeMap.put(".pic", "image/pict");
		imgMimeTypeMap.put(".pict", "image/pict");
		imgMimeTypeMap.put(".pm", "image/x-xpixmap");
		imgMimeTypeMap.put(".png", "image/png");
		imgMimeTypeMap.put(".pnm", "image/x-portable-anymap");
		imgMimeTypeMap.put(".ppm", "image/x-portable-pixmap");
		imgMimeTypeMap.put(".ras", "image/x-cmu-raster");
		imgMimeTypeMap.put(".rast", "image/cmu-raster");
		imgMimeTypeMap.put(".rf", "image/vnd.rn-realflash");
		imgMimeTypeMap.put(".rgb", "image/x-rgb");
		imgMimeTypeMap.put(".rp", "  image/vnd.rn-realpix");
		imgMimeTypeMap.put(".svf", "image/vnd.dwg");
		imgMimeTypeMap.put(".svf", "image/x-dwg");
		imgMimeTypeMap.put(".tiff", "image/tiff");
		imgMimeTypeMap.put(".turbot", "image/florian");
		imgMimeTypeMap.put(".wbmp", "image/vnd.wap.wbmp");
		imgMimeTypeMap.put(".xif", "image/vnd.xiff");
		imgMimeTypeMap.put(".xpm", "image/x-xpixmap");
		imgMimeTypeMap.put(".x-png", "image/png");
		imgMimeTypeMap.put(".xwd", "image/x-xwindowdump");
	}
	
	
}
