package com.banyan.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User:krisjin
 * Date:2019/3/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foobar implements Serializable {
    private Long id;
    private String name;
    private int val;
    private LocalDateTime dateTime;
}