package org.henrique.helper;

public enum Screen {

    MAIN("main.ftl"),;

    public final String template;

    Screen(String template) {
        this.template = template;
    }
}
