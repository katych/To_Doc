package com.cleanup.todoc.model;


public enum SortMethod {

    /**
     * List of all possible sort methods for task
     */

    /**
     * Sort alphabetical by name
     */
    ALPHABETICAL,
    /**
     * Inverted sort alphabetical by name
     */
    ALPHABETICAL_INVERTED,
    /**
     * Lastly created first
     */
    RECENT_FIRST,
    /**
     * First created first
     */
    OLD_FIRST,
    /**
     * No sort
     */
    NONE

}
