package ua.air.epam.customtag;

import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

public class TagHandler extends SimpleTagSupport {
	private static Logger logger = Logger.getLogger(TagHandler.class);
	@Override
	public void doTag() throws JspException {
		try {
			getJspContext().getOut().print(Calendar.getInstance().getTime());
		} catch (Exception e) {
			logger.error("CustomTag Exception" , e);
			throw new SkipPageException();
		}
	}
}