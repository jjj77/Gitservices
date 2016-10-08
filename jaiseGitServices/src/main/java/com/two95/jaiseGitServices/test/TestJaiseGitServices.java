package com.two95.jaiseGitServices.test;

import java.io.IOException;

import com.two95.jaiseGitServices.ListAllBranches;
import com.two95.jaiseGitServices.ListAllTags;
import com.two95.jaiseGitServices.SearchinBranches;
import com.two95.jaiseGitServices.SearchinTags;
/**
*repopath is the local git repository path
*
*
*/
public class TestJaiseGitServices {
	public static void main(String args[]) throws IOException{
		String repopath = "C:/Users/schand018c/git/ruby/.git";
		System.out.println("******************************");
		System.out.println("List all branches");
		ListAllBranches.listBranches(repopath);
		System.out.println("******************************");
		System.out.println("List all tags");
		ListAllTags.listTags(repopath);
		System.out.println("******************************");
		System.out.println("Search in Branches for travis.yml");
		SearchinBranches.searchBranches(repopath, ".travis.yml");
		System.out.println("******************************");
		System.out.println("Search in Tags for travis.yml");
		SearchinTags.searchTags(repopath, ".travis.yml");
		System.out.println("******************************");
		
		
	}

}
