package com.otming.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private int id;
    private String title;
    private String content;
    private User user;
    private BlogType blogType;
    private String summaryText;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cdate;
}
