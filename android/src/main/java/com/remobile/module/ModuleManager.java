package com.remobile.module;

public class ModuleManager {
    private static ModuleActivity activity;
    private static RCTModule module;

    public static ModuleActivity getActivity() {
        return ModuleManager.activity;
    }

    public static void setActivity(ModuleActivity activity) {
        ModuleManager.activity = activity;
    }

    public static RCTModule getModule() {
        return ModuleManager.module;
    }

    public static void setModule(RCTModule module) {
        ModuleManager.module = module;
    }
}