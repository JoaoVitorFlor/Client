package br.com.client.api.exception.exceptionhandler;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @since 2019-03-24
 */

@AllArgsConstructor
@Data
public class Error implements Serializable {
	private static final long serialVersionUID = -5869846526843310867L;
	private String message;

}