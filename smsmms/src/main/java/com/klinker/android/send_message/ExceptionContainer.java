package com.klinker.android.send_message;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mikhail Korshunov on 20.10.17.
 */

public class ExceptionContainer implements Parcelable {
  private Exception exception;

  public ExceptionContainer(Exception exception) {
    this.exception = exception;
  }

  private ExceptionContainer(Parcel in) {
    try {
      in.readException();
    } catch (Exception e) {
      exception = e;
    }
  }

  public Exception getException() {
    return exception;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeException(exception);
  }

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public ExceptionContainer createFromParcel(Parcel in) {
      return new ExceptionContainer(in);
    }

    public ExceptionContainer[] newArray(int size) {
      return new ExceptionContainer[size];
    }
  };

}
