package com.jwtapi.model;

import java.time.LocalDate;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogCall {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description = "id technique", hidden = true)
	private UUID id;
	@NotEmpty
	private String url;

	private String parameter;

	private LocalDate calledDate;
}
