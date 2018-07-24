package com.htdz.def.dbmodel;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatToken implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private String name;
	private String token;
}