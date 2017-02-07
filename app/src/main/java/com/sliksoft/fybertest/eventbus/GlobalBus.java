package com.sliksoft.fybertest.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public class GlobalBus {
    private static EventBus sBus;

    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}


