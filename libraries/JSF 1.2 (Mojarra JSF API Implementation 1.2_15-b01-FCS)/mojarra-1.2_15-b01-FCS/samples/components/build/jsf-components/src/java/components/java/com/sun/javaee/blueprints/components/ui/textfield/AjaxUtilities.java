/* Copyright 2005 Sun Microsystems, Inc. All rights reserved. You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: http://developer.sun.com/berkeley_license.html
 */

/*
 * AjaxUtilities.java
 *
 * Created on June 10, 2005, 4:04 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.sun.javaee.blueprints.components.ui.textfield;

import java.util.ArrayList;


/**
 * Utility methods for the AJAX Text component (and possibly other components
 * we'll develop next)
 *
 * @author Tor Norbye
 */
public class AjaxUtilities {
    /** Creates a new instance of AjaxUtilities */
    private AjaxUtilities() {
    }

    /** Perform a case insensitive binary search for the key in the data string.
     * Not an exact binary search since we return the index of the closest match
     * rather than returning an actual match.
     */
    private static int findClosestIndex(String[] data, String key, boolean ignoreCase) {
        // Do a binary search
        int low = 0;
        int high = data.length - 1;
        int middle = -1;

        while (high > low) {
            middle = (low + high) / 2;

            int result;

            // I can optimize this further by comparing on a per character
            // basis, increasingly more matching characters.
            //int result = key.compareTo(tags[middle]);
            if (ignoreCase) {
                result = key.compareToIgnoreCase(data[middle]);
            } else {
                result = key.compareTo(data[middle]);
            }

            if (result == 0) {
                return middle;
            } else if (result < 0) {
                high = middle;
            } else if (low != middle) {
                low = middle;
            } else {
                return high;
            }
        }

        return middle;
    }

    /**
     * Return (via the result object) a short completion list from the given data using
     * the given prefix
     * @param sortedData A sorted array of Strings we want to pick completion results
     *    from
     * @param prefix A prefix of some of the strings in the sortedData that the
     *    user has typed so far
     * @param result A result object that will be populated with completion results
     *    by this method
     */
    public static void addMatchingItems(String[] sortedData, String prefix, CompletionResult result) {
        int index;

        if (prefix.length() > 0) {
            // PENDING: Do we need to expose case sensitivity? That doesn't seem
            // useful for autocompletion textfields...
            boolean caseInsensitive = true;
            index = findClosestIndex(sortedData, prefix, caseInsensitive);
        } else {
            // If you're trying to complete and have nothing entered,
            // just show the beginning of the list
            index = 0;
        }

        if (index == -1) {
            // Nothing found...
            // TODO Should I return an empty set so the JavaScript code
            // can close the dialog?
            //return;
            // No for now just show results
            index = 0;
        }

        for (int i = 0; i < AjaxPhaseListener.MAX_RESULTS_RETURNED; i++) {
            if (index >= sortedData.length) {
                break;
            }

            result.addItem(sortedData[index]);
            index++;
        }
    }
}
