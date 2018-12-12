package sharukh.piggy.mfcomparision.model.network.parents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Developed by Sharukh Mohammed on 24 July 2018 at 17:58. Copyright © 2018 Wheelstreet All rights reserved.
 */
public class GenericResponse<T> implements Serializable {

  @SerializedName("status-message")
  @Expose
  public String statusMessage;

  @SerializedName("status-code")
  @Expose
  public Integer statusCode;

  @SerializedName("data")
  @Expose
  public T data;
}
