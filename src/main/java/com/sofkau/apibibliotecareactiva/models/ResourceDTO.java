package com.sofkau.apibibliotecareactiva.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceDTO {
    private String id;
    @NotBlank(message = "el nombre no debe estar vacio")
    private String name;
    private LocalDate loanDate;
    @NotNull(message = "la cantidad no debe estar vacia")
    private int quantityAvailable;
    private int quantityBorrowed;
    @NotBlank(message = "el tipo no debe estar vacio")
    private String type;
    @NotBlank(message = "la tematica no debe estar vacia")
    private String thematic;
}
