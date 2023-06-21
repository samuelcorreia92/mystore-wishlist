package com.mystore.wishlist.core;

import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/mystore/wishlist/core")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.mystore.wishlist.core")
public class RunCucumberTest {
}
