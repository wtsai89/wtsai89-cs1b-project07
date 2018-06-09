package cellularData;

import java.util.Iterator;

/**
 * Stores the entire subscription data for a single country
 */
public class Country {
    private String name;
    private LinkedList<SubscriptionYear> subscriptionsPerYear;
    private int minYear, maxYear;

    /**
     *
     * @param name sets the country name
     */
    public Country(String name)
    {
        this.name = name;
        subscriptionsPerYear = new LinkedList<>();
        minYear = 9999;
        maxYear = 0;
    }

    /**
     * Creates a new SubscriptionYear object and saves it in subscriptions array
     * @param year
     * @param subscriptions
     */
    public void addSubscriptionYear(int year, double subscriptions)
    {
        subscriptionsPerYear.add(new SubscriptionYear(year, subscriptions));
        if(year > maxYear)
            maxYear = year;
        if(year < minYear)
            minYear = year;
    }

    /**
     * accessor method for name
     * @return
     */
    public String getName() { return name; }

    /**
     * setter method for name
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     * accessor method for subscriptionsPerYear
     * @return
     */
    public LinkedList<SubscriptionYear> getSubscriptions() { return subscriptionsPerYear; }

    /**
     * accessor method for minYear
     * @return
     */
    public int getStartingYear() { return minYear; }

    /**
     * accessor method for maxYear
     * @return
     */
    public int getEndingYear() { return maxYear; }

    /**
     * returns the total number of subscriptions between start and end years
     * @param start
     * @param end
     * @return
     */
    public double getNumSubscriptionsForPeriod(int start, int end)
    {
        if(start > end)
            throw new IllegalArgumentException("Error: Starting year and ending year are inverted.\n");
        if((start > maxYear && end > maxYear) || (start < minYear && end < minYear))
            throw new IllegalArgumentException("Error: Both starting year and ending year are out of range.\n");

        int validStart = start;
        int validEnd = end;
        int flag = 0;  //only display corrected range if there is a discrepancy
        if (start < minYear) {
            validStart = minYear;
            flag = 1;
        }
        if (end > maxYear) {
            validEnd = maxYear;
            flag = 1;
        }
        if (flag == 1)
            System.out.printf("Illegal Argument Request of year range %d - %d. Valid period for %s is %d to %d\n", start, end, name, validStart, validEnd);

        double sum = 0;
        Iterator<SubscriptionYear> walker = subscriptionsPerYear.iterator();

        for(int i = 0; i < validStart - minYear; i++) //find the start of the period
            walker.next();

        for(int i = 0; i <= validEnd - validStart; i++)  //iterate to specified end
            sum += walker.next().getSubscriptions();

        return sum;
    }

    /**
     * displays the country name and all of the subscriptions in a single formatted line
     * @return
     */
    public String toString()
    {
        String s = String.format("%-25s", name);
        for(SubscriptionYear sub : subscriptionsPerYear)
            s += String.format("%7.2f", sub.getSubscriptions());

        s += "\n";
        return s;
    }

    /**
     * display the years in a single formatted line
     * @return
     */
    public String yearString()
    {
        String s = String.format("%-25s", "");
        for(int i = minYear; i <= maxYear; i++)
            s+= String.format("%7d", i);
        s += "\n";
        return s;
    }

    /**
     * If the countries' names are equal then return true
     * @param data
     * @return
     */
    public boolean equals(Object data)
    {
        Country country = (Country)data;
        if(this.name.equals(country.getName()))
            return true;

        return false;
    }
}
