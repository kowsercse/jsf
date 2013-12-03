/*
 * AjaxValidatorComponent.java
 *
 * Created on May 31, 2005, 9:19 PM
 */

package com.sun.javaee.blueprints.components.ui.components;

import com.sun.javaee.blueprints.components.ui.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.el.*;


/**
 *
 * <p>Wraps any JSF input component and causes any validators or
 * converters attached to it to fire via an AJAX request triggered on a
 * parameterizable javascript event.  The response to the AJAX request
 * is rendered via DHTML to a parameterizable element in the DOM. </p>
 *
 * @author edburns
 */
public class AjaxValidatorComponent extends UIOutput {
    
    /** Creates a new instance of AjaxValidatorComponent */
    public AjaxValidatorComponent() {
    }
    

    private String messageId = null;
    private String eventHook = null;
    
    public String getMessageId() {
	    if (this.messageId != null){
		    return (this.messageId);
	    }
	ValueExpression ve = getValueExpression("messageId");
	if (ve != null) {
	    try {
		return ((String) ve.getValue(getFacesContext().getELContext()));
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return (null);
	}
    }

    public String getEventHook() {
	    if (this.eventHook != null){
		    return (this.eventHook);
	    }
	ValueExpression ve = getValueExpression("eventHook");
	if (ve != null) {
	    try {
		return ((String) ve.getValue(getFacesContext().getELContext()));
	    }
	    catch (ELException e) {
		throw new FacesException(e);
	    }
	} else {
	    return (null);
	}
    }	    
    
    public void setMessageId(String messageId) {
	    this.messageId = messageId;
    }
    
    public void setEventHook(String eventHook) {
	    this.eventHook = eventHook;
    }
    
    public String getFamily() { return "AjaxValidator"; }
    
    public void decode(FacesContext context) { 
        ExternalContext extContext = context.getExternalContext();
        String ifExistsContinueProcessing = (String)
            extContext.getRequestParameterMap().get(this.getClientId(context));
        
        // Don't take action unless this request is an ajax request for
        // this component.
        if (null == ifExistsContinueProcessing ||
                -1 == ifExistsContinueProcessing.indexOf("ajax")) {
            return;
        }
                
        List ajaxValidatorComponents = (List)
            extContext.getRequestMap().get(AjaxValidatorPhaseListener.AJAX_VALIDATOR_COMPONENTS);

        if (null == ajaxValidatorComponents) {
            ajaxValidatorComponents = new ArrayList();
            extContext.getRequestMap().put(AjaxValidatorPhaseListener.AJAX_VALIDATOR_COMPONENTS,
                                           ajaxValidatorComponents);
        }
        assert(null != ajaxValidatorComponents);
        ajaxValidatorComponents.add(this);
        
    }
    
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String id = this.getClientId(context);
        String eventHook = (String) this.getEventHook();
        if (null == eventHook) {
            eventHook = "onblur";
        }
        
        Util.renderMainScriptOnce(context, writer, this);        
        
        writer.startElement("span", this);
        writer.writeAttribute("id", id, null);
        String blurValue = "ajaxFocusIn('" + id + "', '" + eventHook + "');";
        writer.writeAttribute("onmouseover", blurValue, null);
        writer.writeAttribute("messageId", this.getMessageId(),
                null);
        
    }
    
    public void encodeChildren(FacesContext context) throws IOException {
    }
    
    public void encodeEnd(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        writer.endElement("span");
        
    }
    
    // ----------------------------------------------------- StateHolder Methods


    public Object saveState(FacesContext context) {

        Object values[] = new Object[3];
        values[0] = super.saveState(context);
        Object value = null;
        if (null == (value = this.getValueBinding("messageId"))) {
            value = this.getMessageId();
        }
        values[1] = value;
        if (null == (value = this.getValueBinding("eventHook"))) {
            value = this.getEventHook();
        }
        values[2] = value;
        return (values);

    }


    public void restoreState(FacesContext context, Object state) {

        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        if (values[1] instanceof ValueExpression) {
            this.setValueExpression("messageId", (ValueExpression) values[1]);
        }
        else {
            setMessageId((String) values[1]);
        }
        if (values[2] instanceof ValueExpression) {
            this.setValueExpression("eventHook", (ValueExpression) values[2]);
        }
        else {
            setEventHook((String) values[2]);
        }

    }

    
    
}
