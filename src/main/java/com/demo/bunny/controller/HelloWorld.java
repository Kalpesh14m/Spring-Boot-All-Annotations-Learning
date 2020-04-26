package com.demo.bunny.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/first")
public class HelloWorld {

	@Autowired
	private User employeeProperties;

	public void method() {

		String employeeName = employeeProperties.getName();
		String employeeDept = employeeProperties.getDept();

		System.out.println(employeeName);
		System.out.println(employeeDept);

	}

	@GetMapping("/hello")
	@ResponseBody
	public String helloMsg() {
		method();
		return "Hi User";

	}

	@PostMapping("/requestBodyDemo")
	@ResponseBody
	public String requestBodyDemo(@RequestBody User u) {
		return "@RequestBody:" + "\nID: " + u.getId() + "\nName: " + u.getName() + "\nCity:" + u.getCity();
	}

	// Request Header
	@GetMapping("/requestHeaderDemo")
	@ResponseBody
	public String requestHeaderDemo(@RequestHeader(value = "token", defaultValue = "Kalpesh-Header") String token) {
		return "@RequestHeader\nToken: " + token;
	}

	// A Simple Mapping
	@GetMapping("/requestParamDemo")
	@ResponseBody
	public String requestParamDemo(@RequestParam String name) {
		return "@RequestParam: " + name;
	}

	// Specifying the Request Parameter Name
	@GetMapping("/requestParamDemo1")//localhost:8080/user/requestParamDemo1?id=1
	@ResponseBody
	public String requestParamDemo1(@RequestParam(name = "id") String userId) {
		return "@RequestParam: " + userId;
	}

	// Making an Optional Request Parameter
	@GetMapping("/requestParamDemo2")
	@ResponseBody
	public String requestParamDemo2(@RequestParam(name = "id", required = false) String userId) {
		return "@RequestParam: " + userId;
	}

	// Using Java 8 Optional
	@GetMapping("/requestParamDemoOptional")
	@ResponseBody
	public String requestParamDemoOptional(@RequestParam Optional<String> id) {
		return "ID: " + id.orElseGet(() -> "not provided");
	}

	// A Default Value for the Request Parameter
	@GetMapping("/requestParamDemoDefault")
	@ResponseBody
	public String requestParamDemoDefault(@RequestParam(defaultValue = "Demo") String id) {
		return "ID: " + id;
	}

	// Mapping All Parameters
	@PostMapping("/requestParamDemoMap")
	@ResponseBody
	public String requestParamDemoMap(@RequestParam Map<String, String> allParams) {
		return "Parameters are " + allParams.entrySet();
	}

	// Mapping a Multi-Value Parameter
	@GetMapping("/requestParamDemoList")
	@ResponseBody
	public String requestParamDemoList(@RequestParam List<String> id) {
		return "IDs are " + id;
	}

	// Query Parameter vs URI Path OR @RequestParam vs @PathVariable
	// While @RequestParams extract values from the query string
	// @PathVariables extract values from the URI path:

	// Encoded vs Exact Value
	// @PathVariable is extracting values from the URI path, it’s not encoded.
	// On the other hand, @RequestParam is

	@GetMapping("/pathVariableDemo/{id}/{name}")
	// http://localhost:8080/foos/abc => ID: abc
	// http://localhost:8080/foos/ab+c => ID: ab+c
	// @GetMapping({"/myfoos/optional", "/myfoos/optional/{id}"})
	// http://localhost:8080/myfoos/optional/abc => ID: abc else id then ID: null
	@ResponseBody
	public String pathVariableDemo(@PathVariable("id") String id, @PathVariable("name") String name) {
		return "ID: " + id + "\nName: " + name;
	}

	@GetMapping("/requestParam")
	// http://localhost:8080/foos?id=abc => ID: abc
	// http://localhost:8080/foos?id=ab+c => ID: ab c
	@ResponseBody
	public String getByIdUsingQueryParam(@RequestParam String id) {
		return "ID: " + id;
	}
}
