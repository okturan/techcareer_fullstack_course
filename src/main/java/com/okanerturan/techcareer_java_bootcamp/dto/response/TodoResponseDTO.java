package com.okanerturan.techcareer_java_bootcamp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
