package com.startlion.startlionserver.util;

import java.security.Principal;

public class UserUtil {

    public static Long getUserId(Principal principal) {
        return Long.valueOf(principal.getName());
    }
}
