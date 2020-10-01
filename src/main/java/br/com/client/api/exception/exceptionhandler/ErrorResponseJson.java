package br.com.client.api.exception.exceptionhandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @since 2019-03-24
 */

@Data
public class ErrorResponseJson implements Serializable {
	
	private static final long serialVersionUID = -4073428345250135488L;
	
	private List<Error> errors;

	public ErrorResponseJson() {
		errors = new ArrayList<>();
	}

	public ErrorResponseJson(final String message) {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		Error error = new Error(message);
		this.errors.add(error);
	}

	public ErrorResponseJson(List<Error> errors) {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		this.errors.addAll(errors);
	}

}