package it.polito.oop.tables;

/**
 * Represents an option for a table
 * available for a party
 *
 */
public interface TableOption {

    /**
     * retrieves the time the table will be available
     * 
     * @return time as {@code "hh:mm"}
     */
    public String getTime();
    
    /**
     * retrieves the table number
     * @return table number
     */
    public int getTable();
    
    /**
     * retrieves the table occupation.
     * Occupation ranges between 0.0 and 1.0
     * 
     * @return table occupation
     */
    public double getOccupation();
    
    /**
     * String representation of the option
     * {@code "HH:MM, T = OO%"} where:
     * 
     * {@code HH:MM} is the time the table will likely become free,
     * {@code T} table number
     * {@code OO%} table occupation as percentage
     * 
     * @return the string representation
     */
    @Override
    public String toString();
    
}
