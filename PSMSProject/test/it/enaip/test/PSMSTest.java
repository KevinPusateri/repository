package it.enaip.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;



import net.sourceforge.jwebunit.junit.*;



public class PSMSTest {

	static Logger log = Logger.getLogger(PSMSTest.class);
	private WebTester tester;
	private static final String WEBSITE_URL = "http://localhost:9090/PSMSProject";


	@Before
	public void prepare() {
		BasicConfigurator.configure();
		log.info("This is Logger Info");
		tester = new WebTester();
//		webTester.setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
		tester.getTestContext().setBaseUrl(WEBSITE_URL);
	}

	@Test
	public void testLoginPage() {
		tester.beginAt("/index.jsp");
		tester.assertLinkPresent("menu");
		tester.clickLink("menu");
		tester.gotoPage("/jsp/menu.jsp");
		tester.assertTitleEquals("Menu");
	}

	@Test
	public void testMenuPage() {
		tester.assertLinkPresent("new");
		tester.assertLinkPresent("list");
		tester.clickLink("new");
		tester.clickLink("list");
		tester.assertLinkPresent("menu");
	}

}
