package com.example.employee_management_system.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRequestDto {
    private String from;
    private String to;
    private String subject;
    private String html;
}
