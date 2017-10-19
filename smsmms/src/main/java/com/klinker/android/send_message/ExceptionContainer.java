package com.klinker.android.send_message;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mikhail Korshunov on 20.10.17.
 * Container class for exceptions
 */

public class ExceptionContainer implements Parcelable {

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public ExceptionContainer createFromParcel(Parcel in) {
      return new ExceptionContainer(in);
    }

    public ExceptionContainer[] newArray(int size) {
      return new ExceptionContainer[size];
    }
  };
  private String exceptionName;
  private String exceptionMessage;
  private String exceptionStackTrace;

  public ExceptionContainer(Exception exception) {
    this.exceptionName = exception.getClass().getSimpleName();
    this.exceptionMessage = exception.getMessage();
    this.exceptionStackTrace = getStackTrace(exception);
  }

  private ExceptionContainer(Parcel in) {
    exceptionName = in.readString();
    exceptionMessage = in.readString();
    exceptionStackTrace = in.readString();
  }

  private static String getStackTrace(Exception ex) {
    StringBuilder sb = new StringBuilder(500);
    StackTraceElement[] st = ex.getStackTrace();
    sb.append(ex.getClass().getName()).append(": ").append(ex.getMessage()).append("\n");
    for (StackTraceElement aSt : st) {
      sb.append("\t at ").append(aSt.toString()).append("\n");
    }
    return sb.toString();
  }

  public String getExceptionName() {
    return exceptionName;
  }

  public String getExceptionMessage() {
    return exceptionMessage;
  }

  public String getExceptionStackTrace() {
    return exceptionStackTrace;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(exceptionName);
    dest.writeString(exceptionMessage);
    dest.writeString(exceptionStackTrace);
  }
}
