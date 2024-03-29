package kr.ac.sku.intothevegan.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CommentDto {
    @NotBlank
    private String content;

    @NotBlank
    private String memberId;

    @NotBlank
    private Integer boardNo;
}
