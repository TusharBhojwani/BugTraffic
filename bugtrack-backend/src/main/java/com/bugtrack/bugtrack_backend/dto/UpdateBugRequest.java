package com.bugtrack.bugtrack_backend.dto;

import lombok.Data;

@Data
public class UpdateBugRequest {

    private String title;
    private String description;
    private String status;
    private String priority;
}