package com.jayghz.bookhub.dto;

import lombok.Data;

@Data
public class BookDetailsDTO {
    private Integer id;
    private String title;
    private String slug;
    private String description;
    private Float price;
    private String coverPath;
    private String filePath;
    private String categoryName;
    private String authorName;
}
