package com.robot.utils;

import java.util.UUID;

public class IDutils {

    public static String getID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
