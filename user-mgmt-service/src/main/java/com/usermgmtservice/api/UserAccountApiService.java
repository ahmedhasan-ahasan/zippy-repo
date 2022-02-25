package com.usermgmtservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usermgmtservice.dto.ErrorInfoDto;
import com.usermgmtservice.dto.UserAccountActivationStatusDto;
import com.usermgmtservice.dto.UserAccountDto;
import com.usermgmtservice.exceptions.OtpAlreadyVerifiedException;
import com.usermgmtservice.exceptions.OtpMismatchException;
import com.usermgmtservice.exceptions.UnknownVerificationTypeException;
import com.usermgmtservice.exceptions.UserAccountAlreadyActivatedException;
import com.usermgmtservice.exceptions.UserAccountNotFoundException;
import com.usermgmtservice.service.ManageUserAccountService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 you can send request using the below using POST method : http://localhost:9090/account/new/customer
 {
    "firstName": "David",
    "lastName": "Paul",
    "emailAddress": "paul3@gmail.com",
    "mobileNo": "909098778",
    "password": "welcome1",
    "addressDto": {
        "addressLine1": "Matrivanam",
        "addressLine2": "SR Nagar",
        "city": "Hyderabad",
        "state": "Telengana",
        "zip": 600123,
        "country": "India"
    }
}
 */

/**
 * @author ahmed
 */
@RestController
@RequestMapping("/account")
public class UserAccountApiService {
	
	private Logger logger = LoggerFactory.getLogger(UserAccountApiService.class); 

	private static final String APPLICATION_JSON_MEDIA_TYPE = "application/json";
	private static final String HTTP_STATUS_NOT_FOUND = "404";
	private static final String HTTP_STATUS_TOO_MANY_REQUESTS = "429";
	private static final String HTTP_STATUS_ALREADY_REPROTED = "208";
	private static final String HTTP_STATUS_BAD_REQUEST = "400";
	private static final String HTTP_STATUS_NOT_ACCEPTABLE = "406";
	private static final String HTTP_STATUS_OK_SUCCESS= "200";

	
	@Autowired
	private ManageUserAccountService manageUserAccountService;

