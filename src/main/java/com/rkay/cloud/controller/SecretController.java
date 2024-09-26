package com.rkay.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager")
public class SecretController {

	@Autowired
	Environment env;

	@Value("${passwords}")
	private String password;

	@Value("${cloudpass}")
	private String cloudpass;

	@GetMapping("/{secretName}")
	public String getSecre(@PathVariable String secretName) {

		System.out.println(password + "=" + cloudpass);

		return password + "=" + cloudpass;

	}

}
