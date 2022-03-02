package com.usermgmtservice;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"com.usermgmtservice.api","com.usermgmtservice.repositories","com.usermgmtservice.service"})
public class UserMgmtApplicationSuiteTest {

}
