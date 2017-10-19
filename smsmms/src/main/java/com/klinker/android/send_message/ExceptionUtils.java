package com.klinker.android.send_message;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Mikhail Korshunov on 20.10.17.
 * Class helps to deliver exceptions to the main app
 */

public class ExceptionUtils {

  public static final String SMSMMS_EXCEPTION_ACTION = "com.klinker.android.messaging.SMSMMS_EXCEPTION";
  private static final String SMSMMS_EXCEPTION_EXTRA = "SMSMMS_EXCEPTION_EXTRA";

  static void sendException(Context context, Exception exception) {
    Intent intent = new Intent();
    intent.putExtra(SMSMMS_EXCEPTION_EXTRA, new ExceptionContainer(exception));
    BroadcastUtils.sendExplicitBroadcast(context, intent, SMSMMS_EXCEPTION_ACTION);
  }

  public static Exception unpackBroadcastIntent(Intent intent) {
    if (!SMSMMS_EXCEPTION_ACTION.equals(intent.getAction()) ||
        !intent.hasExtra(SMSMMS_EXCEPTION_EXTRA)) {
      return null;
    }

    ExceptionContainer container = intent.getParcelableExtra(SMSMMS_EXCEPTION_EXTRA);
    return container.getException();
  }
}
