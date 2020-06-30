package by.epam.mtlcwtchr.ecafe.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class getTimeCustomTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        try(final JspWriter out = pageContext.getOut() ){
            out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm Z").format(new Date()));
            out.flush();
        } catch (IOException ignored) { }
        return SKIP_BODY;
    }
}
