package com.okanerturan.techcareer_java_bootcamp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Details field is required")
    private String details;

    @NotNull(message = "Completion state is required")
    private Boolean completed;
}
