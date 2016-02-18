package com.salesforce.androidsosguides;

import java.io.Serializable;

/**
 *
 */
public class Contact implements Serializable {

  public final String name;
  public final String company;

  public Contact(String name, String company) {
    this.name = name;
    this.company = company;
  }

  public static final Contact[] ALL = new Contact[]{
          new Contact("Keith Allen", "salesforce.com"),
          new Contact("Harry Roberts", "salesforce.com"),
          new Contact("Raymond Patterson", "salesforce.com"),
          new Contact("Jerry Ford", "salesforce.com"),
          new Contact("Kimberly Douglas", "salesforce.com"),
          new Contact("Jose Estrada", "salesforce.com"),
          new Contact("Rebecca Morris", "salesforce.com"),
          new Contact("Joe Duncan", "salesforce.com"),
          new Contact("Geoff Teehan", "salesforce.com"),
          new Contact("Adam Castro", "salesforce.com"),
          new Contact("Billy Wildman", "salesforce.com"),
          new Contact("Roger McKinnon", "salesforce.com"),
          new Contact("Michael Basch", "salesforce.com"),
          new Contact("Tammy Baxter", "salesforce.com")
  };

}
