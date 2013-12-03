/* Copyright 2005 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://developer.sun.com/berkeley_license.html
 $Id: Util.java,v 1.5 2005/11/04 04:40:42 jenniferb Exp $ */

package com.sun.javaee.blueprints.components.ui.util;

import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.el.ValueExpression;
import javax.el.ExpressionFactory;

/**
 *
 * @author edburns
 */
public class Util {

    public static final String UTIL_LOGGER = "com.sun.javaee.blueprints";

    public static final String UI_LOG_STRINGS =
            "com.sun.javaee.blueprints.components.ui.LogStrings";

    public static final String UI_LOGGER = "com.sun.javaee.blueprints.components.ui.";

    // Log instance for this class
    private static Logger logger = getLogger(UTIL_LOGGER);


    public static Logger getLogger( String loggerName ) {
        return Logger.getLogger(loggerName, UI_LOG_STRINGS );
    }
    
        /**
     * This array contains attributes that have a boolean value in JSP,
     * but have have no value in HTML.  For example "disabled" or
     * "readonly". <P>
     *
     * @see renderBooleanPassthruAttributes
     */

    private static String booleanPassthruAttributes[] = {
        "disabled",
        "readonly",
        "ismap"
    };

    /**
     * This array contains attributes whose value is just rendered
     * straight to the content.  This array should only contain
     * attributes that require no interpretation by the Renderer.  If an
     * attribute requires interpretation by a Renderer, it should be
     * removed from this array.<P>
     *
     * @see renderPassthruAttributes
     */
    private static String passthruAttributes[] = {
        "accesskey",
        "alt",
        "cols",
        "height",
        "lang",
        "longdesc",
        "maxlength",
        "onblur",
        "onchange",
        "onclick",
        "ondblclick",
        "onfocus",
        "onkeydown",
        "onkeypress",
        "onkeyup",
        "onload",
        "onmousedown",
        "onmousemove",
        "onmouseout",
        "onmouseover",
        "onmouseup",
        "onreset",
        "onselect",
        "onsubmit",
        "onunload",
        "rows",
        "size",
        "tabindex",
        //"class",   PENDING(rlubke)  revisit this for JSFA105
        "title",
        "style",
        "width",
        "dir",
        "rules",
        "frame",
        "border",
        "cellspacing",
        "cellpadding",
        "summary",
        "bgcolor",
        "usemap",
        "enctype",
        "accept-charset",
        "accept",
        "target",
        "onsubmit",
        "onreset"
    };

//
// Constructors and Initializers    
//

    private Util() {
        throw new IllegalStateException();
    }

    /**
     * Render any boolean "passthru" attributes.
     */

    public static String renderBooleanPassthruAttributes(FacesContext context,
                                                         UIComponent component) {
        int i = 0, len = booleanPassthruAttributes.length;
        String value;
        boolean thisIsTheFirstAppend = true;
        StringBuffer renderedText = new StringBuffer();

        for (i = 0; i < len; i++) {
            if (null != (value = (String)
                component.getAttributes().get(booleanPassthruAttributes[i]))) {
                if (thisIsTheFirstAppend) {
                    // prepend ' '
                    renderedText.append(' ');
                    thisIsTheFirstAppend = false;
                }
                if (Boolean.valueOf(value).booleanValue()) {
                    renderedText.append(booleanPassthruAttributes[i] + ' ');
                }
            }
        }

        return renderedText.toString();
    }


    /**
     * Render any "passthru" attributes, where we simply just output the
     * raw name and value of the attribute.  This method is aware of the
     * set of HTML4 attributes that fall into this bucket.  Examples are
     * all the javascript attributes, alt, rows, cols, etc.  <P>
     *
     * @return the rendererd attributes as specified in the component.
     *         Padded with leading and trailing ' '.  If there are no passthru
     *         attributes in the component, return the empty String.
     */

    public static String renderPassthruAttributes(FacesContext context,
                                                  UIComponent component) {
        int i = 0, len = passthruAttributes.length;
        String value;
        boolean thisIsTheFirstAppend = true;
        StringBuffer renderedText = new StringBuffer();

        for (i = 0; i < len; i++) {
            if (null != (value = (String)
                component.getAttributes().get(passthruAttributes[i]))) {
                if (thisIsTheFirstAppend) {
                    // prepend ' '
                    renderedText.append(' ');
                    thisIsTheFirstAppend = false;
                }
                renderedText.append(passthruAttributes[i] + "=\"" + value +
                                    "\" ");
            }
        }

        return renderedText.toString();
    }

    public static ValueExpression getValueExpression(String exprString,
                                                     Class expectedType,
                                                     FacesContext context) {
        ExpressionFactory exprFactory =
                  context.getApplication().getExpressionFactory();
            ValueExpression ve =
                  exprFactory.createValueExpression(context.getELContext(),
                                                    exprString,
                                                    expectedType);
        return ve;
    }

    
    private static final String RENDERED_SCRIPT = "com.sun.javaee.blueprints.RENDERED_SCRIPT";        

    public static void renderMainScriptOnce(FacesContext context, 
            ResponseWriter writer,
            UIComponent component) throws IOException {
        // Only render the main script element once per request.
        if (!context.getExternalContext().getRequestMap().containsKey(RENDERED_SCRIPT)) {
            context.getExternalContext().getRequestMap().put(RENDERED_SCRIPT,
                    Boolean.TRUE);
            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
	    String src = "ajax-script.faces";
            writer.writeAttribute("src", src, null);
            
            writer.endElement("script");
        }
        
    }


}
