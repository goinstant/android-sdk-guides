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

}
