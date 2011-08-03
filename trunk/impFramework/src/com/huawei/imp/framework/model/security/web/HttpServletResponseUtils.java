/**
 * 
 */
package com.huawei.imp.framework.model.security.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.huawei.imp.framework.utils.Constant;

/**
 * <p>
 * 
 * </p>
 * 
 * @see
 * @author aohai.li
 * @version FRAMEWORK2, 2010-8-12
 * @since FRAMEWORK2
 */
public class HttpServletResponseUtils implements Constant {

	public static void writeResponse(final String ret,
			HttpServletResponse response) throws IOException {
		response.reset();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
		pw.write(LINE_SEPARATOR);
		pw.write("<html>");
		pw.write(LINE_SEPARATOR);
		pw.write(BLANK);
		pw.write("<head>");
		pw.write(LINE_SEPARATOR);
		pw.write(BLANK);
		pw.write("<title>warning</title>");
		pw.write(LINE_SEPARATOR);
		pw.write(BLANK);
		pw.write("</head>");
		pw.write(LINE_SEPARATOR);
		pw.write(BLANK);
		pw.write("<body>");
		pw.write(LINE_SEPARATOR);
		pw.write(BLANK);
		pw.write(BLANK);
		pw.write(ret);
		pw.write(LINE_SEPARATOR);
		pw.write(BLANK);
		pw.write("</body>");
		pw.write(BLANK);
		pw.write(BLANK);
		pw.write(LINE_SEPARATOR);
		pw.write("</html>");
		pw.close();
	}
}
