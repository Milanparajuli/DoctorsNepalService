package com.milan.doctorsNepal.mail;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public final class EmailConstant {
    public static final Map<Template, String> MAIL = (Map<Template, String>) ImmutableMap.<Template, String>builder()
            .put(Template.SEND, "sendmail.html");


    private EmailConstant() {
    }

    public enum Template {
        SEND("Send_Email");

        private String subject;

        Template(String subject) {
            this.subject = subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String get() {
            return this.subject;
        }
    }
}
