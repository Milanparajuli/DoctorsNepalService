package com.milan.doctorsNepal.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailSender {
    private String to;
    private String msgBody;
    private String subject;
    private String attachment;
}
