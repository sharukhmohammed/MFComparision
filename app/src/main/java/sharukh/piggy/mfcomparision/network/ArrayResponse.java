package sharukh.piggy.mfcomparision.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Developed by Sharukh Mohammed on 24 July 2018 at 17:58. Copyright © 2018 Wheelstreet All rights reserved.
 */
public class ArrayResponse<T> implements Serializable {

  @SerializedName("status-message")
  @Expose
  public int statusMessage;

  @SerializedName("status-code")
  @Expose
  public Integer statusCode;

  @SerializedName("data")
  @Expose
  public ArrayList<T> data;

}
