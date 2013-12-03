/* Copyright 2005 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://developer.sun.com/berkeley_license.html
 $Id: ChartItemTag.java,v 1.3 2005/11/01 21:59:11 jenniferb Exp $ */

package com.sun.javaee.blueprints.components.ui.taglib;

import com.sun.javaee.blueprints.components.ui.components.ChartItemComponent;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentTag;

/**
 * <p><strong>ChartItemTag</strong> is the tag handler that processes the 
 * <code>chartItem</code> custom tag.</p>
 */

public class ChartItemTag extends UIComponentTag {

    public ChartItemTag() {
        super();
    }

    //
    // Class methods
    //

    // 
    // Accessors
    //

    /**
     * <p>The label for this item</p>
     */
    private ValueExpression itemLabel = null;
    /**
     *<p>Set the label for this item.
     */
    public void setItemLabel(ValueExpression label) {
        this.itemLabel = label;
    }

    /**
     * <p>The color for this item.</p>
     */
    private ValueExpression itemColor = null;
    /**
     *<p>Set the color for this item.
     */
    public void setItemColor(ValueExpression color) {
        this.itemColor = color;
    }
    
    /**
     * <p>The value for this item.</p>
     */
    private ValueExpression itemValue = null;
    /**
     *<p>Set the ualue for this item.
     */
    public void setItemValue(ValueExpression itemVal) {
        this.itemValue = itemVal;
    }

    private ValueExpression value = null;
    public void setValue(ValueExpression value) {
        this.value = value;
    }

    //
    // General Methods
    //

    /**
     * <p>Return the type of the component.
     */
    public String getComponentType() {
        return "ChartItem";
    }

    /**
     * <p>Return the renderer type (if any)
     */
    public String getRendererType() {
        return null;
    }

    /**
     * <p>Release any resources used by this tag handler
     */
    public void release() {
        super.release();
        itemLabel = null;
        itemValue = null;
        itemColor = null;
    }

    //
    // Methods from BaseComponentTag
    //

    /**
     * <p>Set the component properties
     */
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        ChartItemComponent chartItem = (ChartItemComponent) component;

        if (null != value) {
            if (!value.isLiteralText()) {
                chartItem.setValueExpression("value", value);
            } else {
                chartItem.setValue(value.getExpressionString());
            }
        }

        if (null != itemLabel) {
            if (!itemLabel.isLiteralText()) {
                chartItem.setValueExpression("itemLabel", itemLabel);
            } else {
                chartItem.setItemLabel(itemLabel.getExpressionString());
            }
        }
        
        if (null != itemColor) {
            if (!itemColor.isLiteralText()) {
                chartItem.setValueExpression("itemColor", itemColor);
            } else {
                chartItem.setItemColor(itemColor.getExpressionString());
            }
        }
        
        if (null != itemValue) {
            if (!itemValue.isLiteralText()) {
                chartItem.setValueExpression("itemValue", itemValue);
            } else {
                chartItem.setItemValue(itemValue.getExpressionString());
            }
        }
    }

}
