package stepFiles;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageFiles.LoginPage;
import pageFiles.ManageCategoryPage;
import utilities.ExcelUtilities;

public class ManageCategoryPageStep extends BaseClassStep {

	LoginPage loginpage;
	ManageCategoryPage manageCategoryPage;
	
	@Test (priority = 1)
	public void verifyIfCategoryListisLoaded() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();
		boolean actualTableStatus = manageCategoryPage.categoryPageSelection();
		Assert.assertEquals(actualTableStatus, true, "Category table list is not displayed.");
	}
	
	@Test (priority = 2)
	public void VerifyNewCategoryCreation() throws AWTException, IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();
		manageCategoryPage.fetchingTheFirstEntryinTable();
		String categoryName = ExcelUtilities.getString(1, 0,"ManageCategory&Subcategory");
		manageCategoryPage.newCategoryCreation(categoryName);
		manageCategoryPage.categoryPageSelection();	
		String actualValueString = manageCategoryPage.fetchingTheFirstEntryinTable();
		String expectedValue = manageCategoryPage.readRandomCategoryNameString();
		Assert.assertEquals(actualValueString, expectedValue, "New Category creation wasn't success.");
	}
	
	@Test (priority = 3)
	public void searchNewlyAddedCategory() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();		
		String categoryName = manageCategoryPage.fetchingTheFirstEntryinTable();
		manageCategoryPage.searchNewlyAddedCategoryVisibility(categoryName);
		//add assertion
	}
	
	@Test(priority = 4, enabled =false)
	public void VerifyDeleteofNewlyAddedCategory() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();	
		manageCategoryPage.fetchingTheFirstEntryinTable();
		manageCategoryPage.deleteNewlyAddedCategory();
		manageCategoryPage.fetchingTheFirstEntryinTable();
		String actualValueString = manageCategoryPage.fetchingTheFirstEntryinTable();
		String expectedValue = ExcelUtilities.getString(1, 0,"ManageCategory&Subcategory");
		Assert.assertNotEquals(actualValueString, expectedValue, "New Category deletion wasn't success.");
	}
}
