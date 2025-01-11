package com.okanerturan.techcareer_java_bootcamp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Details field is required")
    private String details;
}