	/**
	 * 
	 * @param userAccountDto
	 * @return
	 */
	@PostMapping(value = "/new/customer", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> registerCustomer(@RequestBody UserAccountDto userAccountDto) {
		long systemUserId = this.manageUserAccountService.registerCustomer(userAccountDto);
		return ResponseEntity.ok(String.valueOf(systemUserId));
	}

	/**
	 * you can send get request
	 * http://localhost:9090/customer/count/emailAddress?emailAddress=test@gmail.com
	 * 
	 * @param emailAddress
	 * @return
	 */
	/**
	 * TODO rename the method name it should get the count by email address
	 * 
	 */
	@GetMapping(value = "/count/emailAddress", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> getUserAccountByEmailAddress(@RequestParam("emailAddress") String emailAddress) {
		logger.info("calling getUserAccountByEmailAddress return logn");
		return ResponseEntity.ok(this.manageUserAccountService.countUserAccountByEmailAddress(emailAddress));
	}

	/**
	 * you can send get request
	 * http://localhost:9090/customer/count/mobileNo?mobileNo=99999
	 * 
	 * @param mobileNo
	 * @return
	 */
	/**
	 * TODO rename the method name it should get the count by mobile no
	 * 
	 */
	@GetMapping(value = "/count/mobileNo", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> getUserAccountByMobileNo(@RequestParam("mobileNo") String mobileNo) {
		logger.info("calling getUserAccountByMobileNo return long");
		return ResponseEntity.ok(this.manageUserAccountService.countUserAccountByMobileNo(mobileNo));
	}

	@GetMapping(value = "/{userAccountId}" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable("userAccountId") long userAccountId){
		logger.info("calling getUserAccount consumes long return long");
		return ResponseEntity.ok(this.manageUserAccountService.getUserAccount(userAccountId));
	}
	
	@GetMapping(value = "/email/{emailAddress}" , produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiResponses(value = {
			@ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, content = {
					@Content(mediaType = APPLICATION_JSON_MEDIA_TYPE, schema = @Schema(allOf = { ErrorInfoDto.class })) })})
	public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable("emailAddress") String emailAddress) throws UserAccountNotFoundException{
		logger.info("calling getUserAccount pathparam email address return userAccountDto");
		return ResponseEntity.ok(this.manageUserAccountService.getUserAccount(emailAddress));
	}
	
	

	@PostMapping(value = "/verify/{accountId}/{verificationCode}/{verificationType}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, content = {
					@Content(mediaType = APPLICATION_JSON_MEDIA_TYPE, schema = @Schema(allOf = { ErrorInfoDto.class })) }),
			@ApiResponse(responseCode = HTTP_STATUS_ALREADY_REPROTED, content = {
					@Content(mediaType = APPLICATION_JSON_MEDIA_TYPE, schema = @Schema(allOf = { ErrorInfoDto.class })) }),
			@ApiResponse(responseCode = HTTP_STATUS_NOT_ACCEPTABLE, content = {
					@Content(mediaType = APPLICATION_JSON_MEDIA_TYPE, schema = @Schema(allOf = { ErrorInfoDto.class })) }),
			@ApiResponse(responseCode = HTTP_STATUS_BAD_REQUEST, content = {
					@Content(mediaType = APPLICATION_JSON_MEDIA_TYPE, schema = @Schema(allOf = { ErrorInfoDto.class })) }),
			@ApiResponse(responseCode = HTTP_STATUS_TOO_MANY_REQUESTS, content = {
					@Content(mediaType = APPLICATION_JSON_MEDIA_TYPE, schema = @Schema(allOf = { ErrorInfoDto.class })) }),
			@ApiResponse(responseCode = HTTP_STATUS_OK_SUCCESS, content = {
					@Content(mediaType = APPLICATION_JSON_MEDIA_TYPE, schema = @Schema(allOf = { UserAccountActivationStatusDto.class })) })})
	public ResponseEntity<UserAccountActivationStatusDto> verifyOtpAndUpdateAccountStatus(
			@PathVariable("accountId") long accountId, @PathVariable("verificationCode") String verificationCode,
			@PathVariable("verificationType") String verificationType)
			throws UserAccountNotFoundException, OtpMismatchException, UserAccountAlreadyActivatedException,
			OtpAlreadyVerifiedException, UnknownVerificationTypeException {
		logger.info("calling verifyOtpAndUpdateAccountStatus");
		return ResponseEntity.ok(this.manageUserAccountService.verifyOtpAndUpdateAccountStatus(accountId,
				verificationCode, verificationType));
	}

	/**
	 * TODO We are handling the exceptions here, however it is highly recommended to
	 * replace them with global handler in separate class
	 */
	@ExceptionHandler(UserAccountNotFoundException.class)
	public ResponseEntity<ErrorInfoDto> handleUserAccountNotFoundException(UserAccountNotFoundException e) {
		System.out.println("handleUserAccountNotFoundException has been called");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorInfoDto());
	}

	@ExceptionHandler(OtpAlreadyVerifiedException.class)
	public ResponseEntity<ErrorInfoDto> handleOtpAreadyVerifiedException(OtpAlreadyVerifiedException e) {
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(e.getErrorInfoDto());
	}

	@ExceptionHandler(UserAccountAlreadyActivatedException.class)
	public ResponseEntity<ErrorInfoDto> handleUserAccountAlreadyActivatedException(
			UserAccountAlreadyActivatedException e) {
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getErrorInfoDto());
	}

	@ExceptionHandler(OtpMismatchException.class)
	public ResponseEntity<ErrorInfoDto> handleOtpMismatchException(OtpMismatchException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorInfoDto());
	}

	@ExceptionHandler(UnknownVerificationTypeException.class)
	public ResponseEntity<ErrorInfoDto> handle(UnknownVerificationTypeException e) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getErrorInfoDto());
	}

	/**
	 * TODO this method has been written as a workaround and should be replaced
	 * correctly
	 * 
	 * @param e
	 * @return
	 */
	/*
	 * @ExceptionHandler(DataIntegrityViolationException.class) public
	 * ResponseEntity<String> handle(DataIntegrityViolationException e) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); }
	 */
	
	
}
